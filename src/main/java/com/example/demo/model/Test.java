package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Konkretny test zainicjowany przez usera.
 * Przy tworzeniu testu zostaną wylosowane pytania (i zapisane do tabeli TestQuestion)
 */

@Entity
@Table(name = "test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tid;
    Integer sid;
    String album;
    Date start;
    Date finish;
    String ip;
    Double avgscore;
    Boolean official;
}
