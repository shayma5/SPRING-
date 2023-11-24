package tn.esprit.twin1.tpspring.services;

import tn.esprit.twin1.tpspring.entities.Bloc;

import java.util.List;

public interface BlocService {
    Bloc addBloc(Bloc bloc);

    List<Bloc> getAllBloc();

    Bloc findBlocById(Long id);

    String deleteBlocById(Long id);
    Bloc updateBloc(long id, Bloc upbloc);

    Bloc addBlocAndAsigneToFoyer(long idFoyer, Bloc bloc);
    Bloc affecterChambresABloc(List<Long> idChambre, Long idBloc);
}
