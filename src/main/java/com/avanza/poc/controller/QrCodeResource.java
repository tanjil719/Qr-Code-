package com.avanza.poc.controller;

import com.avanza.poc.model.DecodedQrResponse;
import com.avanza.poc.model.GenerateQrRequest;
import com.avanza.poc.service.QrCodeService;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class QrCodeResource {


    private QrCodeService qrCodeService;

    @PostMapping(value = "/api/qr/generate", produces = MediaType.IMAGE_JPEG_VALUE)
    public void generateQr(@RequestBody @Valid GenerateQrRequest request, HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
        if( request==null || request.getQrString()==null || request.getQrString().trim().equals("") ) {
            throw new MissingRequestValueException("QR String is required");
        }
        if (qrCodeService==null){
            System.out.println("Qr code must not be null");
        }
        qrCodeService.generateQr(request.getQrString(), response.getOutputStream());
        response.getOutputStream().flush();
    }

    @PostMapping(path = "/api/qr/decode")
    public DecodedQrResponse decodeQr(@RequestParam("qrCode") MultipartFile qrCode) throws IOException, NotFoundException {
        String qrCodeString =  qrCodeService.decodeQr(qrCode.getBytes());
        return new DecodedQrResponse(qrCodeString);
    }

}
