package org.yascode.securingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.yascode.securingweb.model.Role;
import org.yascode.securingweb.model.Subscriber;
import org.yascode.securingweb.repository.RoleRepository;
import org.yascode.securingweb.repository.SubscriberRepository;

import java.util.Set;

@SpringBootApplication
public class SecuringWebApplication {


	public static void main(String[] args) {
		SpringApplication.run(SecuringWebApplication.class, args);
	}

	//@Bean
	public int init(SubscriberRepository subscriberRepository,
					RoleRepository roleRepository,
					BCryptPasswordEncoder passwordEncoder) {
		Role role1 = Role.builder()
				.name("USER")
				.description("Basic role for normal users")
				.build();
		Role role2 = Role.builder()
				.name("ADMIN")
				.description("Administrative role with extensive privileges")
				.build();

		roleRepository.save(role1);
		roleRepository.save(role2);

		Role userRole = roleRepository.findByName("USER");
		Role adminRole = roleRepository.findByName("ADMIN");

		Subscriber subscriber1 = Subscriber.builder()
				.email("user")
				.password(passwordEncoder.encode("user"))
				.roles(Set.of(userRole))
				.build();

		Subscriber subscriber2 = Subscriber.builder()
				.email("admin")
				.password(passwordEncoder.encode("admin"))
				.roles(Set.of(userRole, adminRole))
				.build();

		subscriberRepository.save(subscriber1);
		subscriberRepository.save(subscriber2);

		return 0;
	}

}
