package tn.esprit.twin1.tpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(includeFieldNames = false)
@Table(name = "Etudiant")
public class Etudiant {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long idEtudiant;
    @Column(name = "nomEt",nullable = false)
    private String nomEt;
    @Column(name = "prenomEt",nullable = false)
    private String prenomEt;
    @Column(name = "cin",nullable = false)
    private long cin;
    String ecole;

    @Temporal(TemporalType.DATE)
    LocalDate dateNaissance;


    @ManyToMany(mappedBy="etudiants")
    @JsonIgnore
    Set<Reservation> reservations;
}
