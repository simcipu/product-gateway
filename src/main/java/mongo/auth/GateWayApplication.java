package mongo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class GateWayApplication {


	@Bean
	public RouteLocator ProductLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/api/v1/**").uri("http://localhost:8080/api/v1"))
				.build();

	}


	@Bean
	public RouteLocator UserLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/auth/**").uri("http://localhost:8081/auth"))
				.build();

	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}
}
