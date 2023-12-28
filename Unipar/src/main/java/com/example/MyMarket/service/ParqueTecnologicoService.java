package com.example.MyMarket.service;

import com.example.MyMarket.domain.model.ParqueTecnologico;
import com.example.MyMarket.domain.model.Processador;
import com.example.MyMarket.domain.model.Setor;
import com.example.MyMarket.domain.model.StatusEnum;
import com.example.MyMarket.domain.repository.ParqueTecnologicoRepository;
import com.example.MyMarket.dto.ParqueTecnologicoDTO;
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
public class ParqueTecnologicoService {
    private final ParqueTecnologicoRepository parqueTecnologicoRepository;

    public boolean existePatrimonio(Long patrimonio) {
        // Verifica se o patrimônio já existe no banco de dados
        return parqueTecnologicoRepository.existsByPatrimonio(patrimonio);
    }

    @Transactional
    public ParqueTecnologicoDTO salvarParqueTecnologico(ParqueTecnologicoDTO parqueTecnologicoDTO) throws Exception {

        var parqueTecnologico = ParqueTecnologico.builder()
                .id(parqueTecnologicoDTO.getId())
                .patrimonio(parqueTecnologicoDTO.getPatrimonio())
                .processador(parqueTecnologicoDTO.getProcessador())
                .geracao(parqueTecnologicoDTO.getGeracao())
                .setor(parqueTecnologicoDTO.getSetor())
                .ram(parqueTecnologicoDTO.getRam())
                .hd(parqueTecnologicoDTO.getHd())
                .status(StatusEnum.ATIVO)
                .dataAlteracao(LocalDateTime.now())
                .build();
        parqueTecnologicoRepository.save(parqueTecnologico);
        return parqueTecnologicoDTO;
    }

    @Transactional
    public List<ParqueTecnologicoDTO> buscarTodosComputadores(Pageable pageable) {
        return parqueTecnologicoRepository.findAll(pageable).stream()
                .map(parqueTecnologico -> ParqueTecnologicoDTO.builder()
                        .patrimonio(parqueTecnologico.getPatrimonio())
                        .processador(parqueTecnologico.getProcessador())
                        .geracao(parqueTecnologico.getGeracao())
                        .setor(parqueTecnologico.getSetor())
                        .ram(parqueTecnologico.getRam())
                        .hd(parqueTecnologico.getHd())
                        .status(parqueTecnologico.getStatus())
                        .dataAlteracao(parqueTecnologico.getDataAlteracao())
                        .build()).collect(Collectors.toList());
    }

    @SneakyThrows
    public ParqueTecnologicoDTO findById(Long id){
        return parqueTecnologicoRepository.findById(id).map(parqueTecnologico -> ParqueTecnologicoDTO.builder()
                .patrimonio(parqueTecnologico.getPatrimonio())
                .processador(parqueTecnologico.getProcessador())
                .geracao(parqueTecnologico.getGeracao())
                .setor(parqueTecnologico.getSetor())
                .ram(parqueTecnologico.getRam())
                .hd(parqueTecnologico.getHd())
                .status(parqueTecnologico.getStatus())
                .dataAlteracao(parqueTecnologico.getDataAlteracao())
                .build()).orElse(null);
    }
}