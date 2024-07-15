package com.example.forohub.modelos.topicos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<topico, Long> {

    List<topico> findBytitulo(String titulo);

    List<topico> findBymensaje(String mensaje);
}
