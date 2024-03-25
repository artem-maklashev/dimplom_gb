/**
 * Этот класс представляет контроллер для управления операциями, связанными с гипсокартоном.
 * Он обрабатывает HTTP-запросы, связанные с производством гипсокартонных плит, задержками, дефектами и планами.
 * Все конечные точки этого контроллера доступны только пользователям с ролью 'USER' или 'ADMIN'.
 */

package org.maklashev.goldengroup.model.controllers;

import java.util.List;

import org.maklashev.goldengroup.model.entity.delays.Delays;
import org.maklashev.goldengroup.model.entity.gypsumboard.BoardDefectsLog;
import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.model.service.DefectsService;
import org.maklashev.goldengroup.model.service.DelaysService;
import org.maklashev.goldengroup.model.service.GypsumBoardService;
import org.maklashev.goldengroup.model.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class GypsumBoardController {

    private final GypsumBoardService gypsumBoardService;
    private final DelaysService delaysService;
    private final DefectsService defectsService;
    private final PlanService planService;

    /**
     * Конструктор для GypsumBoardController.
     *
     * @param gypsumBoardService Сервис для управления операциями, связанными с гипсокартоном.
     * @param delaysService      Сервис для управления задержками.
     * @param defectsService     Сервис для управления дефектами.
     * @param planService        Сервис для управления производственными планами.
     */
    @Autowired
    public GypsumBoardController(GypsumBoardService gypsumBoardService, DelaysService delaysService,
                                 DefectsService defectsService,  PlanService planService) {
        this.gypsumBoardService = gypsumBoardService;
        this.delaysService = delaysService;
        this.defectsService = defectsService;
        this.planService = planService;
    }

    /**
     * Получает все данные о производстве гипсокартонных плит в указанном диапазоне дат.
     *
     * @param startDate Начальная дата для получения данных.
     * @param endDate   Конечная дата для получения данных.
     * @return          Список объектов GypsumBoardProductionData.
     */
    @GetMapping("/allboard")
    public List<GypsumBoardProductionData> allBoard(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate){

        return gypsumBoardService.getAllGypsumBoardsByDate(startDate, endDate);
    }

    /**
     * Получает данные о производстве гипсокартонных плит в указанном диапазоне дат.
     *
     * @param startDate Начальная дата для получения данных.
     * @param endDate   Конечная дата для получения данных.
     * @return          Список объектов BoardProduction.
     */
    @GetMapping("/allboard/production")
    public List<BoardProduction> getProductionData(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate
    ) {
        System.out.println("Получена начальная дата: " + startDate);
        return gypsumBoardService.getBoardProductionByDate(startDate, endDate);
    }

    /**
     * Получает данные о задержках в указанном диапазоне дат.
     *
     * @param startDate Начальная дата для получения данных.
     * @param endDate   Конечная дата для получения данных.
     * @param division  Подразделение, для которого запрашиваются задержки.
     * @return          Список объектов Delays.
     */
    @GetMapping("/allboard/delays")
    public List<Delays> getDelays(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate,
            @RequestParam(name = "division", defaultValue = "1") String division
    ) {
        System.out.println("!!!!!");
        System.out.println(startDate);
        System.out.println(endDate);

        return delaysService.getDelaysByDate(startDate, endDate);
    }

    /**
     * Получает данные о дефектах в указанном диапазоне дат.
     *
     * @param startDate Начальная дата для получения данных.
     * @param endDate   Конечная дата для получения данных.
     * @return          Список объектов BoardDefectsLog.
     */
    @GetMapping("/allboard/defects")
    public List<BoardDefectsLog> getDefects(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate
    ) {
        return defectsService.getDefectsByDate(startDate, endDate);
    }

    /**
     * Получает данные о производственном плане.
     *
     * @return Список объектов Plan.
     */
    @GetMapping("/planData")
    public List<Plan> getPlan() {
        System.out.println("Запрос плана получен");
        return planService.getPlanData();
    }
}
