package tn.esprit.twin1.tpspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin1.tpspring.entities.Etudiant;
import tn.esprit.twin1.tpspring.entities.Foyer;

import java.util.Optional;

public interface FoyerRepositorie  extends JpaRepository<Foyer,Long> {
    Optional<Foyer> findByNomFoyer(String nomFoyer);
}
