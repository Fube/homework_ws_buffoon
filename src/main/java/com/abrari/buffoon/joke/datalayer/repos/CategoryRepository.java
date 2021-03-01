package com.abrari.buffoon.joke.datalayer.repos;

import com.abrari.buffoon.joke.datalayer.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("from Category where name = (:name)")
    Optional<Category> getByName(@Param("name") String name);
}
