package fr.amisss.core.common.system;

import fr.amisss.core.exchange.market.MarketMarginPositionDao;
import fr.amisss.core.exchange.market.MarketOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDatabaseHelper {

    @Autowired
    private MarketMarginPositionDao marketMarginPositionDao;

    @Autowired
    private MarketOrderDao marketOrderDao;

    public void clearDatabase() {
        marketOrderDao.deleteAll();
        marketMarginPositionDao.deleteAll();
    }

}
