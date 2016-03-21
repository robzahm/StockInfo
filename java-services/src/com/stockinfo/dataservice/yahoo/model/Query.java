package com.stockinfo.dataservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Query {
    public final long count;
    public final String created;
    public final String lang;
    public final Results results;

    @JsonCreator
    public Query(@JsonProperty("count") long count, @JsonProperty("created") String created, @JsonProperty("lang") String lang, @JsonProperty("results") Results results){
        this.count = count;
        this.created = created;
        this.lang = lang;
        this.results = results;
    }
}
