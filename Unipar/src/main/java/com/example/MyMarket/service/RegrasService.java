package com.example.MyMarket.service;

import com.example.MyMarket.domain.model.Regras;
import com.example.MyMarket.domain.repository.RegrasRepository;
import com.example.MyMarket.dto.RegrasDTO;
import com.example.MyMarket.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegrasService {
    private final RegrasRepository regrasRepository;


    public RegrasDTO salvarRegra(RegrasDTO regrasDTO){
        var regras = Regras.builder()
                .id(regrasDTO.getId())
                .nomeRegra(regrasDTO.getNomeRegra())
                .build();
        regrasRepository.save(regras);
        return RegrasDTO.builder()
                .id(regras.getId())
                .nomeRegra(regras.getNomeRegra())
                .build();
    }

    public List<RegrasDTO> buscarTodos(Pageable pageable){
        return regrasRepository.findAll(pageable).stream()
                .map(regras -> RegrasDTO.builder()
                        .id(regras.getId())
                        .nomeRegra(regras.getNomeRegra())
                        .build())
                        .collect(Collectors.toList());
    }
    public RegrasDTO buscarById(Long id){
        return regrasRepository.findById(id).map(regras -> RegrasDTO.builder()
                .id(regras.getId())
                .nomeRegra(regras.getNomeRegra())
                .build()).orElse(null);
    }
}
