package com.terrafloppa.web_financial_tracker.category.dto;

import com.terrafloppa.web_financial_tracker.category.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
        @NotBlank(message = "Название не может быть пустым")
        String name,

        @NotNull(message = "Тип категории обязателен")
        CategoryType categoryType
) {}
