package tn.esprit.twin1.tpspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.tpspring.Exception.ResourceNotFoundException;
import tn.esprit.twin1.tpspring.entities.Chambre;
import tn.esprit.twin1.tpspring.repositories.BlocRepositorie;
import tn.esprit.twin1.tpspring.repositories.ChambreRepositorie;
import tn.esprit.twin1.tpspring.repositories.FoyerRepositorie;
import tn.esprit.twin1.tpspring.entities.Bloc;
import tn.esprit.twin1.tpspring.entities.Foyer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements BlocService{

    private final BlocRepositorie blocRepositorie;
    private final FoyerRepositorie foyerRepositorie;
    private final ChambreRepositorie chambreRepository;

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepositorie.save(bloc);
    }

    @Override
    public List<Bloc> getAllBloc() {
        return blocRepositorie.findAll();
    }

    @Override
    public Bloc findBlocById(Long id) {
        return blocRepositorie.findById(id).isPresent() ? blocRepositorie.findById(id).get() : null;
    }

    @Override
    public String deleteBlocById(Long id) {
        if(blocRepositorie.findById(id).isPresent()){
            blocRepositorie.deleteById(id);
            return "Deleted"+blocRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";
    }

    @Override
    public Bloc updateBloc(long id, Bloc upbloc) {
        Bloc bloc = blocRepositorie.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found tutorial with id "));

        bloc.setNomBloc(upbloc.getNomBloc());
        bloc.setCapaciteBloc(upbloc.getCapaciteBloc());


        return  blocRepositorie.save(bloc);
    }

    @Override
    public Bloc addBlocAndAsigneToFoyer(long idFoyer, Bloc bloc) {
        Foyer foyer = foyerRepositorie.findById(idFoyer).orElseThrow(()->new ResourceNotFoundException("Not found tutorial with id ="+idFoyer));
        bloc.setFoyer(foyer);
        return blocRepositorie.save(bloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> idChambre, Long idBloc) {
        Bloc bloc = blocRepositorie.findById(idBloc).orElseThrow(()->new ResourceNotFoundException("Not found tutorial with id ="+idBloc));

        for(Long id:idChambre){
            Chambre chambre = chambreRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found tutorial with id ="+idChambre));
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }

        return blocRepositorie.save(bloc);
    }
}
