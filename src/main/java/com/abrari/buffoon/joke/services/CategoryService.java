package com.abrari.buffoon.joke.services;

import com.abrari.buffoon.exceptions.SQLNotFoundException;
import com.abrari.buffoon.joke.datalayer.entities.Category;

import java.sql.SQLException;

public interface CategoryService {

    Category getById(Integer id) throws SQLException;
    Category getByName(String name) throws SQLNotFoundException;
}
