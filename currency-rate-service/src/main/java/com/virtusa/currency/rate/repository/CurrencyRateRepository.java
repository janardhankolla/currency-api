package com.virtusa.currency.rate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.currency.rate.entity.CurrencyRateEntity;


@Repository
public  interface CurrencyRateRepository extends JpaRepository<CurrencyRateEntity,Long> {
	 Optional<CurrencyRateEntity> findOneByCurrencyCode(String code);
}
