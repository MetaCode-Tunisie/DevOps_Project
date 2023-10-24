package tn.esprit.devops_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.devops_project.entities.ActivitySector;

import java.util.Optional;

public interface ActivitySectorRepository extends JpaRepository<ActivitySector, Long> {

ActivitySector findByCodeSecteurActivite(String code);
}
