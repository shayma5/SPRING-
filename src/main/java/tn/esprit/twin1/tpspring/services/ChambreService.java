package tn.esprit.twin1.tpspring.services;

import tn.esprit.twin1.tpspring.entities.Chambre;
import tn.esprit.twin1.tpspring.entities.TypeChambre;

import java.util.List;

public interface ChambreService {
    Chambre addChambre(Chambre  chambre);
    List<Chambre> getAllChambre();
    Chambre findChambreById(Long id);
    String deleteChambreById(Long id);
    Chambre updateChambre(long id, Chambre upchambre);
    List<Chambre> getChambresParBlocEtType (Long idBloc, TypeChambre typeC) ;
}
