package org.maklashev.goldengroup.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.repositories.ProductionListRepository;
import org.maklashev.goldengroup.repositories.gypsumboard.*;
import org.maklashev.goldengroup.service.GypsumBoardService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GypsumBoardServiceIntegrationTest {

    @Autowired
    private GypsumBoardService gypsumBoardService;

    @MockBean
    private ProductionListRepository productionListRepository;

    @MockBean
    private BoardProductionRepository boardProductionRepository;
    @MockBean
    private ThicknessRepository thicknessRepository;
    @MockBean
    private WidthRepository widthRepository;
    @MockBean
    private GypsumBoardRepository gypsumBoardRepository;
    @MockBean
    private BoardTypeRepository boardTypeRepository;
    @MockBean
    private PlanRepository planRepository;

    @Test
    @DisplayName("Тест получения планов по дате")
    public void testGetPlanByDate() {
        // Подготавливаем тестовые данные
        String startDateValue = "2024-01-01";
        String endDateValue = "2024-01-31";
        LocalDate startDate = LocalDate.parse(startDateValue);
        LocalDate endDate = LocalDate.parse(endDateValue);

        // Устанавливаем поведение productionListRepository
        // возвращать список id планов для указанных дат
        when(productionListRepository.findIdsInDateRange(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .thenReturn(Arrays.asList(1L, 2L, 3L));

        // Вызываем метод, который мы тестируем
        List<Plan> result = gypsumBoardService.getPlanByDate(startDateValue, endDateValue);

        // Проверяем, что результат не null
        assertNotNull(result);

        // Проверяем, что результат содержит только планы из заданного диапазона дат
        for (Plan plan : result) {
            assertTrue(plan.getPlanDate().isEqual(startDate) || plan.getPlanDate().isEqual(endDate) || (plan.getPlanDate().isAfter(startDate) && plan.getPlanDate().isBefore(endDate)));
        }
    }

    @Test
    @DisplayName("Тест получения производства по дате")
    public void testGetBoardProductionByDate() {
        // Подготавливаем тестовые данные
        String startDateValue = "2024-01-01";
        String endDateValue = "2024-01-31";

        // Заглушка списка BoardProduction
        List<BoardProduction> expectedBoardProductions = Arrays.asList(
                new BoardProduction(/*...*/),
                new BoardProduction(/*...*/)
        );

        // Устанавливаем поведение productionListRepository
        // возвращать список id производств для указанных дат
        when(productionListRepository.findIdsInDateRange(LocalDate.parse(startDateValue).atTime(8,0), LocalDate.parse(endDateValue).atTime(8,0)))
                .thenReturn(Arrays.asList(1L, 2L, 3L));

        // Устанавливаем поведение boardProductionRepository
        // возвращать ожидаемые записи производства
        when(boardProductionRepository.findAllByProductionListIdIn(any()))
                .thenReturn(expectedBoardProductions);

        // Вызываем метод, который мы тестируем
        List<BoardProduction> result = gypsumBoardService.getBoardProductionByDate(startDateValue, endDateValue);

        // Проверяем, что результат не null
        assertNotNull(result);

        // Проверяем, что результат содержит ожидаемые записи BoardProduction
        assertEquals(expectedBoardProductions.size(), result.size());
        assertTrue(result.containsAll(expectedBoardProductions));
    }
}
