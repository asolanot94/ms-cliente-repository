package nttdata.grupouno.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clientBootcoin")
public class Client {


    @Id
    private String id;
    @NotEmpty(message = "El número de Documento no puede ser vacio")
    private String documentNumber;
    @NotEmpty(message = "El tipo de documento no puede ser vacio")
    private String documentType;
    @NotNull(message = "El número de cel. no puede ser vacio")
    private Integer cellphone;
    @NotEmpty(message = "El correo no debe estar vacio")
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

}
