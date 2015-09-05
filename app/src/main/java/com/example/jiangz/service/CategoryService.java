package com.example.jiangz.service;

import com.example.jiangz.entity.Category;

import java.util.List;
import java.util.Map;

/**
 * Created by JiangZ on 2015-09-05.
 */
public interface CategoryService {
    long createCategory(Category category);

    boolean deleteAllCategorys();

    int deleteCategoryById(Integer id);

    int update(Category category);

    List<Category> fetchCategoryByMap(Map<Object,Object> map);
}
