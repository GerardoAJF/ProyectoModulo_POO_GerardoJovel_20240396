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
    private LibroService service;

    @GetMapping("/getAllbooks")
    public List<LibroDTO> getAllBooks () {
        return service.getAllBooks();
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<?> getBookById (@PathVariable long id) {
        try {
            LibroDTO data = service.getBookById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(Map.of(
                    "status", "Success",
                    "data", data
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "Error",
                    "message", "No existe libro con ese ID"
            ));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras buscaba un libro con ese ID",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    @PostMapping("/insertBook")
    public ResponseEntity<?> insertBook (@Valid @RequestBody LibroDTO dto) {
        try {
            LibroDTO answer = service.insertBook(dto);

            if (answer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                        "status", "Error",
                        "message", "Error al ingresar el libro, se obtuvo respuesta nula"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Success",
                    "message", "Nuevo registro de libro creado exitosamente",
                    "data", answer
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras se creaba una nueva instancia de libro",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<?> updateBook (@PathVariable long id, @Valid @RequestBody LibroDTO dto) {
        try {
            LibroDTO answer = service.updateBook(id, dto);

            if (answer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                        "status", "Error",
                        "message", "Error al actualizar el libro, se obtuvo respuesta nula"
                ));
            }
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "Success",
                    "message", "Libro actualizado exitosamente",
                    "data", answer
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras se actualizaba una instancia de libro",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<?> deleteBook (@PathVariable long id) {
        try {
            service.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "Success",
                    "message", "Libro eliminado exitosamente"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "Error",
                    "message", "No existe libro con ese ID"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "El servidor presentó un error mientras se eliminaba una instancia de libro",
                    "timestamp", Instant.now().toString()
            ));
        }
    }

}
