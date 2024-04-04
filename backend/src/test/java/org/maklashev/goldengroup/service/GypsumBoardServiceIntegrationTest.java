package org.maklashev.goldengroup.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maklashev.goldengroup.model.entity.TradeMark;
import org.maklashev.goldengroup.model.entity.Types;
import org.maklashev.goldengroup.model.entity.gypsumboard.*;
import org.maklashev.goldengroup.model.entity.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.entity.production.ProductionList;
import org.maklashev.goldengroup.repositories.ProductionListRepository;
import org.maklashev.goldengroup.repositories.ShiftRepository;
import org.maklashev.goldengroup.repositories.TradeMarkRepository;
import org.maklashev.goldengroup.repositories.TypesRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
    @DisplayName("Тест отсутствия планов по дате")
    public void testGetPlanByDateNoData() {
        // Подготавливаем тестовые данные
        String startDateValue = "2024-01-01";
        String endDateValue = "2024-01-31";
        LocalDate startDate = LocalDate.parse(startDateValue);
        LocalDate endDate = LocalDate.parse(endDateValue);

        // Создаем заглушки (mocks) для всех репозиториев
        ProductionListRepository productionListRepository = mock(ProductionListRepository.class);
        BoardTypeRepository boardTypeRepository = mock(BoardTypeRepository.class);
        ThicknessRepository thicknessRepository = mock(ThicknessRepository.class);
        WidthRepository widthRepository = mock(WidthRepository.class);
        GypsumBoardRepository gypsumBoardRepository = mock(GypsumBoardRepository.class);
        BoardProductionRepository boardProductionRepository = mock(BoardProductionRepository.class);
        PlanRepository planRepository = mock(PlanRepository.class);
        ShiftRepository repository = mock(ShiftRepository.class);
        TypesRepository typesRepository = mock(TypesRepository.class);
        TradeMarkRepository tradeMarkRepository = mock(TradeMarkRepository.class);

        // Устанавливаем поведение для productionListRepository
        // возвращать пустой список id планов для указанных дат
        when(productionListRepository.findIdsInDateRange(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .thenReturn(Arrays.asList());

        // Создаем объект GypsumBoardService, передавая в него заглушки репозиториев
        GypsumBoardService gypsumBoardService = new GypsumBoardService(
                repository, typesRepository, tradeMarkRepository, boardTypeRepository,
                thicknessRepository, widthRepository, gypsumBoardRepository,
                boardProductionRepository, productionListRepository, planRepository);

        // Вызываем метод, который мы тестируем
        List<Plan> result = gypsumBoardService.getPlanByDate(startDateValue, endDateValue);

        // Проверяем, что результат пустой
        assertEquals(0, result.size());
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

    @Test
    void testGetProductionData() {
        GypsumBoard board = new GypsumBoard();
            board.setId(1);
            board.setPType(new Types(1, "test"));
            board.setTradeMark(new TradeMark(1, "test"));
            board.setBoardType(new BoardType(1,"test",""));
            board.setEdge(new Edge(1, "test"));
            board.setThickness(new Thickness(1, "test"));
            board.setWidth(new Width(1, "test"));
            board.setLength(new Length(1, "test"));
        // Создаем заглушки (mocks) для всех репозиториев
        ProductionListRepository productionListRepository = mock(ProductionListRepository.class);
        BoardTypeRepository boardTypeRepository = mock(BoardTypeRepository.class);
        ThicknessRepository thicknessRepository = mock(ThicknessRepository.class);
        WidthRepository widthRepository = mock(WidthRepository.class);
        GypsumBoardRepository gypsumBoardRepository = mock(GypsumBoardRepository.class);
        BoardProductionRepository boardProductionRepository = mock(BoardProductionRepository.class);
        PlanRepository planRepository = mock(PlanRepository.class);
        ShiftRepository repository = mock(ShiftRepository.class);
        TypesRepository typesRepository = mock(TypesRepository.class);
        TradeMarkRepository tradeMarkRepository = mock(TradeMarkRepository.class);

        // Создаем экземпляр сервиса, передавая в него заглушки репозиториев
        GypsumBoardService gypsumBoardService = new GypsumBoardService(
                repository, typesRepository, tradeMarkRepository, boardTypeRepository,
                thicknessRepository, widthRepository, gypsumBoardRepository,
                boardProductionRepository, productionListRepository, planRepository);

        // Подготавливаем тестовые данные
        List<BoardProduction> boardProductions = Arrays.asList(
                new BoardProduction(1, new ProductionList(), board, new GypsumBoardCategory(2, "2"), 10000),
                new BoardProduction(2, new ProductionList(), board, new GypsumBoardCategory(3, "3"), 2000),
                new BoardProduction(3, new ProductionList(), board, new GypsumBoardCategory(4, "4"), 3000),
                new BoardProduction(4, new ProductionList(), board, new GypsumBoardCategory(6, "6"), 1000),
                new BoardProduction(5, new ProductionList(), board, new GypsumBoardCategory(1, "1"), 35000)
        );
        List<Plan> planList = Arrays.asList(
                new Plan(1, LocalDate.parse("2024-01-01"), board, 10000),
                new Plan(2, LocalDate.parse("2024-01-02"), board, 20000)
        );

        Float expectedPlanValue = planList.stream().map(Plan::getPlanValue).reduce(0f, Float::sum);
        Float expectedProductionValue = boardProductions.stream().filter(bp-> bp.getProductionList().getId() == 1).map(BoardProduction::getValue).reduce(0f, Float::sum);
        //To-Do
        // Устанавливаем поведение для методов репозиториев
        when(boardProductionRepository.findAll()).thenReturn(boardProductions);
        when(planRepository.findAll()).thenReturn(planList);

        // Вызываем метод, который мы тестируем
        List<GypsumBoardProductionData> result = gypsumBoardService.getProductionData(boardProductions, planList);

        // Проверяем, что результат соответствует ожидаемому
        // Напишите проверки в соответствии с вашими ожиданиями
         assertEquals(expectedPlanValue, result.get(0).getPlanValue());
         assertEquals(expectedProductionValue, result.get(0).getFactValue());
         assertEquals(35000f, result.get(0).getTotal());

    }
}
