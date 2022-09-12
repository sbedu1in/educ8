package com.solvd.educ8.demo.utils;

import java.util.regex.Pattern;

import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class CryptoUtil {
    private static CryptoTool cryptoTool = new CryptoTool(Configuration.get(Configuration.Parameter.CRYPTO_KEY_PATH));
    private static Pattern CRYPTO_PATTERN = Pattern.compile(SpecialKeywords.CRYPT);

    public static String decrypt(String string) {
        return cryptoTool.decryptByPattern(string, CRYPTO_PATTERN);
    }

    public static String encrypt(String strToEncrypt) {
        return cryptoTool.encrypt(strToEncrypt);
    }
}