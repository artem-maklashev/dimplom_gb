package org.maklashev.goldengroup.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;

import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.repositories.ProductionListRepository;
import org.maklashev.goldengroup.repositories.gypsumboard.*;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GypsumBoardServiceTest {

    @Mock
    private BoardProductionRepository boardProductionRepository;
    @Mock
    private ProductionListRepository productionListRepository;
    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private GypsumBoardService gypsumBoardService;

    @Test
    @DisplayName("Тест получения записей о плане по дням")
    public void testGetPlanByDateWithData() {
        String startDateValue = "2024-01-01";
        String endDateValue = "2024-01-31";
        LocalDate startDate = LocalDate.parse(startDateValue);
        LocalDate endDate = LocalDate.parse(endDateValue);
        List<Integer> planIds = Arrays.asList(1, 2, 3); // пример данных
        List<Plan> expectedPlans = Arrays.asList(new Plan(/* мок данные */), new Plan(), new Plan()); // пример данных

        when(planRepository.findIdsInDateRange(startDate, endDate)).thenReturn(planIds);
        when(planRepository.findAllById(planIds)).thenReturn(expectedPlans);

        List<Plan> result = gypsumBoardService.getPlanByDate(startDateValue, endDateValue);

        assertEquals(expectedPlans.size(), result.size());
        assertEquals(expectedPlans, result);
        verify(planRepository, times(1)).findIdsInDateRange(startDate, endDate);
        verify(planRepository, times(1)).findAllById(planIds);
    }

    @Test
    @DisplayName("Тест получения планов по дате без данных")
    public void testGetPlanByDateWithoutData() {
        String startDateValue = "2024-01-01";
        String endDateValue = "2024-01-31";
        LocalDate startDate = LocalDate.parse(startDateValue);
        LocalDate endDate = LocalDate.parse(endDateValue);

        when(planRepository.findIdsInDateRange(startDate, endDate)).thenReturn(Collections.emptyList());

        List<Plan> result = gypsumBoardService.getPlanByDate(startDateValue, endDateValue);

        assertTrue(result.isEmpty());
        verify(planRepository, times(1)).findIdsInDateRange(startDate, endDate);

    }

    @Test
    @DisplayName("Тест получения данных о производстве по дате с данными")
    public void testGetBoardProductionByDateWithData() {
        String startDateValue = "2024-01-01";
        String endDateValue = "2024-01-31";
        LocalDateTime startDate = LocalDate.parse(startDateValue).atTime(8, 0);
        LocalDateTime endDate = LocalDate.parse(endDateValue).atTime(8, 0);
        List<Long> productionListIds = Arrays.asList(1L, 2L, 3L); // пример данных
        List<BoardProduction> expectedBoardProductions = List.of(new BoardProduction()); // пример данных

        when(productionListRepository.findIdsInDateRange(startDate, endDate)).thenReturn(productionListIds);
        when(boardProductionRepository.findAllByProductionListIdIn(productionListIds)).thenReturn(expectedBoardProductions);

        List<BoardProduction> result = gypsumBoardService.getBoardProductionByDate(startDateValue, endDateValue);

        assertEquals(expectedBoardProductions.size(), result.size());
        assertEquals(expectedBoardProductions, result);
        verify(productionListRepository, times(1)).findIdsInDateRange(startDate, endDate);
        verify(boardProductionRepository, times(1)).findAllByProductionListIdIn(productionListIds);
    }

    @Test
    @DisplayName("Тест получения данных о производстве по дате без данных")
    public void testGetBoardProductionByDateWithoutData() {
        String startDateValue = "2024-01-01";
        String endDateValue = "2024-01-31";
        LocalDateTime startDate = LocalDate.parse(startDateValue).atTime(8, 0);
        LocalDateTime endDate = LocalDate.parse(endDateValue).atTime(8, 0);

        when(productionListRepository.findIdsInDateRange(startDate, endDate)).thenReturn(Collections.emptyList());

        List<BoardProduction> result = gypsumBoardService.getBoardProductionByDate(startDateValue, endDateValue);

        assertTrue(result.isEmpty());
        verify(productionListRepository, times(1)).findIdsInDateRange(startDate, endDate);
        verifyNoInteractions(boardProductionRepository);
    }
}
