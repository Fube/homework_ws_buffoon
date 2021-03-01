package com.abrari.buffoon.joke.services;

import com.abrari.buffoon.exceptions.SQLNotFoundException;
import com.abrari.buffoon.joke.datalayer.entities.Category;
import com.abrari.buffoon.joke.datalayer.repos.CategoryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    @NonNull
    final private CategoryRepository categoryRepository;

    final private Supplier<SQLNotFoundException> createNewSQLNotFoundWithMessage = () -> new SQLNotFoundException("Category not found");


    @Override
    public Category getById(Integer id) throws SQLException {

        return categoryRepository.findById(id).orElseThrow(createNewSQLNotFoundWithMessage);
    }

    @Override
    public Category getByName(String name) throws SQLNotFoundException {
        return categoryRepository.getByName(name).orElseThrow(createNewSQLNotFoundWithMessage);
    }
}
