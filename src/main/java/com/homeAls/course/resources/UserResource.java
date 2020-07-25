package com.homeAls.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.homeAls.course.entities.User;
import com.homeAls.course.services.UserService;

@RestController
@RequestMapping(value = "/users") // passa o caminho do recurso
public class UserResource {

	// faz a injeção de dependencia para o camada service
	@Autowired
	// a classe de objeto deve ser registrado no mecanismo do framewor
	// como um componente do spring, la na classe service
	private UserService service;

	// Controlador rest que responde no caminho users
	@GetMapping
	// ResponseEntity - é um tipo do spring para retornar respostas da web
	public ResponseEntity<List<User>> findAll() {

		// pega o findAll da camada service
		List<User> list = service.findAll();
		// retora a respota de sucessi do http e no corpo da reposta é colocado a lista
		return ResponseEntity.ok().body(list);
	}

	// definir o tipo da requisição do tipo GET, e definir que requisição tera uma
	// parametro
	// vai aceitar um ID como parametro
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id)// com a anotação o sring pega o valor e aceita no metodo
	{
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
