package com.homeAls.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeAls.course.entities.User;

//cria o repositorie, instancia um objeto com varias operações
//o spring data ja tem a implementação padrao do jparepositorie
public interface UserRepository extends JpaRepository<User, Long> {

}
