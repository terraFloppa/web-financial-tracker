package com.terrafloppa.web_financial_tracker.category;

import com.terrafloppa.web_financial_tracker.category.dto.CategoryRequestDto;

import java.util.List;

public interface CategoryService {
    Category create(CategoryRequestDto requestDto);

    List<Category> readAll();

    Category readById(Long id);

    Category update(CategoryRequestDto requestDto, Long id);

    void delete(Long id);
}
