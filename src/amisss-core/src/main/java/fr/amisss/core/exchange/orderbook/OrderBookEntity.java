package fr.amisss.core.exchange.orderbook;

import fr.amisss.core.exchange.ExchangeName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author KrowZ
 */
@Entity
@Table(name = "orderbook")
public class OrderBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * localTimestamp != orderBook.timestamp
     * localTimestamp is the date of instantiation in localtime (machine time)
     * Used for cache refresh and persistence
     */
    @Column(name = "localTimestamp")
    private Date localTimestamp;

    /**
     * the timestamp of the orderbook according to the exchange's server, null if not provided
     */
    @Column(name = "exchangeTimeStamp")
    private Date exchangeTimeStamp;

    /**
     * the asks
     */
    @OneToMany(cascade = {CascadeType.ALL})
    private List<LimitOrderEntity> asks;

    /**
     * the bids
     */
    @OneToMany(cascade = {CascadeType.ALL})
    private List<LimitOrderEntity> bids;

    @Enumerated(EnumType.STRING)
    @Column(name = "exchangeName")
    private ExchangeName exchangeName;

    public OrderBookEntity() {
    }

    public OrderBookEntity(ExchangeName exchange) {
        this.localTimestamp = new Date();
        this.exchangeTimeStamp = new Date();
        this.asks = new ArrayList<LimitOrderEntity>();
        this.bids = new ArrayList<LimitOrderEntity>();
        this.exchangeName = exchange;
    }

    public List<LimitOrderEntity> getAsks() {

        return this.asks;
    }

    public List<LimitOrderEntity> getBids() {

        return this.bids;
    }

    /**
     * @param asks the asks to set
     */
    public void setAsks(List<LimitOrderEntity> asks) {
        this.asks = asks;
    }

    /**
     * @param bids the bids to set
     */
    public void setBids(List<LimitOrderEntity> bids) {
        this.bids = bids;
    }

    /**
     * @return Date of instantiation of this OrderBook
     */
    public Date getLocalTimestamp() {
        return localTimestamp;
    }

    public Date getExchangeTimeStamp() {
        return exchangeTimeStamp;
    }

    public void setExchangeTimeStamp(Date timeStamp) {
        this.exchangeTimeStamp = timeStamp;
    }

    /**
     * @return the exchangeName
     */
    public ExchangeName getExchangeName() {
        return exchangeName;
    }

    /**
     * @param exchangeName the exchangeName to set
     */
    public void setExchangeName(ExchangeName exchangeName) {
        this.exchangeName = exchangeName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((asks == null) ? 0 : asks.hashCode());
        result = prime * result + ((bids == null) ? 0 : bids.hashCode());
        result = prime * result + ((exchangeName == null) ? 0 : exchangeName.hashCode());
        result = prime * result + ((exchangeTimeStamp == null) ? 0 : exchangeTimeStamp.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((localTimestamp == null) ? 0 : localTimestamp.hashCode());
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
        OrderBookEntity other = (OrderBookEntity) obj;
        if (asks == null) {
            if (other.asks != null)
                return false;
        } else if (!asks.equals(other.asks))
            return false;
        if (bids == null) {
            if (other.bids != null)
                return false;
        } else if (!bids.equals(other.bids))
            return false;
        if (exchangeName != other.exchangeName)
            return false;
        if (exchangeTimeStamp == null) {
            if (other.exchangeTimeStamp != null)
                return false;
        } else if (!exchangeTimeStamp.equals(other.exchangeTimeStamp))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (localTimestamp == null) {
            if (other.localTimestamp != null)
                return false;
        } else if (!localTimestamp.equals(other.localTimestamp))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OrderBookEntity [" + (id != null ? "id=" + id + ", " : "")
                + (localTimestamp != null ? "localTimestamp=" + localTimestamp + ", " : "")
                + (exchangeTimeStamp != null ? "exchangeTimeStamp=" + exchangeTimeStamp + ", " : "")
                + (asks != null ? "asks=" + asks + ", " : "") + (bids != null ? "bids=" + bids + ", " : "")
                + (exchangeName != null ? "exchangeName=" + exchangeName : "") + "]";
    }


}
