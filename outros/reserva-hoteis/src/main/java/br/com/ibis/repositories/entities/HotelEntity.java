package br.com.ibis.repositories.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document("hoteis")
public class HotelEntity {

    @Id
    private ObjectId id;
    private String nome;
    private String pais;
    private String estado;
    private String cidade;
    private Integer quantidadeDeQuartos;
    private List<DadosReserva> reservas = new ArrayList<>();

    public HotelEntity() {
    }

    public HotelEntity(String nome, String pais, String estado, String cidade, Integer quantidadeDeQuartos) {
        this.nome = nome;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.quantidadeDeQuartos = quantidadeDeQuartos;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getQuantidadeDeQuartos() {
        return quantidadeDeQuartos;
    }

    public void setQuantidadeDeQuartos(Integer quantidadeDeQuartos) {
        this.quantidadeDeQuartos = quantidadeDeQuartos;
    }

    public List<DadosReserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<DadosReserva> reservas) {
        this.reservas = reservas;
    }

    public static class DadosReserva {

        private String email;
        private LocalDate checkIn;
        private LocalDate checkOut;

        public DadosReserva() {
        }

        public DadosReserva(String email, LocalDate checkIn, LocalDate checkOut) {
            this.email = email;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public LocalDate getCheckIn() {
            return checkIn;
        }

        public void setCheckIn(LocalDate checkIn) {
            this.checkIn = checkIn;
        }

        public LocalDate getCheckOut() {
            return checkOut;
        }

        public void setCheckOut(LocalDate checkOut) {
            this.checkOut = checkOut;
        }
    }
}
