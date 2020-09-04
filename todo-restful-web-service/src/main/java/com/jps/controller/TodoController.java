package com.jps.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jps.model.Todo;
import com.jps.service.TodoService;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class TodoController {
	
	@Autowired
	TodoService todoServicecall;
	
	@GetMapping("/v1-jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) { 
		return todoServicecall.getAllTodos(username);
		//return  todoJpaRepository.findByUsername(username);	
		//return  todoService.findAll();	
	}
	
	@GetMapping("/v1-jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) { 
		return todoServicecall.getTodo(username, id);
		//it returns optional back, for getting actual value need to do get()
		//return todoJpaRepository.findById(id).get();
		//return  todoService.findById(id);
		
	}

	@PutMapping("/v1-jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username,
			@PathVariable long id, @RequestBody Todo todo){
		Todo updatedTodo = todoServicecall.updateTodo(username, id, todo);
		//insert username also to db
		//todo.setUsername(username);
       //Todo updatedTodo = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK) ;
		//ResponseEntity helps to build specific request with status 
	}
	
	@PostMapping("/v1-jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String 
			username,@RequestBody Todo todo){
		Todo createdTodo = todoServicecall.createTodo(username, todo);
		//location get current resource url {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return  ResponseEntity.created(uri).build();
		//return ResponseEntity.ok(uri).build();

	}
	
	@DeleteMapping("/v1-jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
			@PathVariable long id){
		todoServicecall.deleteTodo(username, id);
		//todoJpaRepository.deleteById(id); //deleteById is void return type
		return ResponseEntity.noContent().build();
		//ResponseEntity helps to build specific request with status 
	}
	
	
}
