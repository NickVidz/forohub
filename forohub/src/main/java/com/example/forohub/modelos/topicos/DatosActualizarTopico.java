package com.example.forohub.modelos.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico (
        @NotNull Long id, String titulo, String mensaje
){
}
