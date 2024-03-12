package com.arrienda.proj.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;

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
@SQLDelete(sql = "UPDATE solicitud SET  status = 1 WHERE id=?")

public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaSolicitud;
    private int estado;

    @ManyToOne
    @JoinColumn(name = "arrendatario_id")
    private Usuario arrendatario;

    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;    
}
