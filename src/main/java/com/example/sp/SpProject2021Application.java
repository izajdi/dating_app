package com.example.sp;

import com.example.sp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.sp.repository.UserRepository;

@SpringBootApplication
public class SpProject2021Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpProject2021Application.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		this.userRepository.save(new User("bart", "zajda", "aa@aa.pl", "aaa", 22));
	}
}
