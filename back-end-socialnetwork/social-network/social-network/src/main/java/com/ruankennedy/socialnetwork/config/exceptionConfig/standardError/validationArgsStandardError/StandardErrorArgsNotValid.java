package com.ruankennedy.socialnetwork.config.exceptionConfig.standardError.validationArgsStandardError;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;


@JsonPropertyOrder(value = {"field", "message"})
public class StandardErrorArgsNotValid implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "field")
    private final String field;

    @JsonProperty(value = "message")
    private final String message;

    public StandardErrorArgsNotValid(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
