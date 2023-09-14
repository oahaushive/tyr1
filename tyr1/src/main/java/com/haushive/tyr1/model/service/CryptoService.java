package com.haushive.tyr1.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haushive.tyr1.model.domain.Crypto;
import com.haushive.tyr1.model.repository.CryptoRepository;

@Service
public class CryptoService {
	
	@Autowired
	private CryptoRepository cryptoRepository;
	
	public String fetchListOfCryptos() {
		List<Crypto> cryptoList = cryptoRepository.findAll();
		List<String> symbolsList = new ArrayList<>();
		cryptoList.stream().forEach(i -> {
			symbolsList.add(i.getSymbol());
		});
		String commaSeparatedList = StringUtils.join(symbolsList);
		return commaSeparatedList;
 	}
	
	public void insertNewCrypto(List<Crypto> cryptosToInsert) {
		cryptoRepository.saveAll(cryptosToInsert);
	}

}
