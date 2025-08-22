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
    // Validamos que el ID no sea nulo
    @NotNull
    private long id;

    // Validamos que el titulo no sea nulo
    @NotBlank
    private String titulo;

    // Validamos que el ISBN no sea nulo
    @NotBlank
    private String isbn;

    // El año si puede ser nulo, e incluso negativo (para representar fechas antes de cristo)
    private int año;

    // El genero tambien puede ser nulo
    private String genero;

    // Validamos que el id no sea nulo
    @NotNull
    private long autor_id;
}
