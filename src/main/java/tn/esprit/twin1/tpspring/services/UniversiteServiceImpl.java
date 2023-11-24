package tn.esprit.twin1.tpspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.tpspring.entities.Foyer;
import tn.esprit.twin1.tpspring.repositories.FoyerRepositorie;
import tn.esprit.twin1.tpspring.repositories.UniversiteRepositorie;
import tn.esprit.twin1.tpspring.entities.Universite;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversiteServiceImpl implements UniversiteService{

    private final UniversiteRepositorie universiteRepositorie;
    private final FoyerRepositorie foyerRepositorie;

    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepositorie.save(universite);
    }

    @Override
    public List<Universite> findAllUniversute() {
        return universiteRepositorie.findAll();
    }

    @Override
    public Universite findUniversiteById(Long id) {
        return universiteRepositorie.findById(id).orElse(null);
    }

    @Override
    public String deleteUniversuteById(Long id) {
        if(universiteRepositorie.findById(id).isPresent()){
            universiteRepositorie.deleteById(id);
            return "Deleted"+universiteRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";
    }

    @Override
    public Universite updateUniversite(long id, Universite upuniversite) {
        Universite universite = universiteRepositorie.findById(id).orElse(null);

        universite.setNomUniversite(upuniversite.getNomUniversite());
        universite.setAdresse(upuniversite.getAdresse());

        return  universiteRepositorie.save(universite);
    }

    @Override
    public Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepositorie.findById(idFoyer).orElse(null);

        Universite universite = universiteRepositorie.findByNomUniversite(nomUniversite);

        universite.setFoyer(foyer);
        return universiteRepositorie.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(Long idUniversite) {
        Universite universite = universiteRepositorie.findById(idUniversite).orElse(null);

        universite.setFoyer(null);
        return universiteRepositorie.save(universite);
    }
}
