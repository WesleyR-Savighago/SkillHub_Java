package com.apispring.api_spring.Controller;

import java.util.List;

import com.apispring.api_spring.Model.Funcionario;

public interface FuncionarioController {
   
    public String inserirFuncionario(Funcionario f) throws Exception;

    public Funcionario buscaFuncionario(int id) throws Exception;

    public String atualizarFuncionario(Funcionario funcionario) throws Exception;

    public String deleteFuncionario(int id) throws Exception;

    public List<Funcionario> listarFunionarios() throws Exception;
}
