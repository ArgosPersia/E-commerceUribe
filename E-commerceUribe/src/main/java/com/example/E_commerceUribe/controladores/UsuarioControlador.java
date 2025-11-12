package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.UsuarioDTO;
import com.example.E_commerceUribe.modelos.Usuario;
import com.example.E_commerceUribe.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Controlador para operacion tabla usuarios")
public class UsuarioControlador {

    //1.Llama al servicio
    @Autowired
    UsuarioServicio servicio;

    //2.Llamar a lo servicios disponibles
    //3. Se crean funciones por cada llamado a las funciones y se les agrega el metodo HTTP correspnidente
    //(GET/PUS/POST/DELETE)


    //Guardar
    @Operation(summary = "Crear un usuario en la BD")
    @PostMapping (produces = "application/json")
    public ResponseEntity<UsuarioDTO>guardar(@RequestBody Usuario datos){
        UsuarioDTO respuesta = this.servicio.guardarUsuario(datos);
        return  ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listarTodos
    @Operation(summary = "Listar todos los usuarios guardados en la BD")
    @GetMapping (produces = "application/json")
    public ResponseEntity<List<UsuarioDTO>> listar(){
        List<UsuarioDTO> respuesta=this.servicio.buscarListaDeUsuarios();
        return  ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscarPorId
    @Operation(summary = "Buscar un usuario en la BD")
    @GetMapping (value = "/{id}", produces = "application/json" )
    public  ResponseEntity<UsuarioDTO>buscarPorId(@PathVariable Integer id){
        UsuarioDTO respuesta = this.servicio.BuscarUsuarioPorId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar
    @Operation(summary = "Elimina un usuario de la BD")
    @DeleteMapping (value = "/{id}" , produces = "application/json")
    public ResponseEntity<Void>eliminar(@PathVariable Integer id){
    this.servicio.eliminarUsuario(id);
    return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modifica un usuario en la BD")
    @PutMapping (value = "/{id}" , produces = "application/json")
    public ResponseEntity<UsuarioDTO>modificar(@RequestBody Usuario datos, @PathVariable Integer id){
    UsuarioDTO respuesta = this.servicio.actualizarUsuario(id, datos);
    return   ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
