package tn.esprit.twin1.tpspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.tpspring.entities.TypeChambre;
import tn.esprit.twin1.tpspring.repositories.ChambreRepositorie;
import tn.esprit.twin1.tpspring.entities.Chambre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements ChambreService {

    private final ChambreRepositorie chambreRepositorie;

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepositorie.save(chambre);
    }

    @Override
    public List<Chambre> getAllChambre() {
        return chambreRepositorie.findAll();
    }

    @Override
    public Chambre findChambreById(Long id) {
        return chambreRepositorie.findById(id).isPresent() ? chambreRepositorie.findById(id).get() : null;
    }

    @Override
    public String deleteChambreById(Long id) {
        if(chambreRepositorie.findById(id).isPresent()){
            chambreRepositorie.deleteById(id);
            return "Deleted"+chambreRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";
    }

    @Override
    public Chambre updateChambre(long id, Chambre upchambre) {
        Chambre chambre = chambreRepositorie.findById(id).orElse(null);

        chambre.setNumeroChambre(upchambre.getNumeroChambre());
        chambre.setTypeC(upchambre.getTypeC());


        return  chambreRepositorie.save(chambre);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(Long idBloc, TypeChambre typeC) {
        return chambreRepositorie.getChambresParBlocEtType(idBloc, typeC);
    }
}
