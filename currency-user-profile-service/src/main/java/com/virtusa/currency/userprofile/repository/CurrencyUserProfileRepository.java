package com.virtusa.currency.userprofile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.currency.userprofile.entity.CurrencyRateDetailsEntity;

@Repository
public interface CurrencyUserProfileRepository extends JpaRepository<CurrencyRateDetailsEntity, Long> {

	public List<CurrencyRateDetailsEntity> findByUserName(String userName);
}
