package com.arrienda.proj.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaSolicitud;
    private int estado;

    @ManyToOne
    @JoinColumn(name = "arrendatario_id")
    private Usuario arrendatario;

    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;    
}
