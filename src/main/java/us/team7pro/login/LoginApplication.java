package us.team7pro.login;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.filter.CorsFilter;
import us.team7pro.login.Model.Ticket;
import us.team7pro.login.Repository.TicketRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Collections;
import org.springframework.core.Ordered;

import java.util.stream.Stream;

@EnableResourceServer
@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	// Dummy data for testing
	@Bean
	ApplicationRunner init(TicketRepository repository) {
		return args -> {
			Stream.of("NBA", "FIFA", "UAlbany Great Dane", "Breakfast", "Dinner",
					"Apple Pick", "COD OPS4", "Dream Hack", "Dota2").forEach(name -> {
				Ticket ticket = new Ticket();
				ticket.setName(name);
				repository.save(ticket);
			});
			repository.findAll().forEach(System.out::println);
		};
	}

	// CROS config
    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
