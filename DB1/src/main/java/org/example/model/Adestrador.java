package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adestrador")
public class Adestrador {

    @Id
    private String id_adestrador;

    private String nome;
    private String cidade;
    private Integer idade;

    public Adestrador() {}

    public Adestrador(String nome, String cidade, Integer idade) {
        this.nome = nome;
        this.cidade = cidade;
        this.idade = idade;
    }

    public String getId_adestrador() {
        return id_adestrador;
    }

    public void setId_adestrador(String id_adestrador) {
        this.id_adestrador = id_adestrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}