package tn.esprit.twin1.tpspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.twin1.tpspring.entities.Chambre;
import tn.esprit.twin1.tpspring.entities.Reservation;
import tn.esprit.twin1.tpspring.entities.TypeChambre;

import java.util.List;

public interface ChambreRepositorie  extends JpaRepository<Chambre,Long> {
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> getChambresParBlocEtType(Long idBloc, TypeChambre typeC);

    Chambre findByReservationsContains(Reservation reservation);
}
