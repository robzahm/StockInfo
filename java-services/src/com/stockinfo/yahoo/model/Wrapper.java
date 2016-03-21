package com.stockinfo.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wrapper {
	public final Query query;

    @JsonCreator
    public Wrapper(@JsonProperty("query") Query query){
        this.query = query;
    }
}