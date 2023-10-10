package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title="Api Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder)
	{
		return builder
				.routes()
				.route(r->r.path("/BLOG-SERVICE/v3/api-docs").and().method(HttpMethod.GET).uri("lb://BLOG-SERVICE"))
				.route(r->r.path("/USER-SERVICE/v3/api-docs").and().method(HttpMethod.GET).uri("lb://USER-SERVICE"))
				.route(r->r.path("blogs/createBlog").and().method(HttpMethod.POST).uri("lb://BLOG-SERVICE"))
				.route(r->r.path("blogs/getBlogs").and().method(HttpMethod.GET).uri("lb://BLOG-SERVICE"))
				.route(r->r.path("blogs/getBlogByUser/{uid}").and().method(HttpMethod.GET).uri("lb://BLOG-SERVICE"))
				.route(r->r.path("blogs/{uid}/deleteABlog/{bid}").and().method(HttpMethod.DELETE).uri("lb://BLOG-SERVICE"))
				.route(r->r.path("blogs/getBlogByUser/{uid}").and().method(HttpMethod.GET).uri("lb://BLOG-SERVICE"))
				.route(r->r.path("blogs/{uid}/editABlog").and().method(HttpMethod.GET).uri("lb://BLOG-SERVICE"))
				.route(r->r.path("/users/createUser").and().method(HttpMethod.POST).uri("lb://USER-SERVICE"))
				.route(r->r.path("/users/allUsers").and().method(HttpMethod.GET).uri("lb://USER-SERVICE"))
				.route(r->r.path("/users/{user-id}/blogs").and().method(HttpMethod.GET).uri("lb://USER-SERVICE"))
				.route(r->r.path("/users/{uid}/delete/{bid}").and().method(HttpMethod.DELETE).uri("lb://USER-SERVICE"))
				.route(r->r.path("/users/{uid}/edit").and().method(HttpMethod.PUT).uri("lb://USER-SERVICE"))
				.build();
				
	}

}
