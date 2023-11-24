package tn.esprit.twin1.tpspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.tpspring.entities.Etudiant;
import tn.esprit.twin1.tpspring.services.EtudiantService;
import java.util.List;

@RequestMapping("/etudiant")
@RequiredArgsConstructor
@RestController

public class EtudiantController
{
 private final EtudiantService etudiantService;

 @GetMapping("/getAll")
 public ResponseEntity<List<Etudiant>> getAllEtudiant() {
  List<Etudiant> etudiants = etudiantService.findAllEtudiant();
  return new ResponseEntity<>(etudiants, HttpStatus.OK);
 }

 @RequestMapping(value = "new", method = RequestMethod.POST)
 public String saveEtudiant(@RequestBody Etudiant etudiant) {
  etudiantService.addEtudiant(etudiant);
  return "redirect:/listEtudiant/" + etudiant.getIdEtudiant();
 }

 @DeleteMapping("/delete/{idEtudiant}")
 public ResponseEntity<String> deleteEtudiantC(@PathVariable long idEtudiant) {
  try {
   Etudiant etudiant = etudiantService.findById(idEtudiant);
   if (etudiant != null) {
    etudiantService.deleteEtudiant(etudiant);
    return new ResponseEntity<>(HttpStatus.OK);
   }
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  } catch (Exception e) {
   // Handle other exceptions with a 500 Internal Server Error
   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
 }

 @PutMapping("/update/{idEtudiant}")
 public Etudiant updateEtudiant(@PathVariable long idEtudiant,@RequestBody Etudiant etudiant) {
  return etudiantService.updateEtudiant(idEtudiant,etudiant);
 }

 @GetMapping("/getId/{idEtudiant}")
 public Etudiant getId(@PathVariable long idEtudiant) {
  return etudiantService.findById(idEtudiant);
 }
}
