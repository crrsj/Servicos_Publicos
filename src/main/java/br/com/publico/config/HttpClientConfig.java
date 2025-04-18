package br.com.publico.config;

import java.net.http.HttpClient;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {
	
	 @Bean
	    public HttpClient httpClient() {
	        return HttpClient.newBuilder()
	                .connectTimeout(Duration.ofSeconds(10)) // Timeout opcional
	                .followRedirects(HttpClient.Redirect.NORMAL) // Segue redirecionamentos HTTP
	                .build();
	    }

}
