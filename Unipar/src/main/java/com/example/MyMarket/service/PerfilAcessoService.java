package com.example.MyMarket.service;

import com.example.MyMarket.domain.model.PerfilAcesso;
import com.example.MyMarket.domain.model.User;
import com.example.MyMarket.domain.repository.PerfilAcessoRepository;
import com.example.MyMarket.domain.repository.UserRepository;
import com.example.MyMarket.dto.PerfilAcessoDTO;
import com.example.MyMarket.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfilAcessoService {
    private final PerfilAcessoRepository perfilAcessoRepository;


    public PerfilAcessoDTO salvarPerfilAcesso(PerfilAcessoDTO perfilAcessoDTO){
        var perfilAcesso = PerfilAcesso.builder()
                .id(perfilAcessoDTO.getId())
                .nomePerfil(perfilAcessoDTO.getNomePerfil())
                .build();
        perfilAcessoRepository.save(perfilAcesso);
        return PerfilAcessoDTO.builder()
                .id(perfilAcesso.getId())
                .nomePerfil(perfilAcesso.getNomePerfil())
                .build();
    }

    public List<PerfilAcessoDTO> buscarTodos(Pageable pageable){
        return perfilAcessoRepository.findAll(pageable).stream()
                .map(perfilAcesso -> PerfilAcessoDTO.builder()
                        .id(perfilAcesso.getId())
                        .nomePerfil(perfilAcesso.getNomePerfil())
                        .build())
                        .collect(Collectors.toList());
    }
    public PerfilAcessoDTO buscarById(Long id){
        return perfilAcessoRepository.findById(id).map(perfilAcesso -> PerfilAcessoDTO.builder()
                .id(perfilAcesso.getId())
                .nomePerfil(perfilAcesso.getNomePerfil())
                .build()).orElse(null);
    }
}
