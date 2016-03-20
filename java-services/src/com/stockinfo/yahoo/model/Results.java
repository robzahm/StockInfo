package com.stockinfo.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Results {
    public final Quote quote[];
    
    @JsonCreator
    public Results(@JsonProperty("quote") Quote[] quote){
        this.quote = quote;
    }
}
