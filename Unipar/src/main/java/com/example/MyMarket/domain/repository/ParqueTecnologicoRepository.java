package com.example.MyMarket.domain.repository;

import com.example.MyMarket.domain.model.ParqueTecnologico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParqueTecnologicoRepository extends JpaRepository<ParqueTecnologico, Long> {
    boolean existsByPatrimonio(Long patrimonio);
}
