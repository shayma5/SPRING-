package tn.esprit.twin1.tpspring.services;

import tn.esprit.twin1.tpspring.entities.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);

    List<Reservation> findAllReservation();

    Reservation findReservationById(String id);

    String deleteReservationById(String id);
    Reservation updateReservation(Reservation reservation);

    public Reservation ajouterReservation (long idChambre, long cinEtudiant);

    Reservation annulerReservation (Long cinEtudiant) ;
}
