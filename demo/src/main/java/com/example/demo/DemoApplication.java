package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.javafx.app;

import javafx.application.Application;



@SpringBootApplication
public class DemoApplication{

	public static void main(String[] args) {
		Application.launch(app.class, args);
	}

}
