package GerardoJovel_20240396.GerardoJovel_20240396.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@EqualsAndHashCode
public class LibroDTO {
    @NotNull
    private long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String isbn;

    private int año;
    private String genero;

    @NotNull
    private long autor_id;
}
