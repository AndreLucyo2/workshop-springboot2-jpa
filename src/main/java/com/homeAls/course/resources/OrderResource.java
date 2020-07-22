package com.homeAls.course.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeAls.course.entities.Order;
import com.homeAls.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")//passa o caminho do recurso
public class OrderResource {

	//faz a injeção de dependencia para o camada service
	@Autowired 
	//a classe de objeto deve ser registrado no mecanismo do framewor
	//como um componente do spring, la na classe service
	private OrderService service;
	
	//Controlador rest que responde no caminho users
	@GetMapping
	//ResponseEntity - é um tipo do spring para retornar respostas da web
	public ResponseEntity<List<Order>> findAll() {

		//pega o findAll da camada service
		List<Order> list = service.findAll();
		//retora a respota de sucessi do http e no corpo da reposta é colocado a lista
		return ResponseEntity.ok().body(list);
	}
	
	//definir o tipo da requisição do tipo GET, e definir que  requisição tera uma parametro
	//vai aceitar um ID como parametro
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id)//com a anotação o sring pega o valor e aceita no metodo 
	{
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
	