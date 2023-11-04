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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;





@ExtendWith(MockitoExtension.class)

class ActivitySectorImplTest {


    List<ActivitySector> operatorList = new ArrayList<>(){{
        add( new ActivitySector(1L,"khalil","chargui"));
        add( new ActivitySector(2L,"hazem","bayoudh"));

    }};
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


            Long idToRetrieve = 1L;

            ActivitySector activitySector = new ActivitySector().builder().codeSecteurActivite("dzq").build();
        activitySector.setIdSecteurActivite(idToRetrieve);

            Mockito.when(activitySectorRepository.findById(idToRetrieve)).thenReturn(Optional.of(activitySector));

            ActivitySector result = activitySectorSercive.retrieveActivitySector(idToRetrieve);

            assertThat(result.getIdSecteurActivite()).isEqualTo(idToRetrieve);
            assertThat(result).isNotNull();

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