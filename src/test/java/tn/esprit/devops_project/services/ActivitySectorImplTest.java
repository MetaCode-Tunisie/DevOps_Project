package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;





@SpringBootTest
class ActivitySectorImplTest {



    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    private ActivitySectorImpl activitySectorSercive;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addActivitySector() {

        ActivitySectorImpl activitySectorService = Mockito.mock(ActivitySectorImpl.class);

        ActivitySector activitySector = new ActivitySector().builder()
                .codeSecteurActivite("sarra")
                .libelleSecteurActivite("s")
                .build();

        Mockito.when(activitySectorRepository.save(activitySector)).thenReturn(activitySector);

        ActivitySector result = activitySectorRepository.save(activitySector);

        assertThat(result).isEqualTo(activitySector);
        assertThat(result).isNotNull();
    }


    @Test
    void retrieveActivitySector() {
        ActivitySectorImpl activitySectorService = mock(ActivitySectorImpl.class);

        ActivitySector expectedActivitySector = new ActivitySector();
        expectedActivitySector.setIdSecteurActivite(1L);

        when(activitySectorService.retrieveActivitySector(1L)).thenReturn(expectedActivitySector);

        ActivitySector result = activitySectorService.retrieveActivitySector(1L);

        assertThat(result).isNotNull();

        verify(activitySectorService, times(1)).retrieveActivitySector(1L); // Vérifiez que la méthode a été appelée une fois avec l'argument 1L
    }






    @Test
   void updateActivitySector() {
       ActivitySectorImpl activitySectorService = Mockito.mock(ActivitySectorImpl.class);

       Long existingActivitySectorId = 2L;
       ActivitySector existingActivitySector = new ActivitySector();
       existingActivitySector.setIdSecteurActivite(existingActivitySectorId);
       existingActivitySector.setCodeSecteurActivite("Nouveau code");

       Mockito.when(activitySectorService.updateActivitySector(existingActivitySector)).thenReturn(existingActivitySector);

       ActivitySector updatedActivitySector = activitySectorService.updateActivitySector(existingActivitySector);

       assertThat(updatedActivitySector).isNotNull();
       assertThat(updatedActivitySector.getIdSecteurActivite()).isEqualTo(existingActivitySectorId);
       assertThat(updatedActivitySector.getCodeSecteurActivite()).isEqualTo("Nouveau code");
   }
}