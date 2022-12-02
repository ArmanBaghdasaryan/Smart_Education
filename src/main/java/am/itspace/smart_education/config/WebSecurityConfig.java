package am.itspace.smart_education.config;

import am.itspace.smart_education.common.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .loginPage("/")
                .failureUrl("/?error=true")
                .defaultSuccessUrl("/loginSuccess")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/*").hasAuthority(Role.ADMIN.name())
                .antMatchers("/user/register").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/loginPage");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }
}