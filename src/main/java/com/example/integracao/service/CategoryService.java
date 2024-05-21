package com.example.integracao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.integracao.dto.CategoryDTO;
import com.example.integracao.entities.Category;
import com.example.integracao.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(CategoryDTO dto){
        try{
            Category newCategory = categoryRepository.save(new Category(null, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newCategory;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  Optional<Category> findCategoryByName(String name){
        try{
            Optional<Category> category = categoryRepository.findByName(name);
            return category;

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Category> listCategory(){
        try{
            List<Category> category = categoryRepository.findAll();
            return category;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Category findCategory(String id){
        try{
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isEmpty()) {
                return null;
            }

            return category.get();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteCategory(String id){
        try{
            categoryRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }

    public Category updateCategoryById(String id, CategoryDTO dto){
        try{
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isEmpty()) {
                return null;
            }
            Category newCategory = categoryRepository.save(new Category(id, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newCategory;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
