package com.best.of.ngah.bionumtech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean status;

    private Double budget;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime requestDate;

    private LocalDateTime realisationDate;

    @Column(columnDefinition = "TEXT")
    private String image;

    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private Don don;

    @ManyToOne(optional = false)
    private Type type;
}
