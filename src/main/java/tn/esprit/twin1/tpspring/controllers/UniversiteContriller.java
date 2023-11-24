package tn.esprit.twin1.tpspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.tpspring.services.UniversiteService;
import tn.esprit.twin1.tpspring.entities.Universite;

import java.util.List;

@RequestMapping("/universite")
@RequiredArgsConstructor
@RestController
public class UniversiteContriller {
    private final UniversiteService universiteService;

    @PostMapping("/new")
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @PutMapping("/update/{idUniversite}")
    public Universite updateUniversite(@PathVariable long idUniversite,@RequestBody Universite universite) {
        return universiteService.updateUniversite(idUniversite,universite);
    }

    @GetMapping("/getId/{idUniversite}")
    public Universite getId(@PathVariable long idUniversite) {
        return universiteService.findUniversiteById(idUniversite);
    }

    @GetMapping("/getAll")
    public List<Universite>  getAll() {
        return universiteService.findAllUniversute();
    }

    @DeleteMapping("/delete/{idUniversite}")
    public ResponseEntity<String> deleteUniversite(@PathVariable long idUniversite) {
        try {
            Universite universite = universiteService.findUniversiteById(idUniversite);
            if ( universite != null) {
                universiteService.deleteUniversuteById(idUniversite);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions with a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable Long idFoyer, @PathVariable String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PutMapping("/desaffecterFoyer/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable Long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }

}
