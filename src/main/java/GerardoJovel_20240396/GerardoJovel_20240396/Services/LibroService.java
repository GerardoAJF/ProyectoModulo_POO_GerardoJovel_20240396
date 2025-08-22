package GerardoJovel_20240396.GerardoJovel_20240396.Services;

import GerardoJovel_20240396.GerardoJovel_20240396.Entities.LibroEntity;
import GerardoJovel_20240396.GerardoJovel_20240396.Model.DTO.LibroDTO;
import GerardoJovel_20240396.GerardoJovel_20240396.Repositories.LibroRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LibroService {

    @Autowired
    LibroRepository repository;

    /**
     * @return Retorna una lista de DTOs con todos los libros
     */
    public List<LibroDTO> getAllBooks() {
        // Obtenemos todos los libros
        // Posteriormente convertimos los entities a dtos
        return repository.findAll().stream()
                .map(this::BookToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador único del libro (long)
     * @return Retorna el DTO de un libro en específico
     */
    public LibroDTO getBookById(long id) {
        try {
            // Validamos que el libro exista
            if (!repository.existsById(id)) {
                // Lanza una excepción que será atrapada
                throw new IllegalArgumentException();
            }
            // Retornamos el libro solicitado
            LibroEntity entity = repository.getReferenceById(id);
            return BookToDTO(entity);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No existe un libro con ese ID");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Hubo un error al buscar el libro");
        }
    }

    /**
     * @param dto Datos para insertar un nuevo en formato JSON (DTO)
     * @return Retorna el libro recien insertado en formato DTO
     */
    public LibroDTO insertBook(@Valid LibroDTO dto) {
        try {
            // Validamos que se proporcionen argumentos correctos
            if (dto == null) {
                // Lanzamos una excepción que será atrapada
                throw new IllegalArgumentException();
            }
            // Convertimos el dto a un entity
            LibroEntity entity = DTOToBook(dto);
            // Guardamos el entity y posteriormente lo retornamos
            LibroEntity savedEntity = repository.save(entity);
            return BookToDTO(savedEntity);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El dto envíado es nulo");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Error creando un nuevo libro");
        }
    }

    /**
     * @param id Identificador único del libro (long)
     * @param dto Datos para actualizar el libro
     * @return Retorna el libro ya actualizado en formato DTO
     */
    public LibroDTO updateBook(long id, @Valid LibroDTO dto) {
        try {
            // Validamos que el libro a actualizar exista
            if (!repository.existsById(id)) {
                throw new IllegalArgumentException();
            }
            // Validamos que se nos proporcionen datos para actualizar correctos
            if (dto == null) {
                throw new IllegalArgumentException();
            }
            // Obtenemos el libro de la base de datos
            LibroEntity entity = repository.getReferenceById(id);
            // Actualizamos los campos del entity con los obtenidos por el dto
            entity.setTitulo(dto.getTitulo());
            entity.setIsbn(dto.getIsbn());
            entity.setGenero(dto.getGenero());
            entity.setAño(dto.getAño());
            entity.setAutor_id(dto.getAutor_id());
            // Obtenemos el entity ya actualizado y lo retornamos
            LibroEntity savedEntity = repository.save(entity);
            return BookToDTO(savedEntity);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Los datos enviados no son válidos");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Error actualizando un nuevo libro");
        }
    }

    /**
     * @param id Identificador único del libro (long)
     * @return Retorna el libro que se ha eliminado en formato DTO
     */
    public LibroDTO deleteBook(long id) {
        try {
            // Validamos que el libro exista
            if (!repository.existsById(id)) {
                throw  new IllegalArgumentException();
            }
            // Obtenemos el libro
            LibroEntity entity = repository.getReferenceById(id);
            // Eliminamos el libro
            repository.deleteById(id);
            // Retornamos el libro previamente obtenido
            return BookToDTO(entity);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No existe un libro con ese ID");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Error eliminando una instancia de libro libro");
        }
    }

    /**
     * @param entity El libro en formato entity
     * @return Retorna el libro en formato DTO
     */
    private LibroDTO BookToDTO(LibroEntity entity) {
        // Creamos un nuevo objeto dto vacío
        LibroDTO dto = new LibroDTO();
        // Actualizamos los valores del dto con los del entity
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAño(entity.getAño());
        dto.setGenero(entity.getGenero());
        dto.setAutor_id(entity.getAutor_id());
        // retornamos el dto ya llenado
        return dto;
    }

    /**
     * @param dto El libro en formato DTO
     * @return Retorna el libro en formato Entity
     */
    private LibroEntity DTOToBook(LibroDTO dto) {
        // Creamos un nuevo objeto entity vacío
        LibroEntity entity = new LibroEntity();
        // Actualizamos los valores del entity con los del dto
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setAño(dto.getAño());
        entity.setGenero(dto.getGenero());
        entity.setAutor_id(dto.getAutor_id());
        // Retornamos el entity ya llenado
        return entity;
    }

}
