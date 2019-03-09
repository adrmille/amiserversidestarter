package fr.amisss.core.exchange.market;

import fr.amisss.CoreTestApplication;
import fr.amisss.core.common.system.TestDatabaseHelper;
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
public class MarketOrderDaoTest {

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
        final MarketOrderEntity marketOrderEntity =
                new MarketOrderEntity("123", new BigDecimal("0.00081"), new BigDecimal("0.00081"), new BigDecimal("0.00081"), "BTC_EUR", new Date());

        // WHEN
        marketOrderDao.save(marketOrderEntity);

        // THEN
        Assert.assertTrue(marketOrderDao.existsById(marketOrderEntity.getTechnicalId()));
        entityManager.detach(marketOrderEntity);
        final Optional<MarketOrderEntity> result = marketOrderDao.findById(marketOrderEntity.getTechnicalId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(new BigDecimal("0.00081"), result.get().getOriginalAmount());
    }

}
