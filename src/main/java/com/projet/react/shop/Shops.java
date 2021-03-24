package com.projet.react.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SHOPS")
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String titre;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private String adresse;
    private LocalDateTime date_de_creation;
}
