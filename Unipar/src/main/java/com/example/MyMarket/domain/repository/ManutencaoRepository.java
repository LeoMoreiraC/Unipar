package com.example.MyMarket.domain.repository;

import com.example.MyMarket.domain.model.Manutencao;
import com.example.MyMarket.domain.model.Processador;
import com.example.MyMarket.domain.model.Setor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
  @Query(value = "SELECT * FROM Manutencao WHERE patrimobnio IN(:patrimonio)",
          nativeQuery = true)
  List<Manutencao> findByPatrimonio(Long patrimonio);
}
