package com.example.MyMarket.dto;

import com.example.MyMarket.domain.model.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessadorDTO {
    private Long id;
    private String nome;
    private String status;
    private LocalDateTime dataCadastro;
}
