package tn.esprit.twin1.tpspring.services;

import tn.esprit.twin1.tpspring.entities.Etudiant;

import java.util.List;

public interface EtudiantService {
    Etudiant addEtudiant(Etudiant etudiant);

    List<Etudiant> findAllEtudiant();

    Etudiant findById(Long id);

    String deleteEtudiant(Etudiant etudiant);

    Etudiant updateEtudiant(long idEtudiant, Etudiant updatedEt);
}
