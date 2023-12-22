package com.example.MyMarket.domain.repository;

import com.example.MyMarket.domain.model.Processador;
import com.example.MyMarket.domain.model.Setor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessadorRepository extends JpaRepository<Processador, Long> {
    @Query(value = "SELECT * FROM Processador WHERE status IN(\"ATIVO\")",
            nativeQuery = true)
    List<Setor> findByAtivo(Pageable pageable);
}
