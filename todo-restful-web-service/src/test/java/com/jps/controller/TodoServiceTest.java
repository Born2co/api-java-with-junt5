package com.jps.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jps.exception.TodoException;
import com.jps.model.Todo;
import com.jps.repository.TodoJpaRepository;
import com.jps.service.TodoServiceImpl;


public class TodoServiceTest {

    @Mock
    private TodoJpaRepository todoRepository;
    @InjectMocks
    private TodoServiceImpl todoServiceImpl;
    
    @MockBean
    private Todo todos;
    
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        todos = new Todo(1L, "jai", "test todo",new Date(), false);
    }
    
    @Test
    public void createTodoSuccess() {
    	when(todoRepository.findById(todos.getId())).thenReturn(Optional.ofNullable(null));
    	when(todoRepository.save((Todo) any())).thenReturn(todos);
    	Todo resultTodos=todoServiceImpl.createTodo("jai", todos);
        assertEquals(todos, resultTodos);
    }
    
    @Test
    public void createTodoFail() {
    	when(todoRepository.findById(todos.getId())).thenReturn(Optional.of(todos));
    	assertThrows(TodoException.class, () -> {
    		todoServiceImpl.createTodo("jai", todos);
    		
    	});
    	
   
    }
    
    @Test
    public void findTodoByIdSuccess() {
    	when(todoRepository.findById(todos.getId())).thenReturn(Optional.of(todos));
    	Todo getTodo=todoServiceImpl.getTodo("jai", todos.getId());
        assertEquals(todos, getTodo);
    }

    
    @Test
    public void updateTodoSuccess() {
        when(todoRepository.findById(todos.getId())).thenReturn(Optional.of(todos));
        when(todoRepository.save(todos)).thenReturn(todos);
        Todo updateTodo = todoServiceImpl.updateTodo("jai", todos.getId(), todos);
        assertEquals(todos, updateTodo);
    }
    
    @Test
    public void updateTodoFailure() {
        when(todoRepository.findById(todos.getId())).thenReturn(Optional.ofNullable(null));
        assertThrows(TodoException.class,  () -> {
        	todoServiceImpl.updateTodo("jatodosi", todos.getId(), todos);
        	
        });
    }
    
    @Test
    public void deleteTodoFailure() {
    	 when(todoRepository.findById(todos.getId())).thenReturn(Optional.ofNullable(null));
        assertThrows(TodoException.class,  () -> {
        	todoServiceImpl.deleteTodo("jai", todos.getId());
        	
        });
    }
    
    
}
