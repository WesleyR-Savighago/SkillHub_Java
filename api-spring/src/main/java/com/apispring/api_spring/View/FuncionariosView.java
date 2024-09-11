package com.apispring.api_spring.View;

import org.springframework.web.bind.annotation.RestController;

import com.apispring.api_spring.Controller.FuncionarioController;
import com.apispring.api_spring.Controller.Implements.FuncionarioControllerImplements;
import com.apispring.api_spring.Model.Funcionario;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class FuncionariosView {

    FuncionarioController funcionarioController = new FuncionarioControllerImplements();

    @GetMapping("/Funcionario")
    public Funcionario buscaFunciodario(@RequestParam int id) throws Exception {
        return funcionarioController.buscaFuncionario(id);
    }
    

    @PostMapping("/newFuncionario")
    @Validated()
    public String criafuncionario(@RequestBody Funcionario  f) throws Exception {
        try {
            String result = funcionarioController.inserirFuncionario(f);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/atualizaFuncionario")
    public String atualizaFuncionario(@RequestBody Funcionario  f) throws Exception {
        try {
            String result = funcionarioController.atualizarFuncionario(f);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @DeleteMapping("/deletaFuncionario")
    public String deletaFuncionarios(@RequestParam int id) throws Exception {
        try {
            String result = funcionarioController.deleteFuncionario(id);
            return result;
        } catch (Exception e) {
            throw new Exception("Falha ao deletar o documento" + e.getMessage());
        }
    }

    @GetMapping("/listarFuncionarios")
    public List<Funcionario> listarFunionarios() throws Exception {
        try {
            return funcionarioController.listarFunionarios();
        } catch (Exception e) {
            throw new Exception("Falha ao listar " + e.getMessage());
        }
    }
    
    
    

}
