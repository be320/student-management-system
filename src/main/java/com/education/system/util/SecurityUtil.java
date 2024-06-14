package com.education.system.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtil {

    public static String secretKey = "D2UOBRjesy48NEVGl+OqOgbdyE4zJJNMLS7sCbxunVgEyri6nc4+wK1RuAA4SLcm3YfbfKfFmn1cK7A/ywtkxQ==";
    public static String secretHmac = "system_hmac";

    public static String hmacSHA256(String secret, String data) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hmacData = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(hmacData);
    }

}
