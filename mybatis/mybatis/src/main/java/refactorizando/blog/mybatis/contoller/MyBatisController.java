package refactorizando.blog.mybatis.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactorizando.blog.mybatis.constants.ParameterConstans;
import refactorizando.blog.mybatis.constants.URLConstants;
import refactorizando.blog.mybatis.dto.UsuarioDTO;
import refactorizando.blog.mybatis.model.Usuario;
import refactorizando.blog.mybatis.serviceimpl.UsuarioServiceImpl;

import java.util.UUID;

/**
 * Controlador Rest
 */
@RestController
@RequestMapping(value = URLConstants.USUARIOS)
public class MyBatisController {

    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    public MyBatisController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @GetMapping(value=URLConstants.OBTENER_USUARIO)
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable(URLConstants.ID) UUID id){
       return usuarioServiceImpl.obtenerPorId(id);
    }
    @PostMapping
    public ResponseEntity crearUsuario(@RequestBody UsuarioDTO usuario){
        return usuarioServiceImpl.crearUsuario(usuario);
    }
    @PutMapping
    public ResponseEntity actualizarUsuario(@RequestBody Usuario usuario){
        return usuarioServiceImpl.actualizarUsuario(usuario);
    }
    @DeleteMapping(value=URLConstants.OBTENER_USUARIO)
    public ResponseEntity eliminarUsuario(@PathVariable(ParameterConstans.ID) UUID id){
        return usuarioServiceImpl.eliminarUsuario(id);
    }
}
