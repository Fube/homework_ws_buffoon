package com.abrari.buffoon.joke;

import com.abrari.buffoon.joke.dto.JokeGUIDLessDTO;
import com.abrari.buffoon.joke.dto.JokeIDLessDTO;
import com.abrari.buffoon.joke.services.JokeService;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/jokes")
public class JokeController {


    @NonNull
    final private JokeService jokeService;

    @GetMapping
    private ResponseEntity<Map<String, Object>> getAllJokes(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit
    ){

        return ResponseEntity.ok(jokeService.getAllJokes(page, limit));
    }

    @GetMapping("/{guid}")
    private ResponseEntity<JokeIDLessDTO> getSpecificJoke(@PathVariable UUID guid) throws SQLException {

        return ResponseEntity.ok(jokeService.getJokeByGUID(guid));
    }

    @PostMapping
    private ResponseEntity<JokeIDLessDTO> createJoke(@RequestBody JokeGUIDLessDTO joke) throws SQLException {

        return ResponseEntity.ok(jokeService.addJoke(joke));
    }

    @PutMapping("/{guid}")
    private ResponseEntity<JokeIDLessDTO> updateJoke(@PathVariable UUID guid, @RequestBody JokeGUIDLessDTO joke) throws SQLException {

        return ResponseEntity.ok(jokeService.updateJoke(guid, joke));
    }

    @PatchMapping(path = "/{guid}", consumes = "application/json-patch+json")
    private ResponseEntity<JokeIDLessDTO> partiallyUpdateJoke(@PathVariable UUID guid, @RequestBody JsonPatch patch) throws JsonPatchException, SQLException {

        return ResponseEntity.ok(jokeService.partiallyUpdateJoke(guid, patch));
    }

    @DeleteMapping("/{guid}")
    private ResponseEntity<JokeIDLessDTO> deleteJoke(@PathVariable UUID guid) throws SQLException {

        return ResponseEntity.ok(jokeService.deleteJokeById(guid));
    }
}
