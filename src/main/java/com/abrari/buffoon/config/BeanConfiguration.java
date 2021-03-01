package com.abrari.buffoon.config;

import com.abrari.buffoon.joke.datalayer.entities.Joke;
import com.abrari.buffoon.joke.dto.JokeGUIDLessDTO;
import com.abrari.buffoon.joke.dto.JokeIDLessDTO;
import com.abrari.buffoon.utils.BiDirectionalMapping;
import com.abrari.buffoon.utils.BiDirectionalMappingImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public BiDirectionalMapping<Joke, JokeIDLessDTO> getJokeIDLessDTOMapping(){

        var temp = new BiDirectionalMappingImpl<Joke, JokeIDLessDTO>();
        temp.setClassOfA(Joke.class);
        temp.setClassOfB(JokeIDLessDTO.class);

        return temp;
    }

    @Bean
    public BiDirectionalMapping<Joke, JokeGUIDLessDTO> getJokeGUIDLessDTOMapping(){

        var temp = new BiDirectionalMappingImpl<Joke, JokeGUIDLessDTO>();
        temp.setClassOfA(Joke.class);
        temp.setClassOfB(JokeGUIDLessDTO.class);

        return temp;
    }

}
