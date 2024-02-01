package com.ait.service;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ait.binding.QuoteApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DashboardServiceImpl implements IDashboardService {
	
	private String quoteUrl="https://type.fit/api/quotes";
	
	private QuoteApiResponse[] quotes=null;

	@Override
	public String getQuote() {
		
		if(quotes==null) {
			RestTemplate rt=new RestTemplate();
			ResponseEntity<String> forEntity = 
												rt.getForEntity(quoteUrl, String.class);
			String body = forEntity.getBody();
			ObjectMapper om=new ObjectMapper();
			try {
				quotes=om.readValue(body, QuoteApiResponse[].class);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		Random random=new Random();
		int nextInt = random.nextInt(quotes.length-1);
		return quotes[nextInt].getText();
	}

}
