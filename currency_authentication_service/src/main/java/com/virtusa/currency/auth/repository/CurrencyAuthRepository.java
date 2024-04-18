package com.virtusa.currency.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.currency.auth.entity.UserCredentialsEntity;


@Repository
public interface CurrencyAuthRepository  extends JpaRepository<UserCredentialsEntity,Long> {
    Optional<UserCredentialsEntity> findByUserName(String userName);
}
