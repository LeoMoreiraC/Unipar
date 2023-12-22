package com.example.MyMarket.domain.repository;

import com.example.MyMarket.domain.model.Setor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    @Query(value = "SELECT * FROM Setor WHERE status IN(\"ATIVO\")",
            nativeQuery = true)
    List<Setor> findByAtivo(Pageable pageable);
}
