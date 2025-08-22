package GerardoJovel_20240396.GerardoJovel_20240396.Controllers;

import GerardoJovel_20240396.GerardoJovel_20240396.Model.DTO.LibroDTO;
import GerardoJovel_20240396.GerardoJovel_20240396.Services.LibroService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiLibro")
public class LibroController {

    @Autowired
    private LibroService service; // Inyectamos el service al controller

    /**
     * @return Retorna una lista con todos los libros en formato DTO
     */
    @GetMapping("/getAllbooks")
    public List<LibroDTO> getAllBooks () {
        // Le pedimos al service que obtenga todos los libros
        return service.getAllBooks();
    }

    /**
     * @param id Identificador único del libro
     * @return Retorna un ResponseEntity con el libro en específico en formato DTO
     */
    @GetMapping("/getBookById/{id}")
    public ResponseEntity<?> getBookById (@PathVariable long id) {
        try {
            // El service busca el libro por ID
            LibroDTO data = service.getBookById(id);

            // Si encuentra el libro lo retorna en un ResponseEntity
            return ResponseEntity.status(HttpStatus.FOUND).body(Map.of(
                    "status", "Success",
                    "data", data
            ));
        } catch (IllegalArgumentException e) {
            // Si no existe id para ningún libro, manda un error htttp
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "Error",
                    "message", "No existe libro con ese ID"
            ));
        }
        catch (RuntimeException e) {
            // Si encuentra un error mientras hace la operación, manda un error 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras buscaba un libro con ese ID",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    /**
     * @param dto Datos para insertar un nuevo libro
     * @return Retorna un ResponseEntity con el nuevo libro ya insertado en formato DTO
     */
    @PostMapping("/insertBook")
    public ResponseEntity<?> insertBook (@Valid @RequestBody LibroDTO dto) {
        try {
            // El service retorna el libro ya insertado
            LibroDTO answer = service.insertBook(dto);

            // Si la respuesta del service es nula, ocurrió un error, y se manda un error http
            if (answer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                        "status", "Error",
                        "message", "Error al ingresar el libro, se obtuvo respuesta nula"
                ));
            }
            // Si la respuesta es correcta, manda un código http positivo y el objeto ya creado
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Success",
                    "message", "Nuevo registro de libro creado exitosamente",
                    "data", answer
            ));

        } catch (Exception e) {
            // Si ocurre un error mientras se procesa la solicitud, manda un error código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras se creaba una nueva instancia de libro",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    /**
     * @param id Identificador único del libro
     * @param dto Datos nuevos para actualizar el libro
     * @return Retorna un ResponseEntity con el libro ya actualizado
     */
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<?> updateBook (@PathVariable long id, @Valid @RequestBody LibroDTO dto) {
        try {
            // El service retorna el libro ya actualizado
            LibroDTO answer = service.updateBook(id, dto);

            // Si la respuesta es nula, ocurrió un error, y se le hace saber esto al usuario con un código http negativo
            if (answer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                        "status", "Error",
                        "message", "Error al actualizar el libro, se obtuvo respuesta nula"
                ));
            }
            // Si nada ha fallado, retorna una respuesta positiva y el libro ya creado en formato DTO
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "Success",
                    "message", "Libro actualizado exitosamente",
                    "data", answer
            ));

        } catch (Exception e) {
            // Si encuentra un error en tiempo de ejecución, manda un código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras se actualizaba una instancia de libro",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    /**
     * @param id Identificador único del libro
     * @return Retorna un ResponseEntity que indica el estado de la eliminación
     */
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<?> deleteBook (@PathVariable long id) {
        try {
            // El service elimina el libro por el ID
            service.deleteBook(id);

            // Si no hay excepciones, entonces manda una respuesta con un código positivo
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "Success",
                    "message", "Libro eliminado exitosamente"
            ));
        } catch (IllegalArgumentException e) {
            // Si el service no encontro ningún libro con ese Id, retorna un código http negativo BAD REQUEST
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "Error",
                    "message", "No existe libro con ese ID"
            ));
        } catch (Exception e) {
            // Si ocurre un error en tiempo de ejecución manda un código http 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras se eliminaba una instancia de libro",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

}
