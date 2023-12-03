package tn.esprit.twin1.tpspring.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.twin1.tpspring.dto.AddBlocRequest;
import tn.esprit.twin1.tpspring.dto.AddChambreDto;
import tn.esprit.twin1.tpspring.entities.Bloc;
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
    public Chambre updateChambre(long id, AddChambreDto upchambre);
    public ResponseEntity<String> addChambreToBloc(AddChambreDto chambre);
}
