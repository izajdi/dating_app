package com.example.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.sp.User.repository.UserRepository;

@SpringBootApplication
public class SpProject2021Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpProject2021Application.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
	}


}
