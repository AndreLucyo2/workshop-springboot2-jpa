package com.homeAls.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.homeAls.course.entities.User;
import com.homeAls.course.repositories.UserRepository;

//fala pro spring que essa é uma classe de configuação
@Configuration
//especifica para qual perfil esta configuração se aplica
@Profile("test")
public class TestConfig implements CommandLineRunner {

	
	
	//esta anotation faz com que o spring resolva esta dependencia e ja instancia
	@Autowired
	//injeção de dependencia do deposirories (principio SOLID)
	//um objeto depende de outro
	private UserRepository userRepository;

	//Vem da implemetação do CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		//cria os usuarios, serão os seeds, dados iniciais do banco
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//acessa o banco, e ja salva a lista no banco
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}
