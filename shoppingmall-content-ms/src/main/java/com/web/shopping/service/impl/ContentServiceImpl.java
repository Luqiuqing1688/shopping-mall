package com.web.shopping.service.impl;

import com.web.shopping.mapper.ContentMapper;
import com.web.shopping.pojo.Content;
import com.web.shopping.pojo.ContentExample;
import com.web.shopping.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {


    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Content> queryAllContent() {
        return contentMapper.selectByExample(null);
    }

    @Override
    public Content queryContentById(Long ContentId) {
        return contentMapper.selectByPrimaryKey(ContentId);
    }

    @Override
    public void addContent(Content content) {
        content.setCategoryId((long) 1);
        contentMapper.insert(content);
        //清除缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
    }

    @Override
    public void updateContent(Content content) {
        Content oldContent = contentMapper.selectByPrimaryKey(content.getId());
        //清除分类广告缓存
        redisTemplate.boundHashOps("content").delete(oldContent.getCategoryId());

        contentMapper.updateByPrimaryKey(content);
        //清除缓存
        if(content.getCategoryId()!=oldContent.getCategoryId()){
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        }
    }

    @Override
    public void deleteContentByIds(Long[] ids) {
        for (Long id : ids) {
            Content content = contentMapper.selectByPrimaryKey(id);
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());

            contentMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Content> queryContentByCategoryId(Long categoryId) {
        //读取缓存代码
        List<Content> list = (List<Content>) redisTemplate.boundHashOps("content").get(categoryId);

        if(list==null){
            //首次加载
            System.out.println("读取数据库数据================");
            ContentExample example = new ContentExample();
            ContentExample.Criteria criteria = example.createCriteria();
            //有效广告、广告id、字段排序
            criteria.andStatusEqualTo("1");
            criteria.andCategoryIdEqualTo(categoryId);
            example.setOrderByClause("sort_order");

            list = contentMapper.selectByExample(example);
            redisTemplate.boundHashOps("content").put(categoryId, list);
        }else{
            System.out.println("Redis读取缓存数据==============");
        }
        return list;
    }


}
