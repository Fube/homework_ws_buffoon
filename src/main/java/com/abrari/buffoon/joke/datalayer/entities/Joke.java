package com.abrari.buffoon.joke.datalayer.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jokes")
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "guid", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID guid = UUID.randomUUID();

    private String setup;
    private String punchline;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


}
