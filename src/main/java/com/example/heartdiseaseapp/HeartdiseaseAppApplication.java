package com.example.heartdiseaseapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
@ComponentScan(basePackages = "com.example.HeartdiseaseApp")//Indique à Spring de scanner tous les packages pour trouver les @Service, @Controller,
@EnableJpaRepositories(basePackages = "com.example.HeartdiseaseApp.repositories")
@EntityScan(basePackages = "com.example.HeartdiseaseApp.entities")//@EntityScan - Indique à Hibernate où trouver les entités JPA (comme Patients)
public class HeartdiseaseAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartdiseaseAppApplication.class, args);
	}

}
