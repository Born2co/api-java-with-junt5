package com.jps.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jps.model.Todo;
import com.jps.service.TodoService;

@WebMvcTest(TodoController.class)
@AutoConfigureMockMvc
public class TodoControllerTest {
	
	@Autowired
    MockMvc mockMvc;
	
	@Autowired
    ObjectMapper objectMapper;
    
    @MockBean
    TodoService todoService;    
    
    @Test
    void testCreateTodo() throws Exception {
    	
        Todo todos = new Todo(1L, "in28minutes", "test todo",new Date(), false);
        String json = objectMapper.writeValueAsString(todos);
        
        when(todoService.createTodo("in28minutes", todos)).thenReturn(todos);
        
        mockMvc.perform(post("/v1-jpa/users/in28minutes/todos")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    	
    }
    @Test
    void testDeleteBookingById() throws Exception {
    	 Todo todos = new Todo(1L, "in28minutes", "test todo",new Date(), false);
         String json = objectMapper.writeValueAsString(todos);
      
         //doNothing().when(libraryEventProducer).sendLibraryEvent_Approach2(isA(LibraryEvent.class));
         
         when(todoService.getTodo("in28minutes", 1l)).thenReturn(todos);
         mockMvc.perform(delete("/v1-jpa/users/in28minutes/todos/{id}", todos.getId())	
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }
    
    
    @Test
    void testgetTodoById() throws Exception {
    	 Todo todos = new Todo(1L, "in28minutes", "test todo",new Date(), false);
         String json = objectMapper.writeValueAsString(todos);
         
       when(todoService.getTodo("in28minutes", 1l)).thenReturn(todos);
        mockMvc.perform(get("/v1-jpa/users/in28minutes/todos/{id}", todos.getId())
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    
    @Test
    void testUpdateTodo() throws Exception {
    	 Todo todos = new Todo(1L, "in28minutes", "test todo",new Date(), false);
         String json = objectMapper.writeValueAsString(todos);
         
        when(todoService.updateTodo("in28minutes", 1L, todos)).thenReturn(todos);
        mockMvc.perform(put("/v1-jpa/users/in28minutes/todos/{id}", todos.getId())
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    
    
}
