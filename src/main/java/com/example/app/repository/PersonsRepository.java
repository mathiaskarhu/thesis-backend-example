package com.example.app.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.app.model.Person;

@Repository
public class PersonsRepository {

    private final JdbcTemplate jdbc;

    public PersonsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Person> getPerson(String name, String number) {
        StringBuilder query = new StringBuilder("SELECT id, name, number FROM persons WHERE name = ? AND number = ?");
        return jdbc.query(query.toString(), new PersonsRowMapper(), name, number);
    }

    public List<Person> getPersons() {
        StringBuilder query = new StringBuilder("SELECT id, name, number FROM persons");
        return jdbc.query(query.toString(), new PersonsRowMapper());
    }

    public void createPerson(String name, String number) {
        StringBuilder query = new StringBuilder("INSERT INTO persons (name, number) VALUES (?, ?)");
        jdbc.update(query.toString(), name, number);
    }
}
