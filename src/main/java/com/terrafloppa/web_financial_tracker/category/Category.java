package com.terrafloppa.web_financial_tracker.category;

import com.terrafloppa.web_financial_tracker.category.dto.CategoryRequestDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "category_type")
    private CategoryType categoryType;

    public Category() {
    }

    public Category(String name, CategoryType categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }

    public Category(CategoryRequestDto requestDto) {
        this(requestDto.name(), requestDto.categoryType());
    }

    public Category(Long id, String name, CategoryType categoryType) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
    }

    public Category updateFromDto(CategoryRequestDto dto) {
        setName(dto.name());
        setCategoryType(dto.categoryType());

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name)
                && Objects.equals(categoryType, category.categoryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryType);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }
}
