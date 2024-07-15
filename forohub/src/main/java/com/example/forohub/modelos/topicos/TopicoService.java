package com.example.forohub.modelos.topicos;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TopicoService {
    topico crearTopico(DatosRegistroTopico datosRegistroTopico, String nombreUsuario);
    List<topico> obtenerTodosLosTopicos();
    topico obtenerTopicoPorId(Long id);
    topico actualizarTopico(Long id, DatosActualizarTopico datosRegistroTopico);
    boolean eliminarTopico(Long id);
}
