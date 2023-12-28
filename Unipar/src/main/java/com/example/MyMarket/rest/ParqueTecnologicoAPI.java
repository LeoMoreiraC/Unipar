package com.example.MyMarket.rest;

import com.example.MyMarket.dto.ParqueTecnologicoDTO;
import com.example.MyMarket.dto.SetorDTO;
import com.example.MyMarket.service.ParqueTecnologicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class ParqueTecnologicoAPI {
    private final ParqueTecnologicoService parqueTecnologicoService;

    @GetMapping(value = "/api/v1/parqueTecnologico", produces = "application/json")
    public ResponseEntity<List<ParqueTecnologicoDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(parqueTecnologicoService.buscarTodosComputadores(pageable));
    }

    //cadastra os produtos
    @PostMapping(value = "/api/v1/parqueTecnologico", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> cadastrar(@RequestBody ParqueTecnologicoDTO parqueTecnologicoDTO) throws Exception {
        if (parqueTecnologicoDTO.getPatrimonio() != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(parqueTecnologicoService.salvarParqueTecnologico(parqueTecnologicoDTO));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parqueTecnologicoService.salvarParqueTecnologico(parqueTecnologicoDTO));
    }

//    @PostMapping(value = "/api/v1/parqueTecnologico", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<?> cadastrar(@RequestBody ParqueTecnologicoDTO parqueTecnologicoDTO) throws Exception {
//        if (parqueTecnologicoService.existePatrimonio(parqueTecnologicoDTO.getPatrimonio())) {
//            return ResponseEntity.badRequest().body("O patrimônio já existe. Não é possível cadastrar novamente.");
//        }
//        return ResponseEntity.ok(parqueTecnologicoService.salvarParqueTecnologico(parqueTecnologicoDTO));
//    }
//
//    // deleta produtos por codigo
//    @DeleteMapping(value = "/api/v1/produto", consumes = "application/json", produces = "application/json")
//    public ResponseEntity <?> delete(@PathVariable String codigo) {
//        parqueTecnologicoService.deleteCodigo(codigo);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
