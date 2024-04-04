package org.maklashev.goldengroup.repositories;

import org.maklashev.goldengroup.model.entity.production.ProductionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

/**
 * Репозиторий для работы с сущностью ProductionList в базе данных.
 */
public interface ProductionListRepository extends JpaRepository<ProductionList, Long> {

    /**
     * Выполняет пользовательский запрос для поиска идентификаторов записей в таблице ProductionList,
     * где дата начала производства находится в указанном диапазоне дат и дата завершения производства
     * находится в указанном диапазоне дат.
     *
     * @param startDate Начальная дата диапазона для поиска.
     * @param endDate   Конечная дата диапазона для поиска.
     * @return Список идентификаторов записей, соответствующих найденным записям.
     */
    @Query("SELECT id FROM ProductionList WHERE productionStart >= :startDate AND productionFinish <= :endDate")
    List<Long> findIdsInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * Поиск всех записей ProductionList, где дата производства находится в указанном диапазоне.
     *
     * @param startDate Начальная дата диапазона для поиска.
     * @param endDate   Конечная дата диапазона для поиска.
     * @return Список объектов ProductionList, удовлетворяющих условиям запроса.
     */
    List<ProductionList> findProductionListByProductionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
