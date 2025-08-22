package GerardoJovel_20240396.GerardoJovel_20240396.Repositories;

import GerardoJovel_20240396.GerardoJovel_20240396.Entities.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    List<LibroEntity> findByTituloContains(String name);
}
