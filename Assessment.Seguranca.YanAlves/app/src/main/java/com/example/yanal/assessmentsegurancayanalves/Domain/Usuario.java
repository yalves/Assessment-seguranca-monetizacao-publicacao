package com.example.yanal.assessmentsegurancayanalves.Domain;

import java.io.Serializable;

/**
 * Created by yanal on 02/10/2017.
 */

public class Usuario implements Serializable {

    private String Nome;
    private String Email;
    private String Senha;
    private String ConfirmaSenha;
    private String Cpf;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getConfirmaSenha() {
        return ConfirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        ConfirmaSenha = confirmaSenha;
    }

    public String getCpf() { return Cpf; }

    public void setCpf(String cpf) { Cpf = cpf; }
}