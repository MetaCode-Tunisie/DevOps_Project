package tn.esprit.devops_project;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.devops_project.Iservices.IStockService;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.List;

@Component
@AllArgsConstructor

public class StockServiceImpl implements IStockService {


    private final StockRepository stockRepository;
    private static final int MAX_TITLE_LENGTH = 50; // Définissez la longueur maximale souhaitée

   /* @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }*/
   @Override
   public Stock addStock(Stock stock) {

       // Vérifier si le titre du stock est trop long
       if (stock.getTitle() != null && stock.getTitle().length() > MAX_TITLE_LENGTH) {
           // Le titre du stock est trop long, retourner null
           return null;
       }


       // Vérifier si un stock avec le même titre existe déjà
       Stock existingStock = stockRepository.findByTitle(stock.getTitle());

       if (existingStock == null) {
           // Aucun stock avec ce titre n'existe, nous pouvons l'ajouter
           return stockRepository.save(stock);
       } else {
           // Un stock avec le même titre existe déjà, retourner null
           return null;
       }
   }


    @Override
    public Stock retrieveStock(Long id) {
        return stockRepository.findById(id).orElseThrow(() -> new NullPointerException("Stock not found"));
    }

    @Override
    public List<Stock> retrieveAllStock() {
        return stockRepository.findAll();
    }



}
