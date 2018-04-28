package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Rodzaje (typy) pytań, np. pytania do egzaminu dyplomowego, pytania
 * z jakiegoś przedmiotu etc.
 */

@Entity
@Table(name = "questiontype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuesitonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer qtid;
    String description;
}
