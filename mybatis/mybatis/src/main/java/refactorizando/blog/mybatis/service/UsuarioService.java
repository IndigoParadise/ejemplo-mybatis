package refactorizando.blog.mybatis.service;

import org.springframework.http.ResponseEntity;
import refactorizando.blog.mybatis.dto.UsuarioDTO;
import refactorizando.blog.mybatis.model.Usuario;

import java.util.UUID;

public interface UsuarioService {




    public ResponseEntity<Usuario> obtenerPorId(UUID id);

    public ResponseEntity crearUsuario(UsuarioDTO usuario);

    public ResponseEntity eliminarUsuario(UUID id);

    public ResponseEntity actualizarUsuario(UsuarioDTO usuario);
}
