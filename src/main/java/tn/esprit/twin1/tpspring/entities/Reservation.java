package tn.esprit.twin1.tpspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Reservation")
public class Reservation {
    @Id
    private String idReservation;

    private LocalDate anneUniversitere;

    boolean estValide;

    @ManyToMany
    List<Etudiant> etudiants;
}
