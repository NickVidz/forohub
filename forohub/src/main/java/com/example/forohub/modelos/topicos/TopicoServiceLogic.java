package com.example.forohub.modelos.topicos;

import com.example.forohub.modelos.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoServiceLogic implements TopicoService{

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TopicoServiceLogic(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public topico crearTopico(DatosRegistroTopico datosRegistroTopico , String nombreUsuario) {
        boolean tituloExiste = !topicoRepository.findBytitulo(datosRegistroTopico.titulo()).isEmpty();
        boolean mensajeExiste = !topicoRepository.findBymensaje(datosRegistroTopico.mensaje()).isEmpty();

        if(!tituloExiste && !mensajeExiste){
            topico nuevoTopico = new topico();
            nuevoTopico.setTitulo(datosRegistroTopico.titulo());
            nuevoTopico.setMensaje(datosRegistroTopico.mensaje());
            nuevoTopico.setFechaDeCreacion(LocalDate.now());
            nuevoTopico.setStatus("Activo");
            nuevoTopico.setAutor(nombreUsuario);
            return topicoRepository.save(nuevoTopico);
        }else {
            return null;
        }
    }

    @Override
    public List<topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    @Override
    public topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id).orElse(null);
    }

    @Override
    public topico actualizarTopico(Long id, DatosActualizarTopico datosRegistroTopico) {
        Optional<topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            topico topico = topicoOptional.get();
            topico.setTitulo(datosRegistroTopico.titulo());
            topico.setMensaje(datosRegistroTopico.mensaje());
            return topicoRepository.save(topico);
        }else{
            return null;
        }
    }

    @Override
    public boolean eliminarTopico(Long id) {
        Optional<topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            topicoRepository.delete(topicoOptional.get());
            return true;
        }else{
            return false;
        }
    }
}
