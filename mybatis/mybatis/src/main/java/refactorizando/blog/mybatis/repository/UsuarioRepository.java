package refactorizando.blog.mybatis.repository;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.http.ResponseEntity;
import refactorizando.blog.mybatis.constants.Constants;
import refactorizando.blog.mybatis.constants.ParameterConstans;
import refactorizando.blog.mybatis.dto.UsuarioDTO;
import refactorizando.blog.mybatis.handlers.UUIDHandler;
import refactorizando.blog.mybatis.model.Usuario;

import java.util.UUID;

@Mapper
public interface UsuarioRepository  {

    @Select("SELECT id,nombreUsuario,apellidoPaterno,balance FROM usuario WHERE id=#{id}")
    @Result(property = ParameterConstans.ID,column = ParameterConstans.ID,typeHandler = UUIDHandler.class)
    public Usuario obtenerPorId(@Param(ParameterConstans.ID) UUID id);

    @Insert("INSERT INTO usuario(id,nombreUsuario,apellidoPaterno,balance) VALUES (#{usuario.id},#{usuario.nombreUsuario},#{usuario.apellidoPaterno},#{usuario.balance})")
    public Integer crearUsuario(@Param(ParameterConstans.USUARIO)Usuario usuario);

    @Delete("DELETE FROM usuario WHERE id=#{id}")
    public Integer eliminarUsuario(@Param(ParameterConstans.ID) UUID id);

    @Update("UPDATE usuario SET nombreUsuario=#{usuario.nombreUsuario},apellidoPaterno=#{usuario.apellidoPaterno},balance=#{usuario.balance}")
    public Integer actualizarUsuario(@Param(ParameterConstans.USUARIO)UsuarioDTO usuario);
}
