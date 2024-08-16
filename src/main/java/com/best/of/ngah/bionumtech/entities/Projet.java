package com.best.of.ngah.bionumtech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "projet")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder


public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String status;

    private int budget;

    @Column(length = 50)
    private String description;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Column(name = "date_realisation")
    private LocalDate dateRealisation;

    @Column(length = 50)
    private String image;

    @OneToOne
    @JoinColumn(name = "id", unique = true, nullable = false)
    private Don don;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Type type;
}
