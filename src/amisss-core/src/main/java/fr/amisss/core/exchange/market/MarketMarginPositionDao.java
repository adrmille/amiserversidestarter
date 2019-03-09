package fr.amisss.core.exchange.market;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MarketMarginPositionDao extends CrudRepository<MarketMarginPosition, Long> {

    @Transactional
    @Modifying
    @Query(value = "CREATE SEQUENCE IF NOT EXISTS market_margin_position_sequence START 1 INCREMENT 50", nativeQuery = true)
    void createSequence();
}
