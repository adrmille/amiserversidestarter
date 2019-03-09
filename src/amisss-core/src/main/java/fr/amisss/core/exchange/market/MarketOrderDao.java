package fr.amisss.core.exchange.market;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MarketOrderDao extends CrudRepository<MarketOrderEntity, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "CREATE SEQUENCE IF NOT EXISTS market_order_sequence START 1 INCREMENT 50", nativeQuery = true)
    void createSequence();

}
