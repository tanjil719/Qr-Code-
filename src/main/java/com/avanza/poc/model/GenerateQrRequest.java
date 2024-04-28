package com.avanza.poc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class GenerateQrRequest implements Serializable {


    private static final long serialVersionUID = 1230456L;

    private String qrString;

    public String getQrString() {
        return qrString;
    }

    public void setQrString(String qrString) {
        this.qrString = qrString;
    }
}
