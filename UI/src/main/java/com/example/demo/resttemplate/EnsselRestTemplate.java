package com.example.demo.resttemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class EnsselRestTemplate {
	@Bean
	public RestTemplate resttemplate() {
		RestTemplate rtf = new RestTemplate();
		rtf.setUriTemplateHandler(
				new DefaultUriBuilderFactory(UriComponentsBuilder.fromHttpUrl("http://localhost:12400")));

		return rtf;
	}
}
