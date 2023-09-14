package com.haushive.tyr1.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haushive.tyr1.model.domain.Newsletter;
import com.haushive.tyr1.model.repository.NewsletterRepository;

@Service
public class NewsletterService {
	
	@Autowired
	private NewsletterRepository newsletterRepository;
	
	public Newsletter saveNewsletter(Newsletter newsletter) {
		newsletterRepository.save(newsletter);
		return newsletter;
	}
	
	public Newsletter getNewsletter() {
		List<Newsletter> newsletters = newsletterRepository.findAll();
		return newsletters.get(0);
	}

}
