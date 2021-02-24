package com.sample.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.model.Role;
import com.sample.app.model.User;
import com.sample.app.model.UserBasicDetails;
import com.sample.app.repository.UserRepository;
import com.sample.app.repository.interfaces.UserBasicInfo;
import com.sample.app.repository.interfaces.UserBasicInfoOpenProjection;

@SpringBootApplication
public class App {

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	private static void print(List<UserBasicInfo> usersInfo) {
		for (UserBasicInfo info : usersInfo) {
			System.out.println(info.getId() + "," + info.getFirstName());

			for (Role role : info.getRoles()) {
				System.out.println(role);
			}
		}
	}

	private static void printBasicInfo(List<UserBasicInfoOpenProjection> usersInfo) {
		for (UserBasicInfoOpenProjection info : usersInfo) {
			System.out.println(info.getId() + "," + info.getFullName());

			for (Role role : info.getRoles()) {
				System.out.println(role);
			}
		}
	}

	private static void printBasicDetails(List<UserBasicDetails> usersBasicDetails) {
		for (UserBasicDetails info : usersBasicDetails) {
			System.out.println(info.getId() + "," + info.getFirstName());

		}
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {

			Role role1 = new Role("Admin");
			Role role2 = new Role("Service_user");
			Role role3 = new Role("dept_admin");

			Set<Role> set1 = new HashSet<>();
			Set<Role> set2 = new HashSet<>();
			Set<Role> set3 = new HashSet<>();

			set1.add(role1);
			set2.add(role2);
			set3.add(role3);

			User user1 = new User("Krishna", "Gurram", 31, "Bangalore", set1);
			User user2 = new User("Sailu", "PTR", 32, "Hyderabad", set2);
			User user3 = new User("Gopi", "Battu", 32, "Canada", set3);

			userRepository.saveAll(Arrays.asList(user1, user2, user3));

			List<UserBasicInfo> info = userRepository.findByIdIn(Arrays.asList(1, 2));

			System.out.println("\nApproach 1: Printing user basic information");
			print(info);

			List<UserBasicInfoOpenProjection> usersBasicInfo = userRepository.findByFirstName("Krishna");
			System.out.println("\nApproach 2: Printing user basic information");
			printBasicInfo(usersBasicInfo);

			info = userRepository.findByFirstName("Krishna", UserBasicInfo.class);
			System.out.println("\nApproach 3: Dynamic projection");
			print(info);
			System.out.println();

			usersBasicInfo = userRepository.findByFirstName("Krishna", UserBasicInfoOpenProjection.class);
			printBasicInfo(usersBasicInfo);

			System.out.println("\nApproach 4: Class projection");
			List<UserBasicDetails> usersBasicDetails = userRepository.getUsersByFirstName("Krishna");
			printBasicDetails(usersBasicDetails);

		};
	}

}