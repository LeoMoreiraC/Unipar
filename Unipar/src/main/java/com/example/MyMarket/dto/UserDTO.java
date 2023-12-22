package com.example.MyMarket.dto;

import com.example.MyMarket.domain.model.PerfilAcesso;
import com.example.MyMarket.domain.model.Regras;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String senha;
    private String email;
    private String login;
}
