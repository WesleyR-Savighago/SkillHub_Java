package com.apispring.api_spring.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {
    
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private Long nis;

}
