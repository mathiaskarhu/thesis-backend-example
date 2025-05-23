package com.example.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.app.controller.PersonsController;
import com.example.app.model.Person;
import com.example.app.service.PersonsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonsController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonsService service;

    @Test
    public void testGetPersons() throws Exception {
        List<Person> persons = List.of(
                new Person(1, "Matti Meikäläläinen", "+358 40 123 4567"));

        Mockito.when(service.getPersons()).thenReturn(persons);

        mockMvc.perform(get("/api/v1/persons/getPersons")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Matti Meikäläläinen"))
                .andExpect(jsonPath("$[0].number").value("+358 40 123 4567"));

        Mockito.verify(service, Mockito.times(1)).getPersons();
    }
}
