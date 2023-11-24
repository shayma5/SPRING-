package tn.esprit.twin1.tpspring.services;

import tn.esprit.twin1.tpspring.entities.Universite;

import java.util.List;

public interface UniversiteService {
    Universite addUniversite(Universite universite);

    List<Universite> findAllUniversute();

    Universite findUniversiteById(Long id);

    String deleteUniversuteById(Long id);
    Universite updateUniversite(long id, Universite upuniversite);

    Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(Long idUniversite);
}
