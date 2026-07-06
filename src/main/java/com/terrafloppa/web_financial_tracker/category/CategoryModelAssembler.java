package com.terrafloppa.web_financial_tracker.category;

import com.terrafloppa.web_financial_tracker.category.dto.CategoryResponseDto;
import org.jspecify.annotations.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<Category, CategoryResponseDto> {
    @Override
    @NonNull
    public CategoryResponseDto toModel(@NonNull Category entity) {
        // Маппим сущность, добавляем ссылки и возвращаем её
        CategoryResponseDto responseDto = new CategoryResponseDto(entity);
        responseDto.add(linkTo(methodOn(CategoryController.class).getById(entity.getId())).withSelfRel());
        responseDto.add(linkTo(methodOn(CategoryController.class).getAll()).withRel("categories"));
        return responseDto;
    }

    @Override
    @NonNull
    public CollectionModel<CategoryResponseDto> toCollectionModel(@NonNull Iterable<? extends Category> entities) {
        // Маппим все сущности и добавляем ссылку
        CollectionModel<CategoryResponseDto> collectionModel =
                RepresentationModelAssembler.super.toCollectionModel(entities);
        collectionModel.add(linkTo(methodOn(CategoryController.class).getAll()).withSelfRel());
        return collectionModel;
    }
}
