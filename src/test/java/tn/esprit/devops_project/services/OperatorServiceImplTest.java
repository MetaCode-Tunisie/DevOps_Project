package tn.esprit.devops_project.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.controllers.OperatorController;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OperatorServiceImplTest {
    @Autowired
    private OperatorServiceImpl  operatorService;

    @Autowired
    private OperatorController operatorController;

    @Autowired
    private OperatorRepository operatorRepository;
    @BeforeEach

    @Test
    void retrieveAllOperators() {
     List<Operator> operatorList = operatorController.getOperators();
     assertThat(operatorList).isNotNull();
     assertThat(operatorList).isNotEmpty();

    }

    @Test
    void addOperator() {
        Operator operator = new Operator().builder().fname("salem").lname("aa").password("dqdz").build();

        Operator result = operatorService.addOperator(operator);



        assertThat(result).isEqualTo(operator);
        assertThat(result).isNotNull();
        assertThat(result.getFname()).isEqualTo("salem");
        Operator operatorfromdatabase = operatorRepository.findById(result.getIdOperateur()).orElse(null);
        assertThat(operatorfromdatabase).isNotNull();
        assertThat(operatorfromdatabase.getFname()).isEqualTo("salem");
    }

    @Test
    void deleteOperator() {
        Long idToDelete = 17L;
         operatorService.deleteOperator(idToDelete);
        assertThat(idToDelete).isNotNull();
        Operator operatorfromdatabase = operatorRepository.findById(5L).orElse(null);
        assertThat(operatorfromdatabase).isNull();
    }

    @Test
    void updateOperator() {
        Operator operator = new Operator().builder().password("dzqdzdzdmmmmm").build();
        Operator result = operatorService.updateOperator(operator);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(operator);

    }

    @Test
    void retrieveOperator() {
        Operator operator = new Operator().builder().password("dzq").build();
         operator.setIdOperateur(9L);
        Operator result = operatorService.retrieveOperator(9L);
            assertThat(result.getIdOperateur()).isEqualTo(9L);
        assertThat(result).isNotNull();


    }
}