package com.homeAls.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.homeAls.course.entities.User;
import com.homeAls.course.repositories.UserRepository;
import com.homeAls.course.services.exceptions.DatabaseException;
import com.homeAls.course.services.exceptions.ResourceNotFoundException;

//Anotação que registra esta classe como um componente no spring, assim o framework pode faer a injeção de dependencia
@Service
public class UserService {

	// declara a dependencia para o repository
	@Autowired
	private UserRepository repository;

	// recupera todos os usuarios
	public List<User> findAll() {
		// pega a ação que esta disponivel no repository (Banco)
		// faz a chamada e repessa para a camada do repository
		return repository.findAll();
	}

	// faz busca por ID
	public User findById(Long id) {

		// faz a chamada e repassa para a camada do repository
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {
		User entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
