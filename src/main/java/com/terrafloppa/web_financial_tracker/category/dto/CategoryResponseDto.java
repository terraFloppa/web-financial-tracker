package com.terrafloppa.web_financial_tracker.category.dto;

import com.terrafloppa.web_financial_tracker.category.Category;
import com.terrafloppa.web_financial_tracker.category.CategoryType;
import org.springframework.hateoas.RepresentationModel;

public class CategoryResponseDto extends RepresentationModel<CategoryResponseDto> {
    private final Long id;
    private final String name;
    private final CategoryType categoryType;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.categoryType = category.getCategoryType();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }
}
