package com.dronedelivery.apidrone.resource;

import com.dronedelivery.apidrone.model.Entrega;
import com.dronedelivery.apidrone.model.StatusEntrega;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;


public class EntregaDTO {

    private Long idEntrega;

    private String nome;
    @NotNull
    private Float peso;

    @NotNull
    private StatusEntrega status;

    @NotNull
    private String obs;


    private double latitude;

    private double longitude;

    private double height;


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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public static EntregaDTO fromEntity(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setHeight(entrega.getHeight());
        dto.setIdEntrega(entrega.getIdEntrega());
        dto.setLatitude(entrega.getLatitude());
        dto.setLongitude(entrega.getLongitude());
        dto.setNome(entrega.getNome());
        dto.setPeso(entrega.getPeso());
        dto.setStatus(entrega.getStatus());
        dto.setObs(entrega.getObs());
        return dto;
    }

    public Entrega toEntity() {
        Entrega entrega = new Entrega();
        entrega.setIdEntrega(idEntrega);
        entrega.setNome(nome);
        entrega.setPeso(peso);
        entrega.setStatus(status);
        entrega.setHeight(height);
        entrega.setLatitude(latitude);
        entrega.setLongitude(longitude);
        entrega.setObs(obs);
        return entrega;
    }

    public static List<EntregaDTO> fromEntity(List<Entrega> entregas) {
        return entregas.stream().map(entrega-> fromEntity(entrega)).collect(Collectors.toList());
    }

    public static Page<EntregaDTO> fromEntity(Page<Entrega> entregas) {
        List<EntregaDTO> entregasFind = entregas.stream().map(entrega -> fromEntity(entrega)).collect(Collectors.toList());
        Page<EntregaDTO> entregasDTO = new PageImpl<>(entregasFind, entregas.getPageable(), entregas.getTotalElements());
        return entregasDTO;
    }
}
