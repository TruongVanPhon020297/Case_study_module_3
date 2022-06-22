package com.codegym.dao;

import com.codegym.model.Category;

public interface CategoryService extends IGeneralService<Category>{
    boolean existById(int categoryId);
}
