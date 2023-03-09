package blogapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    @Autowired
    DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                    .authorizeRequests()
                    .antMatchers("/sign_in_page/**","/registration/**","/activate/*").permitAll()
                    .antMatchers("/users").hasAuthority("ADMIN")
                    .antMatchers("/**").fullyAuthenticated()
                .and()
                    .formLogin()
                    .loginPage("/sign-in-page")
                    .successForwardUrl("/")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/", true).permitAll()
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/sign-in-page")
                .and()
                    .cors()
                    .and()
                    .csrf()
                    .disable();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return(web) -> web.ignoring()
                .antMatchers(
                        "/css/**", "/fonts/**", "/vendor/**", "/js/**", "/img/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select email,password,active from users where email=?")
                .authoritiesByUsernameQuery("select users.email,role.roles from users inner join role on users.id=role.id where users.email=?");
    }
}
