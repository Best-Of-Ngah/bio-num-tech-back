package com.best.of.ngah.bionumtech.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long liked;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Project project;

    @ManyToOne(optional = false)
    private User user;
}
