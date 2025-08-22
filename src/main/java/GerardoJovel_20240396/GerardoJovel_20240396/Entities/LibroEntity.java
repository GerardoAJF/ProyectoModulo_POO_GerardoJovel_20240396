package GerardoJovel_20240396.GerardoJovel_20240396.Entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name="LIBROS")
@Getter @Setter @ToString @EqualsAndHashCode
public class LibroEntity {

    // Definimos el campo de ID y como se generará automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_libro")
    @SequenceGenerator(name="seq_libro", sequenceName="seq_libro", allocationSize=1)
    private long id;

    // Definimos el campo de titulo con sus restricciones
    @Column(name = "TITULO", nullable = false, length = 200)
    private String titulo;

    // Definimos el campo de ISBN con sus restricciones
    @Column(name = "ISBN", nullable = false, length = 20)
    private String isbn;

    // Definimos el campo que guardará el año de publicación
    @Column(name = "AÑO_PUBLICACION", nullable = true)
    private int año;
    // Definimos el campo que guardará el genero
    @Column(name = "GENERO", nullable = true)
    private String genero;

    // Definimos el campo del id del autor con sus restricciones
    @Column(name = "AUTOR_ID", nullable = false)
    private long autor_id;
}
