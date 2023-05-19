package br.com.ibis.repositories.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("clientes")
public class ClienteEntity {

    @Id
    private ObjectId id;
    private String nome;
    private String email;
    private Boolean emailConfirmado;
    private String telefone;
    private Boolean telefoneConfirmado;
    private LocalDate dataNascimento;
    private String random2FA;

    public ClienteEntity() {
    }

    public ClienteEntity(String nome, String email, String telefone, LocalDate dataNascimento, String random2FA) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.emailConfirmado = false;
        this.telefoneConfirmado = false;
        this.random2FA = random2FA;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailConfirmado() {
        return emailConfirmado;
    }

    public void setEmailConfirmado(Boolean emailConfirmado) {
        this.emailConfirmado = emailConfirmado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getTelefoneConfirmado() {
        return telefoneConfirmado;
    }

    public void setTelefoneConfirmado(Boolean telefoneConfirmado) {
        this.telefoneConfirmado = telefoneConfirmado;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRandom2FA() {
        return random2FA;
    }

    public void setRandom2FA(String random2FA) {
        this.random2FA = random2FA;
    }
}
