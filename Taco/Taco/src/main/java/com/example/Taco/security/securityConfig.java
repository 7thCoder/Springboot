package com.example.Taco.security;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;

// import java.util.List;
// import java.util.ArrayList;
// import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Taco.Repository.IngredientRepository;
import com.example.Taco.Repository.tacoRepository;
import com.example.Taco.Repository.userRepository;
import com.example.Taco.taco.Ingredient;
import com.example.Taco.taco.Taco;
import com.example.Taco.taco.Ingredient.Type;
import com.example.Taco.User;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder){
    //     List<UserDetails> userList = new ArrayList<>();
    //     userList.add(new User("Buzz", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));

    //     return new InMemoryUserDetailsManager(userList);
    // }

    @Bean
    public UserDetailsService userDetailsService(userRepository userRepo){
        return username -> {
            User user = userRepo.findByUsername(username);
        if (user != null) return user;
        throw new UsernameNotFoundException("User ," + username + ", not found");
        };
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    //     return http
    //     .authorizeRequests()
    //     .antMatchers("/design", "/orders")
    //     .access("hasRole('USER')")
    //     .antMatchers("/", "/**").access("permitAll()")
    //     .and()
    //     .formLogin()
    //     .loginPage("/login")
    //     .defaultSuccessUrl("/design")
    //     .and()
    //     .logout()
    //     .and()
    //     .build();
    // }    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
		.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/design", "/orders").hasRole("USER")
			.requestMatchers("/","/**").permitAll()
            .requestMatchers(HttpMethod.POST,"/api/ingredients").hasAnyAuthority("SCOPE_writeIngredients")
            .requestMatchers(HttpMethod.DELETE,"/api/ingredients").hasAnyAuthority("SCOPE_deleteIngredients")
		)
        .oauth2ResourceServer((oauth2)->oauth2.jwt(Customizer.withDefaults()))
       .formLogin(formLogin ->formLogin.loginPage("/login").permitAll()
        .defaultSuccessUrl("/design")
       )
        .logout(lOut -> {lOut.invalidateHttpSession(true)
                .clearAuthentication(true).logoutSuccessUrl("/login?logout")
                .permitAll();
        })
        .build();
    }

    

    // @Bean
    // public CommandLineRunner dataLoader( IngredientRepository repo, userRepository userRepo,  PasswordEncoder encoder,  tacoRepository tacoRepo) {
    // return args -> {
    //         Ingredient flourTortilla = new Ingredient(
    //         "FLTO", "Flour Tortilla", Type.WRAP);
    //         Ingredient cornTortilla = new Ingredient(
    //         "COTO", "Corn Tortilla", Type.WRAP);
    //         Ingredient groundBeef = new Ingredient(
    //         "GRBF", "Ground Beef", Type.PROTEIN);
    //         Ingredient carnitas = new Ingredient(
    //         "CARN", "Carnitas", Type.PROTEIN);
    //         Ingredient tomatoes = new Ingredient(
    //         "TMTO", "Diced Tomatoes", Type.VEGGIES);
    //         Ingredient lettuce = new Ingredient(
    //         "LETC", "Lettuce", Type.VEGGIES);
    //         Ingredient cheddar = new Ingredient(
    //         "CHED", "Cheddar", Type.CHEESE);
    //         Ingredient jack = new Ingredient(
    //         "JACK", "Monterrey Jack", Type.CHEESE);
    //         Ingredient salsa = new Ingredient(
    //         "SLSA", "Salsa", Type.SAUCE);
    //         Ingredient sourCream = new Ingredient(
    //         "SRCR", "Sour Cream", Type.SAUCE);
    //         repo.save(flourTortilla);
    //         repo.save(cornTortilla);
    //         repo.save(groundBeef);
    //         repo.save(carnitas);
    //         repo.save(tomatoes);

    //         repo.save(lettuce);
    //         repo.save(cheddar);
    //         repo.save(jack);
    //         repo.save(salsa);
    //         repo.save(sourCream);
            
    //         Taco taco1 = new Taco();
    //         taco1.setName("Carnivore");
    //         taco1.setIngredients(Arrays.asList(
    //         flourTortilla, groundBeef, carnitas, 
    //         sourCream, salsa, cheddar));
    //         tacoRepo.save(taco1);

    //         Taco taco2 = new Taco();
    //         taco2.setName("Bovine Bounty");
    //         taco2.setIngredients(Arrays.asList(
    //         cornTortilla, groundBeef, cheddar, 
    //         jack, sourCream));
    //         tacoRepo.save(taco2);

    //         Taco taco3 = new Taco();
    //         taco3.setName("Veg-Out");
    //         taco3.setIngredients(Arrays.asList(
    //         flourTortilla, cornTortilla, tomatoes, 
    //         lettuce, salsa));
    //         tacoRepo.save(taco3);
    //     };
    // }

}
