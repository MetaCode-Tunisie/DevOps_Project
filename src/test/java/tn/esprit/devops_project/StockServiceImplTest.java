
package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest()
class StockServiceImplTest {

   // @Mock
   @Autowired
   private  StockRepository stockRepository;

    //@InjectMocks
    @Autowired
    private StockServiceImpl StockService;



    @Test
    void addStock() {
        // Scenario 1: Stock is added successfully

        Stock stock = new Stock();
        stock.setTitle("nom stock");
        // When
        Stock result = StockService.addStock(stock);
        // Then
         assertThat(result).isNotNull();
         assertThat(result.getIdStock()).isNotNull();

        // Scenario 2: Stock with the same title already exists
       // Stock existingStock = new Stock();
        //existingStock.setTitle("nom du stock");
        //Stock duplicateStock = StockService.addStock(existingStock);
        //assertThat(duplicateStock).isNull(); // Vérifie que l'ajout d'un stock avec un titre existant renvoie null

        // Scenario 4: Stock title is too long
       //Stock stockWithLongTitle = new Stock();
        //stockWithLongTitle.setTitle("This is a very long stock title that exceeds the maximum allowed length.");
        //Stock longTitleStock = StockService.addStock(stockWithLongTitle);

        //assertThat(longTitleStock).isNull(); // Verify that adding a stock with a long title returns null
    }


   /* @Test
    void retrieveAllStockNoStocksInDatabase() {
        // Simulez une erreur de base de données (par exemple, en configurant le mock stockRepository pour lancer une exception)

        // Exécutez la méthode retrieveAllStock
        List<Stock> result = StockService.retrieveAllStock();

        // Assurez-vous que la liste résultante est vide ou qu'elle gère correctement l'erreur
        assertThat(result.size()).isEqualTo(0); // Vérifiez que la taille de la liste est égale à 0
    }*/



    @Test
    void retrieveAllStockWithExistingStocks() {
        // Ajoutez un ou plusieurs stocks en base de données

        // Exécutez la méthode retrieveAllStock
        List<Stock> result = StockService.retrieveAllStock();

        // Assurez-vous que la liste résultante n'est pas vide (c'est-à-dire qu'elle n'est pas nulle)
        assertThat(result).isNotNull();

        // Assurez-vous que la taille de la liste résultante correspond au nombre de stocks dans la base de données
        int expectedSize = 1;

        // Utilisez ListAssert pour vérifier la taille
        assertThat(result).asList().hasSize(expectedSize);

        // Vous pouvez également vérifier d'autres propriétés des stocks récupérés si nécessaire
    }



    @Test
    void retrieveAllStockWithRepository() {
        // Ajoutez plusieurs stocks en base de données

        // Exécutez la méthode retrieveAllStock à l'aide du stockRepository
        List<Stock> result = stockRepository.findAll();

        // Assurez-vous que la liste résultante n'est pas nulle
        assertThat(result).isNotNull();

        // Assurez-vous que la taille de la liste résultante correspond au nombre de stocks dans la base de données
        int expectedSize = 1;
        assertThat(result).asList().hasSize(expectedSize);

        // Vous pouvez également vérifier d'autres propriétés des stocks récupérés si nécessaire
    }



    // Using Mockito Method
   /* @Test
    public void testAddStockSuccessfully() {
        Stock stock = new Stock();
        stock.setTitle("nom du stock");

        // Mock le comportement du stockRepository
        when(stockRepository.findByTitle("nom du stock")).thenReturn(null);
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock result = StockService.addStock(stock);

        assertThat(result).isNotNull();
        assertThat(result.getIdStock()).isNotNull();

        // Vérifie que les méthodes du mock ont été appelées correctement
        verify(stockRepository).findByTitle("nom du stock");
        verify(stockRepository).save(stock);
    }

    @Test
    public void testAddStockWithDuplicateTitle() {
        Stock existingStock = new Stock();
        existingStock.setTitle("nom du stock");

        Stock stock = new Stock();
        stock.setTitle("nom du stock");

        // Mock le comportement du stockRepository
        when(stockRepository.findByTitle("nom du stock")).thenReturn(existingStock);

        Stock result = StockService.addStock(stock);

        assertThat(result).isNull();

        // Vérifie que les méthodes du mock ont été appelées correctement
        verify(stockRepository).findByTitle("nom du stock");
        verifyNoMoreInteractions(stockRepository);
    }
*/




}