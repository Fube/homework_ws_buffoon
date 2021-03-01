package com.abrari.buffoon.joke.services;

import com.abrari.buffoon.joke.dto.JokeGUIDLessDTO;
import com.abrari.buffoon.joke.dto.JokeIDLessDTO;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public interface JokeService {

    Iterable<JokeIDLessDTO> getAllJokes();
    Map<String, Object> getAllJokes(Integer page, Integer limit);
    JokeIDLessDTO getJokeById(Integer id) throws SQLException;
    JokeIDLessDTO getJokeByGUID(UUID guid) throws SQLException;
    JokeIDLessDTO addJoke(JokeGUIDLessDTO joke) throws SQLException;
    JokeIDLessDTO deleteJokeById(UUID guid) throws SQLException;
    JokeIDLessDTO partiallyUpdateJoke(UUID guid, JsonPatch patch) throws SQLException, JsonPatchException;
    JokeIDLessDTO updateJoke(UUID guid, JokeGUIDLessDTO joke) throws SQLException;
}
