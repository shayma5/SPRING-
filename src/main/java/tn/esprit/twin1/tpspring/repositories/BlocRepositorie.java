package tn.esprit.twin1.tpspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin1.tpspring.entities.Bloc;
import tn.esprit.twin1.tpspring.entities.Foyer;

import java.util.Optional;

public interface BlocRepositorie  extends JpaRepository<Bloc,Long> {
    Optional<Bloc> findByNomBloc(String nomBloc);
}
