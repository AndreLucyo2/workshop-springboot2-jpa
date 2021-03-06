package com.homeAls.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeAls.course.entities.Order;
import com.homeAls.course.repositories.OrderRepository;

//Anotação que registra esta classe como um componente no spring, assim o framework pode faer a injeção de dependencia
@Service
public class OrderService {

	// declara a dependencia para o repository
	@Autowired
	private OrderRepository repository;

	// recupera todos os usuarios
	public List<Order> findAll() {
		// pega a ação que esta disponivel no repository (Banco)
		// faz a chamada e repessa para a camada do repository
		return repository.findAll();
	}

	// faz busca por ID
	public Order findById(Long id) {

		// faz a chamada e repessa para a camada do repository
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
