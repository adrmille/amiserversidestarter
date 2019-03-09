package fr.amisss.core.exchange.margin;


import fr.amisss.core.exchange.order.OrderType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum PositionType {

    SHORT, LONG;

    public OrderType getOrderType() {
        return (this == SHORT) ? OrderType.ASK : OrderType.BID;
    }

}
