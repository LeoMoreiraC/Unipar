package com.example.MyMarket.service;

import com.example.MyMarket.domain.model.Processador;
import com.example.MyMarket.domain.model.StatusEnum;
import com.example.MyMarket.domain.repository.ProcessadorRepository;
import com.example.MyMarket.domain.repository.SetorRepository;
import com.example.MyMarket.dto.ProcessadorDTO;
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
public class ProcessadorService {
    private final ProcessadorRepository processadorRepository;

    @Transactional
    public ProcessadorDTO salvarProcessador(ProcessadorDTO processadorDTO) throws Exception {
        var processador = Processador.builder()
                .id(processadorDTO.getId())
                .nome(processadorDTO.getNome())
                .status(processadorDTO.getStatus())
                .dataCadastro(LocalDateTime.now())
                .build();
        processadorRepository.save(processador);
        return processadorDTO;
    }

    @Transactional
    public List<ProcessadorDTO> buscarProcessador(Pageable pageable) {
        return processadorRepository.findAll(pageable).stream()
                .map(processador -> ProcessadorDTO.builder()
                        .id(processador.getId())
                        .nome(processador.getNome())
                        .status(processador.getStatus())
                        .dataCadastro(processador.getDataCadastro())
                        .build()).collect(Collectors.toList());
    }

    @SneakyThrows
    public ProcessadorDTO findById(Long id){
        return processadorRepository.findById(id).map(processador -> ProcessadorDTO.builder()
                .id(processador.getId())
                .nome(processador.getNome())
                .status(processador.getStatus())
                .dataCadastro(processador.getDataCadastro())
                .build()).orElse(null);
    }

    @SneakyThrows
    public void deleteById(Long id) {
        processadorRepository.deleteById(id);
    }

    @SneakyThrows
    public List<ProcessadorDTO> buscarProcessadorAtivo(Pageable pageable){
        return processadorRepository.findByAtivo(pageable).stream()
                .map(processador -> ProcessadorDTO.builder()
                        .id(processador.getId())
                        .nome(processador.getNome())
                        .status(processador.getStatus())
                        .dataCadastro(processador.getDataCadastro())
                        .build()).collect(Collectors.toList());
    }
}