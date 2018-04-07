package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "testquestion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tqid;
    Integer tid;
    Integer qid;
    Integer clientAnswerId;     //to co odpowiedział klient: 0,1,2
    Integer score;              //obliczone przez backend
}
