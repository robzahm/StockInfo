package com.stockinfo.dataservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {
    public final String symbol;
    public final String date;
    public final double open;
    public final double high;
    public final double low;
    public final double close;
    public final String volume;
    public final double adj_Close;

    @JsonCreator
    public Quote(@JsonProperty("Symbol") String symbol, @JsonProperty("Date") String date, @JsonProperty("Open") double open, 
    		@JsonProperty("High") double high, @JsonProperty("Low") double low, @JsonProperty("Close") double close, 
    		@JsonProperty("Volume") String volume, @JsonProperty("Adj_Close") double adj_Close){
        this.symbol = symbol;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adj_Close = adj_Close;
    }
}
