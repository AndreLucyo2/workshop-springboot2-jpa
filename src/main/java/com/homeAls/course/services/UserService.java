package com.homeAls.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeAls.course.entities.User;
import com.homeAls.course.repositories.UserRepository;

//Anotação que registra esta classe como um componente no spring, assim o framework pode faer a injeção de dependencia
@Service
public class UserService {

	//declara a dependencia para o repository
	@Autowired
	private UserRepository repository;
	
	//recupera todos os usuarios
	public List<User> findAll() {
		//pega a ação que esta disponivel no repository (Banco)
		//faz a chamada e repessa para a camada do repository
		return repository.findAll();
	}
	
	//faz busca por ID
	public User findById(Long id) {
		
		//faz a chamada e repessa para a camada do repository
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
}
