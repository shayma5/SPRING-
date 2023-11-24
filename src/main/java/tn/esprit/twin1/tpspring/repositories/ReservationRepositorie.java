package tn.esprit.twin1.tpspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin1.tpspring.entities.Reservation;

import java.time.LocalDate;

public interface ReservationRepositorie  extends JpaRepository<Reservation,String> {
    boolean existsByEtudiantsCinAndAnneUniversitereBetween(long etudiants_cin, LocalDate startDate, LocalDate endDate);
}
