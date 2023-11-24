package com.dronedelivery.apidrone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "entregas")
public class Entrega extends Location{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "peso")
    private Float peso;

    @NotNull
    @Column
    private StatusEntrega status;

    @NotNull
    @Column(name = "obs")
    private String obs;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trajeto", referencedColumnName = "idTrajeto")
    private long trajeto;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rota", referencedColumnName = "idRota")
    private long rota;

    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public long getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(long trajeto) {
        this.trajeto = trajeto;
    }

    public long getRota() {
        return rota;
    }

    public void setRota(long rota) {
        this.rota = rota;
    }
}
