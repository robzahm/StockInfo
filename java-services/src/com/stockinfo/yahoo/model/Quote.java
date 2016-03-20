package com.stockinfo.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {
    public final String symbol;
    public final String date;
    public final String open;
    public final String high;
    public final String low;
    public final String close;
    public final String volume;
    public final String adj_Close;

    @JsonCreator
    public Quote(@JsonProperty("Symbol") String symbol, @JsonProperty("Date") String date, @JsonProperty("Open") String open, 
    		@JsonProperty("High") String high, @JsonProperty("Low") String low, @JsonProperty("Close") String close, 
    		@JsonProperty("Volume") String volume, @JsonProperty("Adj_Close") String adj_Close){
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
