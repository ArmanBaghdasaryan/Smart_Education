package am.itspace.smart_education_rest;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAsync
@RequiredArgsConstructor
@SpringBootApplication
@ComponentScan(basePackages = {"am.itspace.smart_education_rest.*", "am.itspace.smart_education_common.*"})
@EntityScan(basePackages = {"am.itspace.smart_education_common.*"})
@EnableJpaRepositories(basePackages = "am.itspace.smart_education_common.*")
public class SmartEducationRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartEducationRestApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
