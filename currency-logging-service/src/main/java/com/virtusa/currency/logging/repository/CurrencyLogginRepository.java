package com.virtusa.currency.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.currency.logging.entitiy.CurrencyDetailsEntity;
import java.util.List;


@Repository
public interface CurrencyLogginRepository extends JpaRepository<CurrencyDetailsEntity, Long>{
	List<CurrencyDetailsEntity> findByUserName(String userName);
}
