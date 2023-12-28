package com.example.MyMarket.service;

import com.example.MyMarket.domain.model.Manutencao;
import com.example.MyMarket.domain.repository.ManutencaoRepository;
import com.example.MyMarket.dto.ManutencaoDTO;
import com.example.MyMarket.dto.SetorDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManutencaoService {
    private final ManutencaoRepository manutencaoRepository;


    @Transactional
    public ManutencaoDTO salvarManutencao(ManutencaoDTO manutencaoDTO) throws Exception {
        var patrimonio = manutencaoDTO.getPatrimonio();
        var manutencao = Manutencao.builder()
                .id(manutencaoDTO.getId())
                .descricao(manutencaoDTO.getDescricao())
                .dataCadastro(LocalDateTime.now())
                .status(manutencaoDTO.getStatus())
                .patrimonio(manutencaoDTO.getPatrimonio())
                .build();
        manutencaoRepository.save(manutencao);
        return manutencaoDTO;
    }

    @Transactional
    public List<ManutencaoDTO> buscarManutacao(Pageable pageable) {
        return manutencaoRepository.findAll(pageable).stream()
                .map(manutencao -> ManutencaoDTO.builder()
                        .id(manutencao.getId())
                        .descricao(manutencao.getDescricao())
                        .status(manutencao.getStatus())
                        .dataCadastro(manutencao.getDataCadastro())
                        .patrimonio(manutencao.getPatrimonio())
                        .build()).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<ManutencaoDTO> buscarPatrimonio(Long patrimonio){
        return manutencaoRepository.findByPatrimonio(patrimonio).stream()
                .map(manutencao -> ManutencaoDTO.builder()
                        .id(manutencao.getId())
                        .descricao(manutencao.getDescricao())
                        .status(manutencao.getStatus())
                        .dataCadastro(manutencao.getDataCadastro())
                        .patrimonio(manutencao.getPatrimonio())
                        .build()).collect(Collectors.toList());
    }

}