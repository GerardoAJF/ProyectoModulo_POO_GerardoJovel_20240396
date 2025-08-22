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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_libro")
    @SequenceGenerator(name="seq_libro", sequenceName="seq_libro", allocationSize=1)
    private long id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "AÑO_PUBLICACION")
    private int año;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "AUTOR_ID")
    private long autor_id;
}
