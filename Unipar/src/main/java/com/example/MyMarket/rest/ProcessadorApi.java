package com.example.MyMarket.rest;

import com.example.MyMarket.dto.ProcessadorDTO;
import com.example.MyMarket.dto.SetorDTO;
import com.example.MyMarket.service.ProcessadorService;
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
public class ProcessadorApi {
    private final ProcessadorService processadorService;

    @PostMapping(value = "/api/v1/processador", consumes = "application/json", produces = "application/json")
    //@PreAuthorize("hasAuthority('salvar-processador')")
    public ResponseEntity<?> save(@RequestBody ProcessadorDTO processadorDTO) throws Exception {
        if(processadorDTO.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(processadorService.salvarProcessador(processadorDTO));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(processadorService.salvarProcessador(processadorDTO));

    }

    @GetMapping(value = "/api/v1/processador", produces = "application/json")
    //@PreAuthorize("hasAuthority('busca-processador')")
    public ResponseEntity<List<ProcessadorDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(processadorService.buscarProcessador(pageable));
    }

    @GetMapping(value = "/api/v1/processador/{id}", produces = "application/json")
    //@PreAuthorize("hasAuthority('busca-processador-id')")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var processadorDTO = processadorService.findById(id);
        if(processadorDTO == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(processadorDTO);
    }

    @DeleteMapping(value = "/api/v1/processador/{id}")
    //@PreAuthorize("hasAuthority('deleta-processador-id')")
    public ResponseEntity <?> delete(@PathVariable Long id) {
        processadorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/api/v1/processador/status", produces = "application/json")
    //@PreAuthorize("hasAuthority('busca-processador-ativo')")
    public ResponseEntity<List<ProcessadorDTO>> findbyAtivo(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(processadorService.buscarProcessadorAtivo(pageable));
    }
}
