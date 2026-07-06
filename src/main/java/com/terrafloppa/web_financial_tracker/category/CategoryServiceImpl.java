package com.terrafloppa.web_financial_tracker.category;

import com.terrafloppa.web_financial_tracker.category.dto.CategoryRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(CategoryRequestDto requestDto) {
        return repository.save(new Category(requestDto));
    }

    @Override
    public List<Category> readAll() {
        return repository.findAll();
    }

    @Override
    public Category readById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category update(CategoryRequestDto dto, Long id) {
        Category category = readById(id).updateFromDto(dto);
        return repository.save(category);
    }

    // TODO Проверить
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
