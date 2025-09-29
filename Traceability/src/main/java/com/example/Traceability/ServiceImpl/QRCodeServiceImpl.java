package com.example.Traceability.ServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class QRCodeServiceImpl {

    private final ObjectMapper objectMapper = new ObjectMapper();

    
    public String generateQRCodeBase64(Object payload) throws Exception {
    	
        String json = objectMapper.writeValueAsString(payload);
        
        BitMatrix matrix = new MultiFormatWriter().encode(json,
        		BarcodeFormat.QR_CODE, 500, 500);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
        
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
