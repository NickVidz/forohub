package com.example.forohub.controller;


import com.electronwill.nightconfig.core.conversion.Path;
import com.example.forohub.infra.security.AuthenticationService;
import com.example.forohub.modelos.topicos.DatosActualizarTopico;
import com.example.forohub.modelos.topicos.DatosRegistroTopico;
import com.example.forohub.modelos.topicos.TopicoService;
import com.example.forohub.modelos.topicos.topico;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class topicoController {

    private final TopicoService topicoService;
    private final AuthenticationService authenticationService;

    @Autowired
    public topicoController(TopicoService topicoService, AuthenticationService authenticationService) {
        this.topicoService = topicoService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<?> crearTopico(@Valid @RequestBody DatosRegistroTopico datosRegistroTopico, HttpServletRequest request) {
        String nombreUsuario = obtenerNombreUsuarioDesdeJWT(request);
        topico nuevoTopico = topicoService.crearTopico(datosRegistroTopico ,nombreUsuario);


        if(nuevoTopico != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El titulo o el mensaje ya existen");
        }
    }


    @GetMapping
    public ResponseEntity<List<topico>> obtenerTodosLosTopicos(){
        List<topico> topicos = topicoService.obtenerTodosLosTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<topico> obtenerTopicoPorId(@PathVariable Long id){
        topico topico = topicoService.obtenerTopicoPorId(id);
        if(topico!=null){
            return ResponseEntity.ok(topico);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<topico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody DatosActualizarTopico datosRegistroTopico){
        topico topicoActualizado  = topicoService.actualizarTopico(id,datosRegistroTopico);
        if (topicoActualizado != null){
            return ResponseEntity.ok(topicoActualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id){
        boolean eliminado = topicoService.eliminarTopico(id);
        if(eliminado){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private String obtenerNombreUsuarioDesdeJWT(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return authenticationService.obtenerNombreUsuarioJWT(token);
    }


}
