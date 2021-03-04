package refactorizando.blog.mybatis.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import refactorizando.blog.mybatis.constants.Constants;
import refactorizando.blog.mybatis.dto.StandardMessage;
import refactorizando.blog.mybatis.dto.UsuarioDTO;
import refactorizando.blog.mybatis.model.Usuario;
import refactorizando.blog.mybatis.repository.UsuarioRepository;
import refactorizando.blog.mybatis.service.UsuarioService;
import refactorizando.blog.mybatis.util.Util;

import java.util.UUID;

/**
 * En esta clase se hace el tratado de la informacion
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    ObjectMapper mapper= new ObjectMapper();
    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<Usuario> obtenerPorId(UUID id){
        Object object=usuarioRepository.obtenerPorId(id);
        Usuario usuario=usuarioRepository.obtenerPorId(id);
        if(Util.isNull(usuario))
            return Util.createResponseEntity(null,HttpStatus.NOT_FOUND);
        return Util.createResponseEntity(usuario,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StandardMessage> crearUsuario(UsuarioDTO usuario){
        StandardMessage mensaje= new StandardMessage();
        Usuario usuarioMapeado=this.mapUsuarioDtoToUsuario(usuario);
        usuarioMapeado.setId(this.generateRandomUUID());
        System.out.println(usuarioMapeado.getId());
        Integer rowsAffected=usuarioRepository.crearUsuario(usuarioMapeado);
        if (rowsAffected<=0){
            mensaje.setMensaje(Constants.NO_SE_PUDO_CREAR);
            return Util.createResponseEntity(mensaje,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        mensaje.setMensaje(Constants.OPERACION_EXITOSA);
        return  Util.createResponseEntity(mensaje,HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<StandardMessage> eliminarUsuario(UUID id){
        StandardMessage mensaje= new StandardMessage();
        Integer rowsAffected=usuarioRepository.eliminarUsuario(id);
        if (rowsAffected<=0){
            mensaje.setMensaje(Constants.NO_EXISTE_ELIMINAR);
            return Util.createResponseEntity(mensaje,HttpStatus.NOT_FOUND);
        }
        mensaje.setMensaje(Constants.OPERACION_EXITOSA);
        return  Util.createResponseEntity(mensaje,HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<StandardMessage> actualizarUsuario(Usuario usuario){
        StandardMessage mensaje= new StandardMessage();
        Integer rowsAffected=usuarioRepository.actualizarUsuario(usuario);
        if (rowsAffected<=0){
            mensaje.setMensaje(Constants.NO_EXISTE_ACTUALIZAR);
            return Util.createResponseEntity(mensaje,HttpStatus.NOT_FOUND);
        }
        mensaje.setMensaje(Constants.OPERACION_EXITOSA);
        return  Util.createResponseEntity(mensaje,HttpStatus.CREATED);
    }

    private Usuario mapUsuarioDtoToUsuario(UsuarioDTO  usuarioDTO){
        return mapper.convertValue(usuarioDTO,Usuario.class);

    }

    private UUID generateRandomUUID(){
        UUID generatedUUID=UUID.randomUUID();
        Usuario usuario=usuarioRepository.obtenerPorId(generatedUUID);
        if (!Util.isNull(usuario))
            generateRandomUUID();
        return generatedUUID;
    }

}
