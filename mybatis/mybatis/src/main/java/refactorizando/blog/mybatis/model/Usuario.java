package refactorizando.blog.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private UUID id;
    private String nombreUsuario;
    private String apellidoPaterno;
    private BigDecimal balance;



}
