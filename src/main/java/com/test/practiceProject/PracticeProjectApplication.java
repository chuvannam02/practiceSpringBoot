package com.test.practiceProject;

import com.test.practiceProject.Entity.AppParams;
import com.test.practiceProject.Entity.UserEntity;
import com.test.practiceProject.Repository.AppParamsRepository;
import com.test.practiceProject.Repository.UserRepository;
import com.test.practiceProject.Request.LocalDatasource;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// Bạn phải kích hoạt chức năng Auditing bằng annotation @EnableJpaAuditing
@EnableJpaAuditing
@SpringBootApplication
//@EnableScheduling
@EnableAsync
public class PracticeProjectApplication {

	public static void main(String[] args) {
//		SpringApplication.run(PracticeProjectApplication.class, args);

//		ApplicationContext context =
//		UserRepository userRepository = context.getBean(UserRepository.class);
//
//		// Lấy ra toàn bộ user trong db
//		userRepository.findAll()
//				.forEach(System.out::println);
//
//		// Lưu user xuống database
//		UserEntity user = userRepository.save(new UserEntity());
//		// Khi lưu xong, nó trả về User đã lưu kèm theo Id.
//		System.out.println("User vừa lưu có ID: " + user.getId());
//		Long userId = user.getId();
//		// Cập nhật user.
//		user.setAgi(100);
//		// Update user
//		// Lưu ý, lúc này đối tượng user đã có Id.
//		// Nên nó sẽ update vào đối tượng có Id này
//		// chứ không insert một bản ghi mới
//		userRepository.save(user);
//
//		// Query lấy ra user vừa xong để kiểm tra xem.
//		UserEntity user2 = userRepository.findById(userId).get();
//		System.out.println("User: " + user);
//		System.out.println("User2: " + user2);
//
//		// Xóa User khỏi DB
//		userRepository.delete(user);
//
//		// In ra kiểm tra xem userId còn tồn tại trong DB không
//		UserEntity user3 = userRepository.findById(userId).orElse(null);
//		System.out.println("User3: " + user2);

		SpringApplication application = new SpringApplication(PracticeProjectApplication.class);
		ConfigurableEnvironment environment = new StandardEnvironment();
//        Thay đổi môi trường bằng cách comment và xem kết quả
//        environment.setActiveProfiles("local");
		environment.setActiveProfiles("local");
		application.setEnvironment(environment);
		ApplicationContext context = application.run(args);

		LocalDatasource localDatasource = context.getBean(LocalDatasource.class);
		System.out.println(localDatasource);
	}

//	@Autowired
//	AppParamsRepository appParamsRepository;
//
//	@Bean
//	CommandLineRunner commandLineRunner() {
//		return args -> {
//			AppParams appParams = AppParams.builder()
//					.paramName("Loda")
//					.paramValue("handsome - https://loda.me")
//					.build();
//
//			System.out.println("Đối tượng Param trước khi insert: " + appParams);
//			appParamsRepository.save(appParams);
//			System.out.println("Đối tượng Param sau khi insert: " + appParams);
//
//			System.out.println("In ra tất cả bản ghi trong DB:");
//			System.out.println(appParamsRepository.findAll());
//		};
//	}


	// CommandLineRunner is from Spring Boot framework
	// Executed after the Spring Beans have been loaded and the application context has been created.
	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		// Lambda expression
		// This is a functional interface which has a method run
		// This method will be called when the application is started
		return runner -> {
			System.out.println("Hello Spring Boot 2.4.2");
		};
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(3);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}
}
