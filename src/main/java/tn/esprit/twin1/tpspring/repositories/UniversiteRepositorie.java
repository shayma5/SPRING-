package tn.esprit.twin1.tpspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin1.tpspring.entities.Universite;

public interface UniversiteRepositorie  extends JpaRepository<Universite,Long> {
    Universite findByNomUniversite(String nomUniversite);
}
