package refactorizando.blog.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String nombreUsuario;
    private String apellidoPaterno;
    private BigDecimal balance;
}
