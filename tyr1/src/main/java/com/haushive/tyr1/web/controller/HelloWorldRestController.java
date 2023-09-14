package com.haushive.tyr1.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haushive.tyr1.model.domain.Currency;
import com.haushive.tyr1.model.domain.PoolDetail;
import com.haushive.tyr1.model.service.CryptoService;

@RestController
public class HelloWorldRestController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired 
	private CryptoService cryptoService;
	
	@RequestMapping("/api/rest/hello")
	public Map<String, Object> hello(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("now", LocalDateTime.now());
		map.put("url", request.getRequestURL());
		return map;
	}
	
	@GetMapping(value="/cryptocurrency", produces = MediaType.APPLICATION_JSON_VALUE)
	public Currency[] getCurrency(String symbols) throws IOException {
		String apiKey = "cd17b83624f5e25fd3e0ec119953729b8227d0a8";
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);  
		HttpEntity request = new HttpEntity(headers);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
		messageConverters.add(map);
		messageConverters.add(new FormHttpMessageConverter());
		template.setMessageConverters(messageConverters);
		Currency[] currencies = template.postForObject("https://api.nomics.com/v1/currencies/ticker?key=" + apiKey + "&ids=" + symbols, request, Currency[].class);
//		Currency currency = currencies[0];
		return currencies; 
	}

}
