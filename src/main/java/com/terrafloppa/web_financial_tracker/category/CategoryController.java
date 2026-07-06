package com.terrafloppa.web_financial_tracker.category;

import com.terrafloppa.web_financial_tracker.category.dto.CategoryRequestDto;
import com.terrafloppa.web_financial_tracker.category.dto.CategoryResponseDto;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryModelAssembler assembler;

    public CategoryController(CategoryService service, CategoryModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<CategoryResponseDto> getAll() {
        return assembler.toCollectionModel(service.readAll());
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return assembler.toModel(service.readById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@Valid @RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto responseDto = assembler.toModel(service.create(requestDto));
        return ResponseEntity
                .created(responseDto.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(
            @Valid @RequestBody CategoryRequestDto requestDto,
            @PathVariable Long id
    ) {
        CategoryResponseDto responseDto = assembler.toModel(service.update(requestDto, id));
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
