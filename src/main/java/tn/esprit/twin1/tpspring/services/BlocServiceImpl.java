package tn.esprit.twin1.tpspring.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.tpspring.Exception.ResourceNotFoundException;
import tn.esprit.twin1.tpspring.dto.AddBlocRequest;
import tn.esprit.twin1.tpspring.entities.Chambre;
import tn.esprit.twin1.tpspring.repositories.BlocRepositorie;
import tn.esprit.twin1.tpspring.repositories.ChambreRepositorie;
import tn.esprit.twin1.tpspring.repositories.FoyerRepositorie;
import tn.esprit.twin1.tpspring.entities.Bloc;
import tn.esprit.twin1.tpspring.entities.Foyer;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlocServiceImpl implements BlocService{

    private final BlocRepositorie blocRepositorie;
    private final FoyerRepositorie foyerRepositorie;
    private final ChambreRepositorie chambreRepository;

    @Override
    public Bloc addBloc(Bloc bloc  ) {
        Foyer foyer= bloc.getFoyer();
        bloc.setFoyer(foyer);
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
    public void deleteBlocById(long id) {
        blocRepositorie.deleteById(id);

    }

    @Override
    public Bloc updateBloc(long id, AddBlocRequest upbloc) {
        Bloc bloc = blocRepositorie.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found tutorial with id "));
        Foyer foyer = foyerRepositorie.findByNomFoyer(upbloc.getNomFoyer())
                .orElseThrow(() -> new EntityNotFoundException("Foyer not found"));

        bloc.setNomBloc(upbloc.getNomBloc());
        bloc.setCapaciteBloc(upbloc.getCapaciteBloc());
        bloc.setFoyer(foyer);


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

    @Override
    public ResponseEntity<String> addBlocToFoyer(AddBlocRequest request) {
        Foyer foyer = foyerRepositorie.findByNomFoyer(request.getNomFoyer())
                .orElseThrow(() -> new EntityNotFoundException("Foyer not found"));

        Bloc bloc = new Bloc();
        bloc.setNomBloc(request.getNomBloc());
        bloc.setCapaciteBloc(request.getCapaciteBloc());
        bloc.setFoyer(foyer);

        foyer.getBlocs().add(bloc);

        foyerRepositorie.save(foyer);

        return ResponseEntity.ok("Bloc added to Foyer successfully");
    }


    @Override
    @Scheduled(fixedRate = 60000)//lazem nzidou l'annotation @Enablecheduling fil main application
    public void testSchedulure() {
        List<Bloc> blocs = blocRepositorie.findAll();
        log.info("hello !!!!");
        for (Bloc bloc : blocs){
            log.info(String.valueOf(chambreRepository.findChambreByBloc(bloc).size()));
        }
    }

}
