package com.example.MyMarket.service;

import com.example.MyMarket.domain.model.Setor;
import com.example.MyMarket.domain.model.StatusEnum;
import com.example.MyMarket.domain.repository.SetorRepository;
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
public class SetorService {
    private final SetorRepository setorRepository;


    @Transactional
    public SetorDTO salvarSetor(SetorDTO setorDTO) throws Exception {
        var setor = Setor.builder()
                .id(setorDTO.getId())
                .nome(setorDTO.getNome())
                .status(setorDTO.getStatus())
                .dataCadastro(LocalDateTime.now())
                .build();
        setorRepository.save(setor);
        return setorDTO;
    }

    @Transactional
    public List<SetorDTO> buscarSetores(Pageable pageable) {
        return setorRepository.findAll(pageable).stream()
                .map(setor -> SetorDTO.builder()
                        .id(setor.getId())
                        .nome(setor.getNome())
                        .status(setor.getStatus())
                        .dataCadastro(setor.getDataCadastro())
                        .build()).collect(Collectors.toList());
    }

    @SneakyThrows
    public SetorDTO findById(Long id){
        return setorRepository.findById(id).map(setor -> SetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .status(setor.getStatus())
                .dataCadastro(setor.getDataCadastro())
                .build()).orElse(null);
    }

    @SneakyThrows
    public void deleteById(Long id) {
        setorRepository.deleteById(id);
    }

    @SneakyThrows
    public List<SetorDTO> buscarSetorAtivo(Pageable pageable){
        return setorRepository.findByAtivo(pageable).stream()
                .map(setor -> SetorDTO.builder()
                        .id(setor.getId())
                        .nome(setor.getNome())
                        .status(setor.getStatus())
                        .dataCadastro(setor.getDataCadastro())
                        .build()).collect(Collectors.toList());
    }

}