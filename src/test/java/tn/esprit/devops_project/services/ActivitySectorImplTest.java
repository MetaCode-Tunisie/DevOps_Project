package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;




@SpringBootTest

class ActivitySectorImplTest {


    @Mock
    private ActivitySectorRepository activitySectorRepository;

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


   /* @Test
    void retrieveActivitySector() {
        ActivitySectorImpl activitySectorService = Mockito.mock(ActivitySectorImpl.class);

        ActivitySectorRepository activitySectorRepository = Mockito.mock(ActivitySectorRepository.class);

        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(1L);

        Mockito.when(activitySectorRepository.findById(1L)).thenReturn(Optional.of(activitySector));

        Mockito.when(activitySectorService.retrieveActivitySector(1L)).thenReturn(activitySector);

        ActivitySector result = activitySectorService.retrieveActivitySector(1L);

        assertThat(result).isNotNull();
    }*/



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
