package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Person;
import com.example.app.service.PersonsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/persons")
public class PersonsController {

    @Autowired
    private PersonsService service;

    @GetMapping(path = "/getPerson", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getPerson(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "number") String number) {
        return new ResponseEntity<>(service.getPerson(name, number), HttpStatus.OK);
    }

    @GetMapping(path = "/getPersons", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(service.getPersons(), HttpStatus.OK);
    }

    @PostMapping(path = "/createPerson", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        service.createPerson(person.getName(), person.getNumber());
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
}
