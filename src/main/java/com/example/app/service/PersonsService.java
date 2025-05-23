package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Person;
import com.example.app.repository.PersonsRepository;

@Service
public class PersonsService {

    @Autowired
    private PersonsRepository repository;

    public List<Person> getPerson(String name, String number) {
        return repository.getPerson(name, number);
    }

    public List<Person> getPersons() {
        return repository.getPersons();
    }

    public void createPerson(String name, String number) {
        repository.createPerson(name, number);
    }
}
