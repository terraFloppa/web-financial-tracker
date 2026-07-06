package com.terrafloppa.web_financial_tracker.category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Не была найдена категория с id: " + id);
    }
}
