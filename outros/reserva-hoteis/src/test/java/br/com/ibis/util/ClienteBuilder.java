package br.com.ibis.util;

import br.com.ibis.controllers.models.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClienteBuilder {

    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;

    public static ClienteBuilder builder() {
        return new ClienteBuilder();
    }

    public ClienteBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }
    public ClienteBuilder email(String email) {
        this.email = email;
        return this;
    }
    public ClienteBuilder telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }
    public ClienteBuilder dataNascimento(String dataNascimento) {
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return this;
    }

    public Cliente build() {

        return new Cliente(nome, email, telefone, dataNascimento);
    }

}
