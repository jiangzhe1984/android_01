package com.example.jiangz.service.impl;

import android.content.Context;
import com.example.jiangz.dao.CategoryDBAdapter;
import com.example.jiangz.entity.Category;
import com.example.jiangz.service.CategoryService;
import java.util.List;
import java.util.Map;

/**
 * Created by JiangZ on 2015-09-05.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDBAdapter categoryDBAdapter;

    public CategoryServiceImpl(Context context){
        categoryDBAdapter = new CategoryDBAdapter(context);
    }

    @Override
    public long createCategory(Category category) {
        long result = 0l;

        try {
            categoryDBAdapter.open("write");
            result = categoryDBAdapter.createCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            categoryDBAdapter.closeDB();
            categoryDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public boolean deleteAllCategorys() {
        boolean flag = false;
        try {
            categoryDBAdapter.open("write");
            flag = categoryDBAdapter.deleteAllCategorys();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            categoryDBAdapter.closeDB();
            categoryDBAdapter.closeHelper();
        }
        return flag;
    }

    @Override
    public int deleteCategoryById(Integer id) {
        int result = 0;

        try{
            categoryDBAdapter.open("write");
            result = categoryDBAdapter.deleteCategoryById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            categoryDBAdapter.closeDB();
            categoryDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public int update(Category category) {
        int result = 0;

        try{
            categoryDBAdapter.open("write");
            result = categoryDBAdapter.update(category);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            categoryDBAdapter.closeDB();
            categoryDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public List<Category> fetchCategoryByMap(Map<Object, Object> map) {
        List<Category> categories = null;

        try {
            categoryDBAdapter.open("write");
            categories = categoryDBAdapter.fetchCategoryByMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            categoryDBAdapter.closeDB();
            categoryDBAdapter.closeHelper();
        }
        return categories;
    }
}
