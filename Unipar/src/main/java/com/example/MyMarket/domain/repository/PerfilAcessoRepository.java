package com.example.MyMarket.domain.repository;

import com.example.MyMarket.domain.model.PerfilAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long> {
    Optional<PerfilAcesso> findByNomePerfil(String nomePerfil);
}
