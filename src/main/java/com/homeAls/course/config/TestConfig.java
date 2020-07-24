package com.homeAls.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.homeAls.course.entities.Category;
import com.homeAls.course.entities.Order;
import com.homeAls.course.entities.OrderItem;
import com.homeAls.course.entities.Payment;
import com.homeAls.course.entities.Product;
import com.homeAls.course.entities.User;
import com.homeAls.course.entities.enums.OrderStatus;
import com.homeAls.course.repositories.CategoryRepository;
import com.homeAls.course.repositories.OrderItemRepository;
import com.homeAls.course.repositories.OrderRepository;
import com.homeAls.course.repositories.ProductRepository;
import com.homeAls.course.repositories.UserRepository;

//fala pro spring que essa é uma classe de configuação
@Configuration
// no application.properties o campo  spring.profiles.active=test aponta para este endPoint e especifica para qual perfil esta configuração se aplica 
//no application-test.properties esta setado o banco e o comportamento
@Profile("test")
public class TestConfig implements CommandLineRunner {

	// esta anotation faz com que o spring resolva esta dependencia e ja instancia
	@Autowired
	// injeção de dependencia do deposirories (principio SOLID) - onde sera gravado
	// os dados (tabela)
	// um objeto depende de outro
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	// Vem da implemetação do CommandLineRunner
	@Override
	public void run(String... args) throws Exception {

		// ###################################################################################################################
		// CATEGORIAS:
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		// salva TODOS no banco:
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		// ###################################################################################################################
		// PRODUTO
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		// salva TODOS no banco:
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		// ###################################################################################################################
		// CRIAR AS ASSOCIAÇÕES DOS PRODUTOS COM AS CATEGORIAS - ver o diagrama no pdf
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		// salva novamente ja com as associações
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		// ###################################################################################################################
		// USER - client
		// cria os usuarios, serão os seeds, dados iniciais do banco
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		// acessa o banco, e ja salva a lista de User no banco - Client
		userRepository.saveAll(Arrays.asList(u1, u2));

		// ###################################################################################################################
		// PEDIDOS - order
		// a String "2019-06-20T19:53:07Z" = horario noa padroa UTC, é padrao no mundo,
		// ao usar , ja ajusta para o horario local
		// ao inserir o Order ja esta associado ao u1 - cinculado pela FK
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELED, u1);

		// acessa o banco, e ja salva a lista de Order no banco - Pedido
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		// ###################################################################################################################
		//ITENS DE PEDIDO
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		// acessa o banco, e ja salva a lista
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		// ###################################################################################################################
		//PAGAMENTOS
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		//para salvar um objeto dependente nume relação UM-PARA-EM
		// DEVE faz a associação em memoria promeiro, e depois manda salvar
		o1.setPayment(pay1);
		//Salva novamente o order
		orderRepository.save(o1);
		
	}
}
