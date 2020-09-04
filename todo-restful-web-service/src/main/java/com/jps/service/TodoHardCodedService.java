package com.jps.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jps.model.Todo;
@Service
public class TodoHardCodedService {
	
	private static List<Todo> todos = new ArrayList<>();
	private static long idCounter = 0;
	
	static {
		todos.add( new Todo(++idCounter, "in28minutes","learn to dance",new Date(), false));
		todos.add( new Todo(++idCounter, "in28minutes","learn to micro service",new Date(), false));
		todos.add( new Todo(++idCounter, "in28minutes","learn to react",new Date(), false));
	}
	
	
	public Todo save(Todo todo) {
		if(todo.getId()==-1 ||todo.getId()==0) { //create new todo
			todo.setId(++idCounter);
			todos.add(todo);
			
		}else {
			deleteById(todo.getId());
			todos.add(todo);
			
		}return todo;
		
	}
	public List<Todo> findAll() {
		return todos;
	}

	public Todo deleteById(long id) {
		Todo todo =findById(id);
		
		if(todo ==null) return null; //find todo
		if(todos.remove(todo)){     //able to delete todo
			return todo;
			
		}
		return null;
	}

	public Todo findById(long id) {
		for(Todo todo: todos) {
			if(todo.getId()==id) {
				return todo;
				
			}
		}
		return null;
	}
}
