package com.jps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jps.model.Todo;
@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
	
	List<Todo> findByUsername(String username);

}
