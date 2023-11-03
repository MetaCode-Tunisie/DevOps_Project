package tn.esprit.devops_project.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {
    List<Operator> operatorList = new ArrayList<Operator>(){{
           add( new Operator(1L,"khalil","chargui","root"));
        add( new Operator(2L,"hazem","bayoudh","xd"));

    }};
    @InjectMocks
    private OperatorServiceImpl operatorService;



    @Mock
    private OperatorRepository operatorRepository;


    @Test
    void retrieveAllOperators() {

            Mockito.when(operatorService.retrieveAllOperators()).thenReturn(operatorList);
            List<Operator> list = operatorService.retrieveAllOperators();
        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list).isEqualTo(operatorList);
    }

     @Test
    void addOperator() {
        // Create a mock operator
        Operator operator = new Operator().builder().fname("Salem").lname("AA").password("dqdz").build();

        // Stub the behavior of the mocked repository when saving an operator
        Mockito.when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.addOperator(operator);

        assertThat(result).isNotNull();
        assertThat(result.getFname()).isEqualTo("Salem");

        // Verify that the repository's save method was called
        Mockito.verify(operatorRepository).save(operator);
    }
    @Test
    void retrieveOperator() {
        Long idToRetrieve = 1L;

        // Create a mock operator
        Operator operator = new Operator().builder().password("dzq").build();
        operator.setIdOperateur(idToRetrieve);

        // Stub the behavior of the mocked repository when retrieving an operator
        Mockito.when(operatorRepository.findById(idToRetrieve)).thenReturn(Optional.of(operator));

        Operator result = operatorService.retrieveOperator(idToRetrieve);

        assertThat(result.getIdOperateur()).isEqualTo(idToRetrieve);
        assertThat(result).isNotNull();
    }
}
    /*
    @Test
    void deleteOperator() {
        Long idToDelete = 33L;

        // Stub the behavior of the mocked repository when deleting an operator
        when(operatorRepository.findById(idToDelete)).thenReturn(Optional.empty());

        operatorService.deleteOperator(idToDelete);

        // Verify that the repository's delete method was called
        verify(operatorRepository).deleteById(idToDelete);
    }

    @Test
    void updateOperator() {
        // Create a mock operator
        Operator operator = new Operator().builder().password("newPassword").build();

        // Stub the behavior of the mocked repository when updating an operator
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.updateOperator(operator);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(operator);

        // Verify that the repository's save method was called
        verify(operatorRepository).save(operator);
    }

    @Test
    void retrieveOperator() {
        Long idToRetrieve = 9L;

        // Create a mock operator
        Operator operator = new Operator().builder().password("dzq").build();
        operator.setIdOperateur(idToRetrieve);

        // Stub the behavior of the mocked repository when retrieving an operator
        when(operatorRepository.findById(idToRetrieve)).thenReturn(Optional.of(operator));

        Operator result = operatorService.retrieveOperator(idToRetrieve);

        assertThat(result.getIdOperateur()).isEqualTo(idToRetrieve);
        assertThat(result).isNotNull();
    }
*/

