package com.jps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jps.exception.TodoException;
import com.jps.model.Todo;
import com.jps.repository.TodoJpaRepository;



@Service
public class TodoServiceImpl  implements TodoService {
	@Autowired
	TodoJpaRepository todoJpaRepository;

	@Override
	public Todo createTodo(String username, Todo todo) {
		if(todo.getId()>0) {
			Optional<Todo> existingTodo = todoJpaRepository.findById(todo.getId());
			if(existingTodo.isPresent()) {
				throw new TodoException("Todo already available in database");
			}		
		}
		
		todo.setUsername(username);
		return todoJpaRepository.save(todo);
	}

	@Override
	public List<Todo> getAllTodos(String username) {
		return todoJpaRepository.findAll();
	}

	@Override
	public Todo getTodo(String username, long id) {
		// TODO Auto-generated method stub
		return todoJpaRepository.findById(id).get();
	}

	@Override
	public Todo updateTodo(String username, long id, Todo todo) {
		Optional<Todo> existingTodo = todoJpaRepository.findById(todo.getId());
				
		if (!existingTodo.isPresent()) {
			throw new TodoException("ID not found in booking database. Provide valid booking ID to delete");
		}
		
		
		todo.setUsername(username);
		return todoJpaRepository.save(todo);
	}

	@Override
	public void deleteTodo(String username, long id) {
		
		Optional<Todo> todo = todoJpaRepository.findById(id);
		if (!todo.isPresent()) {
			throw new TodoException("ID not found in database. Provide valid todo ID to delete");
		}
		todoJpaRepository.deleteById(id);
		
	}
	

}
