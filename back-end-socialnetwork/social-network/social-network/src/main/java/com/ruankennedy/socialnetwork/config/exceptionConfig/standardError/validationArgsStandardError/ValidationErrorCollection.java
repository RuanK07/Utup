package com.ruankennedy.socialnetwork.config.exceptionConfig.standardError.validationArgsStandardError;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"timestamp", "status", "error", "validationErrorList", "path"})
public class ValidationErrorCollection implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "GMT-3")
    @JsonProperty(value = "timestamp")
    private final Instant timestamp;

    @JsonProperty(value = "status")
    private final Integer status;

    @JsonProperty(value = "error")
    private final String error;

    @JsonProperty(value = "path")
    private final String path;

    @JsonProperty(value = "validationErrorList")
    private final List<StandardErrorArgsNotValid> validationErrorList = new ArrayList<>();

    public ValidationErrorCollection(Integer status, String path, String error) {
        this.timestamp = Instant.now();
        this.status = status;
        this.path = path;
        this.error = error;
    }

    public void addStandardErrorArgsNotValid(StandardErrorArgsNotValid standard) {
        validationErrorList.add(standard);
    }

}
