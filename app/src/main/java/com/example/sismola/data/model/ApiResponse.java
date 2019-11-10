package com.example.sismola.data.model;

import com.google.gson.annotations.SerializedName;
/**
 * @author by M on 10/11/19
 */
public final class ApiResponse {
    @SerializedName("response")
    private final Response response;

    public final Response getResponse() {
        return this.response;
    }

    public ApiResponse(Response response) {
        this.response = response;
    }
}

