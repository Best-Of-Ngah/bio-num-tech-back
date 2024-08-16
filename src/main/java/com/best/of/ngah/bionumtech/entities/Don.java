package com.best.of.ngah.bionumtech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "don")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String provance;

    @Column(name = "provene_date")
    private LocalDate proveneceDate;

    @Column(precision = 15, scale = 2)
    private BigDecimal montant;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;
}
