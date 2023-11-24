package tn.esprit.twin1.tpspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.tpspring.entities.Foyer;
import tn.esprit.twin1.tpspring.services.FoyerService;

@RequestMapping("/foyer")
@RequiredArgsConstructor
@RestController
public class FoyerController {
    private final FoyerService foyerService;

    @PostMapping("/new")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @PutMapping("/update/{idFoyer}")
    public Foyer updateFoyer(@PathVariable long idFoyer,@RequestBody Foyer foyer) {
        return foyerService.updateFoyer(idFoyer,foyer);
    }

    @GetMapping("/getId/{idFoyer}")
    public Foyer getId(@PathVariable long idFoyer) {
        return foyerService.findFoyerById(idFoyer);
    }

    @DeleteMapping("/delete/{idFoyer}")
    public ResponseEntity<String> deleteFoyer(@PathVariable long idFoyer) {
        try {
            Foyer foyer = foyerService.findFoyerById(idFoyer);
            if ( foyer != null) {
                foyerService.deleteFoyerById(idFoyer);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions with a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new/uni/{idUni}")
    public Foyer addFoyerAndAsigneToUni(@PathVariable long idUni, Foyer foyer) {
        return foyerService.addFoyerAndAsigneToUni(idUni, foyer);
    }

}
