package com.best.of.ngah.bionumtech.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "action")
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
    private int id;

    private int liked;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "id")
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;
}
