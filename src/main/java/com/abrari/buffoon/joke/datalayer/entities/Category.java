package com.abrari.buffoon.joke.datalayer.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "joke_categories")
public class Category {

    @Id
    private Integer id;

    private String name;
}
