package com.best.of.ngah.bionumtech.entities;


import com.best.of.ngah.bionumtech.enums.TypeName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private TypeName name;
}
