package com.apispring.api_spring.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Funcionario extends Pessoa{

    private double salario;
    private String dataRegistro;
    private boolean ativo;

    
}