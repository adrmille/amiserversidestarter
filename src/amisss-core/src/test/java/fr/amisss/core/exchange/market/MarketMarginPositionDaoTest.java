package fr.amisss.core.exchange.market;

import fr.amisss.CoreTestApplication;
import fr.amisss.core.common.system.TestDatabaseHelper;
import fr.amisss.core.exchange.ExchangeName;
import fr.amisss.core.exchange.currency.CurrencyPair;
import fr.amisss.core.exchange.margin.PositionType;
import fr.amisss.core.exchange.order.OrderType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CoreTestApplication.class})
@SpringBootTest
@Transactional
@ActiveProfiles("disableRealTrading")
public class MarketMarginPositionDaoTest {

    @Autowired
    private MarketMarginPositionDao marketMarginPositionDao;

    @Autowired
    private MarketOrderDao marketOrderDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TestDatabaseHelper testDatabaseHelper;

    @Before
    public void init() {
        testDatabaseHelper.clearDatabase();
    }

    @Test
    public void should_create_a_margin_position_given_every_param_is_provided() {
        // GIVEN
        final MarketOrderEntity marketOrderEntity = new MarketOrderEntity("123", new BigDecimal("50"), new BigDecimal("50"),
                new BigDecimal("50"), "BTC_EUR", new Date());
        final MarketMarginPosition marketMarginPosition = new MarketMarginPosition(PositionType.LONG, marketOrderEntity,
                ExchangeName.KRAKEN, new BigDecimal("0.00015"), "2");

        // WHEN
        marketOrderDao.save(marketOrderEntity);
        marketMarginPositionDao.save(marketMarginPosition);

        // THEN
        Assert.assertTrue(marketMarginPositionDao.existsById(marketMarginPosition.getId()));
        entityManager.detach(marketMarginPosition);
        final Optional<MarketMarginPosition> result = marketMarginPositionDao.findById(marketMarginPosition.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(new BigDecimal("0.00015"), result.get().getAskedPrice().stripTrailingZeros());
    }

}
