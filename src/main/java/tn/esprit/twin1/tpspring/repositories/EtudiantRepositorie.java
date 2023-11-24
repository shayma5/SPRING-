package tn.esprit.twin1.tpspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.twin1.tpspring.entities.Etudiant;

import java.util.Optional;

@Repository
public interface EtudiantRepositorie extends JpaRepository<Etudiant,Long>
{
    Optional <Etudiant> findByCin(Long cin);
}
