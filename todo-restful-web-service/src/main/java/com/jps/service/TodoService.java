package com.jps.service;

import java.util.List;

import com.jps.model.Todo;

public interface TodoService {
	
	public Todo createTodo(String username, Todo todo);
	
	public List<Todo> getAllTodos(String username);
	public Todo getTodo(String username, long id);
	
	public Todo updateTodo( String username,long id,Todo todo);
	
	public void deleteTodo(String username, long id);

}
