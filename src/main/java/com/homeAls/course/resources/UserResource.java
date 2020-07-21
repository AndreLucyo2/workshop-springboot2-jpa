package com.homeAls.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeAls.course.entities.User;

@RestController
@RequestMapping(value = "/users")//passa o caminho do recurso
public class UserResource {

	//Controlador rest que responde no caminho users
	@GetMapping
	//Metodo de teste para retornar os usuarios
	//ResponseEntity - é um tipo do spring par retornar respostas da web
	public ResponseEntity<User> findAll() {
		//metodo para ecessar o endpoin do recurso
		//so para teste criado um user
		User u = new User(1L, "Maria", "maria@gmail.com", "9999999", "12345");
		
		//retorna ok, como o body da resposnta
		return ResponseEntity.ok().body(u);
	}
}
