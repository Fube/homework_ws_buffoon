package com.abrari.buffoon.joke.services;

import com.abrari.buffoon.exceptions.SQLNotFoundException;
import com.abrari.buffoon.joke.datalayer.entities.Joke;
import com.abrari.buffoon.joke.datalayer.repos.JokeRepository;
import com.abrari.buffoon.joke.dto.JokeGUIDLessDTO;
import com.abrari.buffoon.joke.dto.JokeIDLessDTO;
import com.abrari.buffoon.utils.BiDirectionalMapping;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JokeServiceImpl implements JokeService {

    @NonNull
    final private JokeRepository jokeRepository;

    @NonNull
    final private CategoryService categoryService;

    @NonNull
    final private ObjectMapper objectMapper;

    @NonNull
    final private BiDirectionalMapping<Joke, JokeIDLessDTO> jokeIDLessDTOMapping;

    @NonNull
    final private BiDirectionalMapping<Joke, JokeGUIDLessDTO> jokeGUIDLessDTOMapping;

    final private Supplier<SQLNotFoundException> createNewSQLNotFoundWithMessage = () -> new SQLNotFoundException("Joke not found");


    @Override
    public Iterable<JokeIDLessDTO> getAllJokes() {

        return jokeRepository.findAll()
                .stream()
                .map(jokeIDLessDTOMapping::mapToRight)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getAllJokes(Integer page, Integer limit) {

        var _page = jokeRepository
                .findAll(PageRequest.of(page-1, limit))
                .map(jokeIDLessDTOMapping::mapToRight);

        return new TreeMap<>(){{

            put("jokes", _page.getContent());
            put("currentPage", _page.getNumber() + 1);
            put("totalItems", _page.getTotalElements());
            put("totalPages", _page.getTotalPages());
        }};
    }

    @Override
    public JokeIDLessDTO getJokeById(Integer id) throws SQLException {
        return jokeRepository
                .findById(id)
                .map(jokeIDLessDTOMapping::mapToRight)
                .orElseThrow(createNewSQLNotFoundWithMessage);
    }

    public JokeIDLessDTO getJokeByGUID(UUID guid) throws SQLException {

        return jokeRepository.
                findByGUID(guid)
                .map(jokeIDLessDTOMapping::mapToRight)
                .orElseThrow(createNewSQLNotFoundWithMessage);
    }

    @Override
    public JokeIDLessDTO addJoke(JokeGUIDLessDTO joke) throws SQLException {

        Joke asJoke = jokeGUIDLessDTOMapping.mapToLeft(joke);

        Joke createdJoke = jokeRepository.save(asJoke);

        createdJoke.getCategory().setName(
                categoryService.getById(createdJoke.getCategory().getId()).getName()
        );

        return jokeIDLessDTOMapping.mapToRight(createdJoke);
    }

    @Override
    public JokeIDLessDTO deleteJokeById(UUID guid) throws SQLException {

        var joke =  jokeRepository.findByGUID(guid).orElseThrow(createNewSQLNotFoundWithMessage);
        jokeRepository.deleteById(joke.getId());
        return jokeIDLessDTOMapping.mapToRight(joke);
    }

    @Override
    public JokeIDLessDTO partiallyUpdateJoke(UUID guid, JsonPatch patch) throws SQLException, JsonPatchException {

        var foundJoke =  jokeRepository.findByGUID(guid).orElseThrow(createNewSQLNotFoundWithMessage);
        val originalGUID = foundJoke.getGuid();
        val originalCatName = foundJoke.getCategory().getName();

        JsonNode patched = patch.apply(
                objectMapper.convertValue(foundJoke, JsonNode.class)
        );
        var backToJoke = objectMapper.convertValue(patched, Joke.class);

        if(!originalGUID.equals(backToJoke.getGuid()))throw new SQLIntegrityConstraintViolationException("Cannot set GUID explicitly");
        if(!originalCatName.equals(backToJoke.getCategory().getName()))throw new SQLIntegrityConstraintViolationException("Cannot set category name explicitly");

        jokeRepository.save(backToJoke);

        backToJoke.getCategory().setName(
                categoryService.getById(backToJoke.getCategory().getId()).getName()
        );

        return jokeIDLessDTOMapping.mapToRight(backToJoke);
    }

    @Override
    public JokeIDLessDTO updateJoke(UUID guid, JokeGUIDLessDTO joke) throws SQLException {

        var foundJoke = jokeRepository.findByGUID(guid).orElseThrow(createNewSQLNotFoundWithMessage);

        var mapped = jokeGUIDLessDTOMapping.mapToLeft(joke);

        mapped.setId(foundJoke.getId());
        mapped.setGuid(foundJoke.getGuid());

        jokeRepository.save(mapped);

        mapped.getCategory().setName(
                categoryService.getById(mapped.getCategory().getId()).getName()
        );

        return jokeIDLessDTOMapping.mapToRight(mapped);
    }
}
