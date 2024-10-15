package com.microservices.microservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Integer idcliente;

    @Column(name = "idpersona")
    private Integer idpersona;

    @Column(name = "clienteid")
    private String clienteid;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estadocliente")
    private Boolean estadocliente;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona", insertable = false, updatable = false)
    private Persona idpersonaNavigation;

    // Getters y Setters

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getClienteid() {
        return clienteid;
    }

    public void setClienteid(String clienteid) {
        this.clienteid = clienteid;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstadocliente() {
        return estadocliente;
    }

    public void setEstadocliente(Boolean estadocliente) {
        this.estadocliente = estadocliente;
    }

    public Persona getIdpersonaNavigation() {
        return idpersonaNavigation;
    }

    public void setIdpersonaNavigation(Persona idpersonaNavigation) {
        this.idpersonaNavigation = idpersonaNavigation;
    }
}
