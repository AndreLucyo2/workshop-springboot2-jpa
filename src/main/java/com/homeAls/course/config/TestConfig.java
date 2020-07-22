package com.homeAls.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.homeAls.course.entities.Category;
import com.homeAls.course.entities.Order;
import com.homeAls.course.entities.User;
import com.homeAls.course.entities.enums.OrderStatus;
import com.homeAls.course.repositories.CategoryRepository;
import com.homeAls.course.repositories.OrderRepository;
import com.homeAls.course.repositories.UserRepository;

//fala pro spring que essa é uma classe de configuação
@Configuration
//especifica para qual perfil esta configuração se aplica
@Profile("test")
public class TestConfig implements CommandLineRunner {

	
	//esta anotation faz com que o spring resolva esta dependencia e ja instancia
	@Autowired
	//injeção de dependencia do deposirories (principio SOLID) - onde sera gravado os dados (tabela)
	//um objeto depende de outro
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	//Vem da implemetação do CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		//CATEGORIAS:
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		//salva no banco a lista:
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		//USER - client
		//cria os usuarios, serão os seeds, dados iniciais do banco
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//PEDIDOS - order
		//a String "2019-06-20T19:53:07Z" = horario noa padroa UTC, é padrao no mundo, ao usar , ja ajusta para o horario local
		//ao inserir o Order ja esta associado ao u1 - cinculado pela FK
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.CANCELED, u1);
		
		//acessa o banco, e ja salva a lista de User no banco - Client
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		//acessa o banco, e ja salva a lista de Order no banco - Pedido
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}
