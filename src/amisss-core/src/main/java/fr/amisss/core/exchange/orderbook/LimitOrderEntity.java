/**
 *
 */
package fr.amisss.core.exchange.orderbook;

import fr.amisss.core.exchange.order.OrderType;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author KrowZ
 */
@Entity
@Table(name = "limitorder")
public class LimitOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Order type i.e. bid or ask
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OrderType type;

    /**
     * Amount to be ordered / amount that was ordered
     */
    @Column(name = "originalAmount")
    private BigDecimal originalAmount;

    /**
     * The currency pair
     */
    private String currencyPair;

    /**
     * The limit price
     */
    @Column(name = "limitPrice")
    private BigDecimal limitPrice;

    public LimitOrderEntity() {
    }

    public LimitOrderEntity(OrderType type, BigDecimal originalAmount, String currencyPair, BigDecimal limitPrice) {
        this.type = type;
        this.originalAmount = originalAmount;
        this.currencyPair = currencyPair;
        this.limitPrice = limitPrice;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public OrderType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(OrderType type) {
        this.type = type;
    }

    /**
     * @return the originalAmount
     */
    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    /**
     * @param originalAmount the originalAmount to set
     */
    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    /**
     * @return the currencyPair
     */
    public String getCurrencyPair() {
        return currencyPair;
    }

    /**
     * @param currencyPair the currencyPair to set
     */
    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    /**
     * @return the limitPrice
     */
    public BigDecimal getLimitPrice() {
        return limitPrice;
    }

    /**
     * @param limitPrice the limitPrice to set
     */
    public void setLimitPrice(BigDecimal limitPrice) {
        this.limitPrice = limitPrice;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currencyPair == null) ? 0 : currencyPair.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((limitPrice == null) ? 0 : limitPrice.hashCode());
        result = prime * result + ((originalAmount == null) ? 0 : originalAmount.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LimitOrderEntity other = (LimitOrderEntity) obj;
        if (currencyPair == null) {
            if (other.currencyPair != null)
                return false;
        } else if (!currencyPair.equals(other.currencyPair))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (limitPrice == null) {
            if (other.limitPrice != null)
                return false;
        } else if (!limitPrice.equals(other.limitPrice))
            return false;
        if (originalAmount == null) {
            if (other.originalAmount != null)
                return false;
        } else if (!originalAmount.equals(other.originalAmount))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LimitOrderEntity [" + (id != null ? "id=" + id + ", " : "")
                + (type != null ? "type=" + type + ", " : "")
                + (originalAmount != null ? "originalAmount=" + originalAmount + ", " : "")
                + (currencyPair != null ? "currencyPair=" + currencyPair + ", " : "")
                + (limitPrice != null ? "limitPrice=" + limitPrice : "") + "]";
    }


}