package com.example.demo.service;

import org.fluttercode.datafactory.impl.DataFactory;

public class FakeDataGenerator {
    public static void main(String[] args) {
        DataFactory df = new DataFactory();
        for (int i = 0; i < 100; i++) {
            String name = df.getFirstName() + " "+ df.getLastName() + " " + df.getCity();
            System.out.println(name);
        }
    }
}
