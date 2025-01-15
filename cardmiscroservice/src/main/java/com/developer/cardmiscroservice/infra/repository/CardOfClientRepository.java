package com.developer.cardmiscroservice.infra.repository;

import com.developer.cardmiscroservice.domain.CardOfClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardOfClientRepository extends JpaRepository<CardOfClient, Long> {
    List<CardOfClient> findByCpf(String cpf);
}
