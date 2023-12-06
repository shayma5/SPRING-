package tn.esprit.twin1.tpspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.tpspring.dto.AddBlocRequest;
import tn.esprit.twin1.tpspring.dto.AddChambreDto;
import tn.esprit.twin1.tpspring.dto.BlocDto;
import tn.esprit.twin1.tpspring.dto.ChambreDto;
import tn.esprit.twin1.tpspring.entities.Bloc;
import tn.esprit.twin1.tpspring.entities.Foyer;
import tn.esprit.twin1.tpspring.entities.TypeChambre;
import tn.esprit.twin1.tpspring.repositories.BlocRepositorie;
import tn.esprit.twin1.tpspring.repositories.ChambreRepositorie;
import tn.esprit.twin1.tpspring.repositories.FoyerRepositorie;
import tn.esprit.twin1.tpspring.services.ChambreService;
import tn.esprit.twin1.tpspring.entities.Chambre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chambre")
@RequiredArgsConstructor
@RestController
public class ChambreController {

    private final ChambreService chambreService;

    /*@PostMapping("/new")
    public Chambre addFoyer(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }*/

    @PostMapping("/new/bloc")
    public ResponseEntity<String> addChambreToBloc(@RequestBody AddChambreDto chambre) {
        chambreService.addChambreToBloc(chambre);
        return ResponseEntity.ok("chambre added to bloc successfully");
    }

    private final ChambreRepositorie chambreRepositorie;
    @GetMapping("/chambres")
    public List<ChambreDto> getAllchambres() {
        List<Chambre> chambres = chambreRepositorie.findAll();
        List<ChambreDto> chambreDtos = new ArrayList<>();

        for (Chambre chambre : chambres) {
            ChambreDto chambreDto = new ChambreDto();
            chambreDto.setIdChambre(chambre.getIdChambre());
            chambreDto.setNumeroChambre(chambre.getNumeroChambre());
            chambreDto.setTypeC(chambre.getTypeC());
            chambreDto.setNomBloc(chambre.getBloc().getNomBloc());


            chambreDtos.add(chambreDto);
        }

        return chambreDtos;
    }

    private final BlocRepositorie blocRepositorie;
    @GetMapping("/blocnames")
    public List<String> getBlocNames() {
        List<Bloc> blocs = blocRepositorie.findAll();
        return blocs.stream()
                .map(Bloc::getNomBloc)
                .collect(Collectors.toList());
    }

    /*@GetMapping("/types")
    public List<String> getChambreTypes() {
        return Arrays.stream(TypeChambre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }*/

    @PutMapping("/update/{idChambre}")
    public Chambre updateChambre(@PathVariable long idChambre,@RequestBody AddChambreDto chambre) {
        return chambreService.updateChambre(idChambre,chambre);
    }

    /*@GetMapping("/all")
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambre();
    }*/

    @GetMapping("/getId/{idChambre}")
    public Chambre getId(@PathVariable long idChambre) {
        return chambreService.findChambreById(idChambre);
    }

    @DeleteMapping("/delete/{idChambre}")
    public ResponseEntity<String> deletechambre(@PathVariable long idChambre) {
        try {
            Chambre chambre = chambreService.findChambreById(idChambre);
            if ( chambre != null) {
                chambreService.deleteChambreById(idChambre);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions with a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/getChambresParBlocEtType/{idBloc}/{typeC}")
    public List<Chambre> getChambresParBlocEtType(@PathVariable Long idBloc, @PathVariable TypeChambre typeChambre) {
        return chambreService.getChambresParBlocEtType(idBloc, typeChambre);
    }*/
}
