package tn.esprit.twin1.tpspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.tpspring.repositories.EtudiantRepositorie;
import tn.esprit.twin1.tpspring.entities.Etudiant;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl  implements EtudiantService{


    private final EtudiantRepositorie etudiantRepositorie ;

    @Override
    public Etudiant addEtudiant(Etudiant etudiant){
        return etudiantRepositorie.save(etudiant);
    }

    @Override
    public List<Etudiant> findAllEtudiant(){
        return (List<Etudiant>)etudiantRepositorie.findAll();
    }

    @Override
    public Etudiant findById(Long id){
        return etudiantRepositorie.findById(id).isPresent() ? etudiantRepositorie.findById(id).get() : null;
    }

    @Override
    public String deleteEtudiant(Etudiant etudiant){
        if(etudiantRepositorie.findById(etudiant.getIdEtudiant()).isPresent()){
            etudiantRepositorie.delete(etudiant);
            return "Deleted"+etudiant.toString();
        }else
            return etudiant.toString()+"Doesn't exist";
    }

    @Override
    public Etudiant updateEtudiant(long idEtudiant, Etudiant updatedEt) {

        Etudiant etudiant = etudiantRepositorie.findById(idEtudiant).orElse(null);

        etudiant.setNomEt(updatedEt.getNomEt());
        etudiant.setPrenomEt(updatedEt.getPrenomEt());
        etudiant.setEcole(updatedEt.getEcole());
        etudiant.setDateNaissance(updatedEt.getDateNaissance());
        etudiant.setCin(updatedEt.getCin());

        return  etudiantRepositorie.save(etudiant);
    }



}
