package tn.esprit.twin1.tpspring.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.twin1.tpspring.dto.AddBlocRequest;
import tn.esprit.twin1.tpspring.entities.Bloc;

import java.util.List;

public interface BlocService {
    Bloc addBloc(Bloc bloc);

    List<Bloc> getAllBloc();

    Bloc findBlocById(Long id);

    public void deleteBlocById(long id);
    Bloc updateBloc(long id, AddBlocRequest upbloc);

    Bloc addBlocAndAsigneToFoyer(long idFoyer, Bloc bloc);
    Bloc affecterChambresABloc(List<Long> idChambre, Long idBloc);

    public ResponseEntity<String> addBlocToFoyer(AddBlocRequest request);

    public void testSchedulure();
}
