package com.example.MyMarket.rest;

import com.example.MyMarket.dto.SetorDTO;
import com.example.MyMarket.dto.UserDTO;
import com.example.MyMarket.service.SetorService;
import com.example.MyMarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class SetorApi {
    private final SetorService setorService;

    @PostMapping(value = "/api/v1/setor", consumes = "application/json", produces = "application/json")
    //@PreAuthorize("hasAuthority('salvar-setor')")
    public ResponseEntity<?> save(@RequestBody SetorDTO setorDTO) throws Exception {
        if(setorDTO.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(setorService.salvarSetor(setorDTO));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(setorService.salvarSetor(setorDTO));

    }

    @GetMapping(value = "/api/v1/setor", produces = "application/json")
    //@PreAuthorize("hasAuthority('busca-setores')")
    public ResponseEntity<List<SetorDTO>> findAll(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(setorService.buscarSetores(pageable));
    }

    @GetMapping(value = "/api/v1/setor/{id}", produces = "application/json")
    //@PreAuthorize("hasAuthority('busca-setores-id')")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var setorDTO = setorService.findById(id);
        if(setorDTO == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(setorDTO);
    }

    @DeleteMapping(value = "/api/v1/setor/{id}")
    //@PreAuthorize("hasAuthority('deleta-setores-id')")
    public ResponseEntity <?> delete(@PathVariable Long id) {
        setorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/api/v1/setor/status", produces = "application/json")
    //@PreAuthorize("hasAuthority('consulta-item-joias')")
    public ResponseEntity<List<SetorDTO>> findbyAtivo(@PageableDefault(page = 0, size = 9999, sort = { "nome", "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(setorService.buscarSetorAtivo(pageable));
    }
}
