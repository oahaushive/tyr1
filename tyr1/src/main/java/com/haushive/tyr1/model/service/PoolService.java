package com.haushive.tyr1.model.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import javax.transaction.Transactional;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haushive.tyr1.model.domain.Currency;
import com.haushive.tyr1.model.domain.Pool;
import com.haushive.tyr1.model.domain.PoolDetail;
import com.haushive.tyr1.model.repository.PoolDetailRepository;
import com.haushive.tyr1.model.repository.PoolRepository;
import com.haushive.tyr1.web.controller.HelloWorldRestController;

@Service
public class PoolService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HelloWorldRestController controller;
	
	@Autowired 
	private PoolRepository poolRepository;
	
	@Autowired
	private PoolDetailRepository poolDetailRepository;
	
	public Pool insertNewPool(List<PoolDetail> details) throws IOException{
		PoolDetail detail = details.get(0);
		Pool pool = new Pool();
		pool.setPoolId(detail.getPoolId());
		pool.setChangeUsername(detail.getChangeUsername());
		poolRepository.save(pool);
		poolDetailRepository.saveAll(details);
		pool = findPoolWithDetails(detail.getPoolId());
		return pool;
	}
	
	public Pool updatePool(List<PoolDetail> details, String poolId) throws IOException{
		Pool poolToUpdate = poolRepository.findByPoolId(poolId);
		details.stream().forEach(detail -> {
			updateOrInsertDetail(detail);
		});
		poolToUpdate = findPoolWithDetails(poolId);
		return poolToUpdate;
	}
	
	@Transactional
	public Pool deletePoolWithDetails(String poolId) {
		Pool pool = poolRepository.findByPoolId(poolId);
		poolRepository.deleteById(poolId);
		poolDetailRepository.deleteByPoolId(poolId);
		return pool;
	}
	
	@Transactional
	public Pool deletePoolDetail(String poolId, String currencySymbol) throws IOException {
		poolDetailRepository.deleteByPoolIdAndCurrencySymbol(poolId, currencySymbol);
		Pool pool = findPoolWithDetails(poolId);
		return pool;
	}
	
	public List<Pool> getPools(){
		List<Pool> poolsToReturn = poolRepository.findAll();
		poolsToReturn.stream().forEach(pool -> {
			try {
			pool.setDetails(poolDetailRepository.findByPoolId(pool.getPoolId()));
			String currencies = fetchListOfCryptosInPool(pool.getPoolId());
			setCurrencyForPoolDetail(pool.getDetails(), currencies);
			System.out.println("pool: " + pool.getPoolId() + " " + LocalDateTime.now());
			setPoolDetailTotals(pool.getDetails());
			setPoolTotal(pool);
			Thread.sleep(1000);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return poolsToReturn;
	}
	
	public Pool findPoolWithDetails(String poolId) throws IOException {
		Pool pool = poolRepository.findByPoolId(poolId);
		if(!poolDetailRepository.findByPoolId(poolId).isEmpty()) {
			pool.setDetails(poolDetailRepository.findByPoolId(poolId));
			String currencies = fetchListOfCryptosInPool(poolId);
			setCurrencyForPoolDetail(pool.getDetails(), currencies);
			setPoolDetailTotals(pool.getDetails());
			setPoolTotal(pool);
		}
		return pool;
	}
	
	
	public String fetchListOfCryptosInPool(String poolId) {
		List<PoolDetail> details = poolDetailRepository.findByPoolId(poolId);
		List<String> symbolsList = new ArrayList<>();
		details.stream().forEach(i -> {
			symbolsList.add(i.getCurrencySymbol());
		});
		String commaSeparatedList = StringUtils.join(symbolsList);
		return commaSeparatedList;
 	}
	
	public void setCurrencyForPoolDetail(List<PoolDetail> details, String currencies) throws IOException {
		Currency[] currencyArray = getCurrencies(currencies);
		for (PoolDetail detail : details) {
			for (Currency currency : currencyArray) {
				if (detail.getCurrencySymbol().equals(currency.getId())) {
					detail.setCurrency(currency);
				}
			}
		}
	}
	
	public void updateOrInsertDetail(PoolDetail detailToUpdate) {
		Optional<PoolDetail> currentDetail = poolDetailRepository.findByPoolIdAndCurrencySymbol(detailToUpdate.getPoolId(), detailToUpdate.getCurrencySymbol());
		if(!currentDetail.isEmpty()) {
			detailToUpdate.setId(currentDetail.get().getId());
			poolDetailRepository.save(detailToUpdate);
		} else {
			poolDetailRepository.save(detailToUpdate);
		}
	}
	
	public Currency[] getCurrencies(String currencies) throws IOException {
		Currency[] currencyArray = controller.getCurrency(currencies);
		return currencyArray;
	}
	
	public void setPoolDetailTotals(List<PoolDetail> details) {
		details.stream().forEach(detail -> {
			if (detail.getCurrency() == null) {
				detail.setCurrencyTotal(0);
			} else {
				double totalCurrency = detail.getVolume() * Double.valueOf(detail.getCurrency().getPrice());
				detail.setCurrencyTotal(totalCurrency);
			}
		});
	}
	
	public void setPoolTotal(Pool pool) {
		List<PoolDetail> details = pool.getDetails();
		List<Double> listToAdd = new ArrayList<>();
		details.stream().forEach(detail -> {
			listToAdd.add(detail.getCurrencyTotal());
		});
		double poolTotal = 0;
		for(Double currency : listToAdd) {
			poolTotal += currency; 
		}
		pool.setPoolTotal(poolTotal);
	}

}
