package fr.amisss.core.exchange.market;

import fr.amisss.core.exchange.ExchangeName;
import fr.amisss.core.exchange.margin.PositionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represent a trading operation.<br>
 */
@Entity
public class MarketMarginPosition implements Serializable {

    private static final long serialVersionUID = 7958779017050174032L;

    @Id
    @SequenceGenerator(name = "market_margin_position_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "market_margin_position_sequence")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PositionType positionType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ExchangeName exchangeName;

    @NotNull
    private String currencyPair;

    @ManyToOne
    @NotNull
    private MarketOrderEntity order;

    @Column(columnDefinition = "DECIMAL(19,10)")
    @NotNull
    private BigDecimal askedPrice;

    @NotNull
    private String leverage;

    public MarketMarginPosition() {
    }

    public MarketMarginPosition(PositionType positionType, MarketOrderEntity order, ExchangeName exchangeName,
                                BigDecimal askedPrice, String leverage) {
        this.positionType = positionType;
        this.exchangeName = exchangeName;
        this.currencyPair = order.getCurrencyPair();
        this.order = order;
        this.askedPrice = askedPrice;
        this.leverage = leverage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public ExchangeName getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(ExchangeName exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public MarketOrderEntity getOrder() {
        return order;
    }

    public void setOrder(MarketOrderEntity order) {
        this.order = order;
    }

    public BigDecimal getAskedPrice() {
        return askedPrice;
    }

    public void setAskedPrice(BigDecimal askedPrice) {
        this.askedPrice = askedPrice;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((askedPrice == null) ? 0 : askedPrice.hashCode());
        result = prime * result + ((currencyPair == null) ? 0 : currencyPair.hashCode());
        result = prime * result + ((exchangeName == null) ? 0 : exchangeName.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((leverage == null) ? 0 : leverage.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((positionType == null) ? 0 : positionType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MarketMarginPosition other = (MarketMarginPosition) obj;
        if (askedPrice == null) {
            if (other.askedPrice != null)
                return false;
        } else if (!askedPrice.equals(other.askedPrice))
            return false;
        if (currencyPair == null) {
            if (other.currencyPair != null)
                return false;
        } else if (!currencyPair.equals(other.currencyPair))
            return false;
        if (exchangeName != other.exchangeName)
            return false;
        if (id != other.id)
            return false;
        if (leverage == null) {
            if (other.leverage != null)
                return false;
        } else if (!leverage.equals(other.leverage))
            return false;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (positionType != other.positionType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MarketMarginPosition [id=" + id + ", positionType=" + positionType + ", exchangeName=" + exchangeName
                + ", currencyPair=" + currencyPair + ", order=" + order + ", askedPrice=" + askedPrice
                + ", leverage=" + leverage + "]";
    }

}
