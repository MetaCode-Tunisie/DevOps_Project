package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        ActivitySector activitySector = new ActivitySector().builder().codeSecteurActivite("fddxd").libelleSecteurActivite("ffff").build();



        ActivitySector result = activitySectorService.addActivitySector(activitySector);

        assertThat(result).isEqualTo(activitySector);
        assertThat(result).isNotNull();
    }

    @Test
    void retrieveActivitySector() {
        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(5L);


        ActivitySector result = activitySectorService.retrieveActivitySector(5L);



        ActivitySector activitySector1 = activitySectorRepository.findById(result.getIdSecteurActivite()).orElse(null);
        assertThat(result).isNotNull();
    }

    @Test
    void deleteActivitySector() {
        Long idToDelete = 21L;
        ActivitySector activitySector = activitySectorRepository.findById(idToDelete).orElse(null);
        activitySectorService.deleteActivitySector(idToDelete);
        assertThat(activitySector).isNull();
    }

    @Test
    void updateActivitySector() {
        ActivitySector activitySector = new ActivitySector();


        ActivitySector response = activitySectorService.updateActivitySector(activitySector);

        assertThat(response).isEqualTo(activitySector);


    }
}