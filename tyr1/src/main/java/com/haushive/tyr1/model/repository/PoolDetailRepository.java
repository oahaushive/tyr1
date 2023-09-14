package com.haushive.tyr1.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.tyr1.model.domain.PoolDetail;

@Repository
public interface PoolDetailRepository extends CrudRepository<PoolDetail, String>{
	
	public List<PoolDetail> findByPoolId(String poolId);
	
	public Optional<PoolDetail> findByPoolIdAndCurrencySymbol(String poolId, String currencySymbol);
	
	public void deleteByPoolId(String poolId);
	
	public void deleteByPoolIdAndCurrencySymbol(String poolId, String currencySymbol);

}
