package com.example.MyMarket.rest;

import com.example.MyMarket.dto.ManutencaoDTO;
import com.example.MyMarket.dto.SetorDTO;
import com.example.MyMarket.service.ManutencaoService;
import com.example.MyMarket.service.SetorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class ManutencaoApi {
    private final ManutencaoService manutencaoService;

    @PostMapping(value = "/api/v1/manutencao", consumes = "application/json", produces = "application/json")
    //@PreAuthorize("hasAuthority('salvar-manutencao')")
    public ResponseEntity<?> save(@RequestBody ManutencaoDTO manutencaoDTO) throws Exception {
        if(manutencaoDTO.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(manutencaoService.salvarManutencao(manutencaoDTO));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(manutencaoService.salvarManutencao(manutencaoDTO));

    }

    @GetMapping(value = "/api/v1/manutencao", produces = "application/json")
    //@PreAuthorize("hasAuthority('busca-setores')")
    public ResponseEntity<List<ManutencaoDTO>> findAll(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(manutencaoService.buscarManutacao(pageable));
    }

    @GetMapping(value = "/api/v1/manutencao/{patrimonio}", produces = "application/json")
    //@PreAuthorize("hasAuthority('busca-setores-id')")
    public ResponseEntity<List<ManutencaoDTO>> findByPatrimonio(@PathVariable Long patrimonio){
        var manutencaoDTO = manutencaoService.buscarPatrimonio(patrimonio);
        if(manutencaoDTO == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(manutencaoDTO);
    }
}
