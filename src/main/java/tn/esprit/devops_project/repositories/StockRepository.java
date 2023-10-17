package tn.esprit.devops_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devops_project.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByTitle(String title);
}

