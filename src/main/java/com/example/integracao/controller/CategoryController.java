package com.example.integracao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integracao.dto.CategoryDTO;
import com.example.integracao.entities.Category;
import com.example.integracao.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDTO dto) {
        Optional<Category> category = categoryService.findCategoryByName(dto.name());
        if (category.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Categoria já cadastrada."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Category>("Created", categoryService.createCategory(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> listCategory() {
        List<Category> listCategory = categoryService.listCategory();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Category>>("Ok", listCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> findCategory(@PathVariable String id) {
        Category category = categoryService.findCategory(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Category>("Ok", category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String id) {
        Category category = categoryService.findCategory(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable String id, @RequestBody CategoryDTO dto) {
        Category category = categoryService.findCategory(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Categoria não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Category>("Updated", categoryService.updateCategoryById(id, dto)));
    }
}
