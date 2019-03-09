
package fr.amisss.core.exchange.market;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class MarketOrderEntity implements Serializable {

    private static final long serialVersionUID = 2132642206689359265L;

    @Id
    @SequenceGenerator(name = "market_order_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "market_order_sequence")
    private Long technicalId;

    private String marketOrderId;

    @Column(columnDefinition = "DECIMAL(19,10)")
    @NotNull
    private BigDecimal originalAmount;

    @Column(columnDefinition = "DECIMAL(19,10)")
    private BigDecimal cumulativeAmount;

    @Column(columnDefinition = "DECIMAL(19,10)")
    private BigDecimal averagePrice;

    @NotNull
    private String currencyPair;

    private Date timestamp;

    public MarketOrderEntity() {
    }

    public MarketOrderEntity(String marketOrderId, @NotNull BigDecimal originalAmount, BigDecimal cumulativeAmount,
                             BigDecimal averagePrice, @NotNull String currencyPair, Date timestamp) {
        this.marketOrderId = marketOrderId;
        this.originalAmount = originalAmount;
        this.cumulativeAmount = cumulativeAmount;
        this.averagePrice = averagePrice;
        this.currencyPair = currencyPair;
        this.timestamp = timestamp;
    }

    public String getMarketOrderId() {
        return marketOrderId;
    }

    public void setMarketOrderId(String marketOrderId) {
        this.marketOrderId = marketOrderId;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount.stripTrailingZeros();
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getCumulativeAmount() {
        return cumulativeAmount.stripTrailingZeros();
    }

    public void setCumulativeAmount(BigDecimal cumulativeAmount) {
        this.cumulativeAmount = cumulativeAmount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice.stripTrailingZeros();
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(Long technicalId) {
        this.technicalId = technicalId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((averagePrice == null) ? 0 : averagePrice.hashCode());
        result = prime * result + ((cumulativeAmount == null) ? 0 : cumulativeAmount.hashCode());
        result = prime * result + ((currencyPair == null) ? 0 : currencyPair.hashCode());
        result = prime * result + ((marketOrderId == null) ? 0 : marketOrderId.hashCode());
        result = prime * result + ((originalAmount == null) ? 0 : originalAmount.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        MarketOrderEntity other = (MarketOrderEntity) obj;
        if (averagePrice == null) {
            if (other.averagePrice != null)
                return false;
        } else if (!averagePrice.equals(other.averagePrice))
            return false;
        if (cumulativeAmount == null) {
            if (other.cumulativeAmount != null)
                return false;
        } else if (!cumulativeAmount.equals(other.cumulativeAmount))
            return false;
        if (currencyPair == null) {
            if (other.currencyPair != null)
                return false;
        } else if (!currencyPair.equals(other.currencyPair))
            return false;
        if (marketOrderId == null) {
            if (other.marketOrderId != null)
                return false;
        } else if (!marketOrderId.equals(other.marketOrderId))
            return false;
        if (originalAmount == null) {
            if (other.originalAmount != null)
                return false;
        } else if (!originalAmount.equals(other.originalAmount))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MarketOrderEntity [technicalId=" + technicalId + ", marketOrderId=" + marketOrderId + ", type="
                + ", status=" + ", originalAmount=" + originalAmount + ", cumulativeAmount=" + cumulativeAmount
                + ", averagePrice=" + averagePrice + ", currencyPair=" + currencyPair + ", timestamp=" + timestamp
                + "]";
    }

}
