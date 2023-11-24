package tn.esprit.twin1.tpspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.tpspring.repositories.FoyerRepositorie;
import tn.esprit.twin1.tpspring.repositories.UniversiteRepositorie;
import tn.esprit.twin1.tpspring.entities.Foyer;
import tn.esprit.twin1.tpspring.entities.Universite;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoyerServiceImpl implements FoyerService {

    private final FoyerRepositorie foyerRepositorie;
    private final UniversiteRepositorie universiteRepositorie;

    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepositorie.save(foyer);
    }

    @Override
    public List<Foyer> findAllFoyer() {
        return foyerRepositorie.findAll();
    }

    @Override
    public Foyer findFoyerById(Long id) {
        return foyerRepositorie.findById(id).isPresent() ? foyerRepositorie.findById(id).get() : null;
    }

    @Override
    public String deleteFoyerById(Long id) {
        if(foyerRepositorie.findById(id).isPresent()){
            foyerRepositorie.deleteById(id);
            return "Deleted"+foyerRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";
    }

    @Override
    public Foyer updateFoyer(long id, Foyer upfoyer) {
        Foyer foyer = foyerRepositorie.findById(id).orElse(null);

        foyer.setNomFoyer(upfoyer.getNomFoyer());
        foyer.setCapaciteFoyer(upfoyer.getCapaciteFoyer());


        return  foyerRepositorie.save(foyer);
    }

    @Override
    public Foyer addFoyerAndAsigneToUni(long idUni, Foyer foyer) {

        Universite universite = universiteRepositorie.findById(idUni).orElse(null);
        foyer.setUniversite(universite);
        return foyerRepositorie.save(foyer);
    }
}
