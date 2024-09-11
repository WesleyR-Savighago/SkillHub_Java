package com.apispring.api_spring.Controller.Implements;

import java.util.List;

import com.apispring.api_spring.Controller.FuncionarioController;
import com.apispring.api_spring.Model.Funcionario;
import com.apispring.api_spring.Repositories.funcionarioRepositorie;

public class FuncionarioControllerImplements implements FuncionarioController {
    @Override
    public String inserirFuncionario(Funcionario f) {
        try {
            return funcionarioRepositorie.createFuncionario(f);
        } catch (Exception e) {
            return e.getMessage();
        }
        
    }

    @Override
    public Funcionario buscaFuncionario(int id) {
        try {
            return funcionarioRepositorie.getFuncionario(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String atualizarFuncionario(Funcionario funcionario) throws Exception {
        try {
            return funcionarioRepositorie.UpdateFuncionario(funcionario);
        } catch (Exception e) {
            throw new Exception("Erro ao deletar o atualizar" + e.getMessage());
        }  
    }

    @Override
    public String deleteFuncionario(int id) throws Exception {
        try {
            return funcionarioRepositorie.DeleteFuncionario(id);
        } catch (Exception e) {
            throw new Exception("Falha ao deletear o documento!");
        }
    }

    @Override 
    public List<Funcionario> listarFunionarios() throws Exception {
        try {
            return funcionarioRepositorie.listarFunionarios();
        } catch (Exception e) {
            throw new Exception("Falha ao listar os funcionarios" + e.getMessage());
        }

    }
    
}
