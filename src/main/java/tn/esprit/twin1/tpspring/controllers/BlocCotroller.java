package tn.esprit.twin1.tpspring.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.tpspring.dto.AddBlocRequest;
import tn.esprit.twin1.tpspring.entities.Foyer;
import tn.esprit.twin1.tpspring.repositories.BlocRepositorie;
import tn.esprit.twin1.tpspring.repositories.FoyerRepositorie;
import tn.esprit.twin1.tpspring.services.BlocService;
import tn.esprit.twin1.tpspring.entities.Bloc;
import tn.esprit.twin1.tpspring.dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bloc")
@RequiredArgsConstructor
@RestController
@Slf4j
public class BlocCotroller {
    private final BlocRepositorie blocRepositorie;
    private final BlocService blocService;

    @PostMapping("/new")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @PutMapping("/update/{idBloc}")
    public Bloc updateFoyer(@PathVariable long idBloc,@RequestBody AddBlocRequest bloc) {
        return blocService.updateBloc(idBloc,bloc);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Bloc>> getAllEtudiant() {
        List<Bloc> etudiants = blocService.getAllBloc();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @GetMapping("/blocs")
    public List<BlocDto> getAllBlocs() {
        List<Bloc> blocs = blocRepositorie.findAll();
        List<BlocDto> blocDtos = new ArrayList<>();

        for (Bloc bloc : blocs) {
            BlocDto blocDto = new BlocDto();
            blocDto.setIdBloc(bloc.getIdBloc());
            blocDto.setNomBloc(bloc.getNomBloc());
            blocDto.setCapaciteBloc(bloc.getCapaciteBloc());
            blocDto.setNomFoyer(bloc.getFoyer().getNomFoyer()); // Assuming Foyer has a 'name' property

            blocDtos.add(blocDto);
        }

        return blocDtos;
    }

    @GetMapping("/getId/{idBloc}")
    public Bloc getId(@PathVariable long idBloc) {
        return blocService.findBlocById(idBloc);
    }

    @DeleteMapping("/delete/{idBloc}")
    public ResponseEntity<String> deleteFoyer(@PathVariable long idBloc) {
        try {
            Bloc bloc = blocService.findBlocById(idBloc);
            if (bloc != null) {
                blocService.deleteBlocById(idBloc);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions with a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/affecterBlocFoyer/{idFoyer}")
    public Bloc addBlocAndAsigneToFoyer(@PathVariable long idFoyer,@RequestBody Bloc bloc) {
        return blocService.addBlocAndAsigneToFoyer(idFoyer, bloc);
    }

    @PutMapping("/affecterChambres/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> idChambre, @PathVariable Long idBloc) {
        return blocService.affecterChambresABloc(idChambre, idBloc);
    }


    @PostMapping("/foyers/addBloc")
    public ResponseEntity<String> addBlocToFoyer(@RequestBody AddBlocRequest request) {
        blocService.addBlocToFoyer(request);
        return ResponseEntity.ok("Bloc added to Foyer successfully");
    }


    private final FoyerRepositorie foyerRepository;
    @GetMapping("/foyernames")
    public List<String> getFoyerNames() {
        List<Foyer> foyers = foyerRepository.findAll();
        return foyers.stream()
                .map(Foyer::getNomFoyer)
                .collect(Collectors.toList());
    }


    @GetMapping("{date}")
    public void dateTest(@PathVariable LocalDate date){
        log.info(String.valueOf(date));
    }
}
