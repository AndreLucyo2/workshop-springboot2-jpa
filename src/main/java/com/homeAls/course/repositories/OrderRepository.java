package com.homeAls.course.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.homeAls.course.entities.Order;

//cria o repositorie, instancia um objeto com varias operações
//o spring data ja tem a implementação padrao do jparepositorie
//nao precisa regstrr como componente, pois ja herda da JpaRepository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
