package com.abrari.buffoon.joke.datalayer.repos;

import com.abrari.buffoon.joke.datalayer.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Integer> {

    @Query("from Joke where guid = (:guid)")
    Optional<Joke> findByGUID(@Param("guid") UUID guid);
}
