package com.web.shopping.service;

import com.web.shopping.pojo.Content;

import java.util.List;

public interface ContentService {

    List<Content> queryAllContent();

    Content queryContentById(Long ContentId);

    void addContent(Content content);

    void updateContent(Content content);

    void deleteContentByIds(Long[] ids);

    List<Content> queryContentByCategoryId(Long categoryId);
}
