package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.devops_project.controllers.ActivitySectorController;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ActivitySectorImplTest {

    @Autowired
    private ActivitySectorImpl activitySectorService;

    @InjectMocks
    private ActivitySectorController activitySectorController;

    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addActivitySector() {
        // Créez un mock du service activitySectorService
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
        // Créez un mock du service activitySectorService
        ActivitySectorImpl activitySectorService = Mockito.mock(ActivitySectorImpl.class);

        ActivitySectorRepository activitySectorRepository = Mockito.mock(ActivitySectorRepository.class);

        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(1L);

        Mockito.when(activitySectorRepository.findById(1L)).thenReturn(Optional.of(activitySector));

        Mockito.when(activitySectorService.retrieveActivitySector(1L)).thenReturn(activitySector);

        ActivitySector result = activitySectorService.retrieveActivitySector(1L);

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