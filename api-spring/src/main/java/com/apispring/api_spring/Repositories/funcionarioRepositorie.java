package com.apispring.api_spring.Repositories;

import com.apispring.api_spring.Model.Funcionario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.apispring.api_spring.Model.FireStore;

public class funcionarioRepositorie {
    public static String createFuncionario(Funcionario f) throws IOException, InterruptedException, ExecutionException {

        Firestore db = FireStore.initializeFirestore();
        DocumentReference docRef = db.collection("Funcionarios").document(String.valueOf(f.getId()));

        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dataHoraAtual.format(formatter);
        
        try {
            DocumentSnapshot lastDocument = FireStore.getLastRecord("Funcionarios");

            if (lastDocument.exists()) {
                f.setId(Integer.parseInt(lastDocument.getId()) + 1);
            } else {
                //CASO O BANCO ESTEJA VAZIO 
                f.setId(0);
            }

            f.setDataRegistro(dataFormatada);
            f.setAtivo(true);
            db.collection("Funcionarios").document(String.valueOf(f.getId())).set(f);

            return "Funcionário Criado com sucesso!";
        } catch (Exception e) {
            throw new IOException("Falha ao criar o funcionário! " + e.getMessage());
        }

    }

    public static Funcionario getFuncionario(int id)  throws IOException {
        
        Firestore db = FireStore.initializeFirestore();
        DocumentReference docRef = db.collection("Funcionarios").document(String.valueOf(id));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;
        try {
            document = future.get();
        } catch (Exception e) {
            throw new IOException("Falha ao buscar o documento! " + e.getMessage());
        }

        if (document.exists()) {
            Funcionario f = document.toObject(Funcionario.class);
            return f;
        } else {
            return null;
        }

    }

    public static String UpdateFuncionario(Funcionario funcionarioAtualizado)  throws IOException {
        Firestore db = FireStore.initializeFirestore();
        DocumentReference docRef = db.collection("Funcionarios").document(String.valueOf(funcionarioAtualizado.getId()));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;
        Funcionario funcionario = new Funcionario();
        
        try {
            document = future.get();
            if (document.exists()) {
                funcionario = document.toObject(Funcionario.class);
            } else {
                return "Registro para atualização não encontrado"; 
            }
    
            Map<String, Object> map = new HashMap<>();
            
            if (!funcionario.getNome().equals(funcionarioAtualizado.getNome())) {
                map.put("nome", funcionarioAtualizado.getNome());
            }
            if (!funcionario.getSobrenome().equals(funcionarioAtualizado.getSobrenome())) {
                map.put("sobrenome", funcionarioAtualizado.getSobrenome());
            }
            if (!funcionario.getEmail().equals(funcionarioAtualizado.getEmail())) {
                map.put("email", funcionarioAtualizado.getEmail());
            }
            if (funcionario.getSalario() != funcionarioAtualizado.getSalario()) {
                map.put("salario", funcionarioAtualizado.getSalario());
            }
            if (funcionario.getNis() != funcionarioAtualizado.getNis()) {
                map.put("nis", funcionarioAtualizado.getNis());
            }

            if (!map.isEmpty()) {
                docRef.update(map);
                return "Documento Atualizado com sucesso!";
            } else {
                return "Nenhuma atualização necessária";
            }
        } catch (Exception e) {
            throw new IOException("Falha ao atualizar o documento! " + e.getMessage());
        }

    }

    public static String DeleteFuncionario( int id ) throws IOException {

        Firestore db = FireStore.initializeFirestore();
        DocumentReference docRef = db.collection("Funcionarios").document(String.valueOf(id));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;

        try {
            document = future.get();

            if (document.exists()) {
                db.collection("Funcionarios").document(String.valueOf(id)).delete();
                return "Documento deletado com sucesso!";
            }
            else {
                throw new Exception("Documento não localizado");
            }
        } catch (Exception e) {
            throw new IOException("Falha ao deletar o documento!" + e.getMessage());
        }
        

    }

    public static List<Funcionario> listarFunionarios() throws Exception {

        Firestore db = FireStore.initializeFirestore();
        List<Funcionario> listaFuncionarios = new ArrayList<>();
         try {
            ApiFuture<QuerySnapshot> listarFuncionario = db.collection("Funcionarios").whereEqualTo("ativo", true).get();

            QuerySnapshot querySnapshot = listarFuncionario.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (DocumentSnapshot document : documents) {
                Funcionario funcionario = document.toObject(Funcionario.class);
                listaFuncionarios.add(funcionario);
            }

            return listaFuncionarios;
            
         } catch (Exception e) {
            throw new Exception("Falha ao listar os funcionarios!" + e.getMessage());
         }

    } 
}
