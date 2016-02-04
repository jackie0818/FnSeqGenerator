package com.codeway.service;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static junit.framework.Assert.assertEquals;

/**
 * Created by yixli on 2016/2/4.
 */
public class FnSeqServiceIT {
    static String endpoint = "http://localhost:8080/fn/out";
    @Test
    /*functional test*/
    public void testIntegerPara() throws Exception {
        String integerCaseUrl = endpoint + "/10";
        String response = getResponseFromUrl(integerCaseUrl);
        System.out.println(response);
        String expected = "{\"Fibonacci\":\"0 1 1 2 3 5 8 13 21 34\"}";
        assertEquals(expected, response);
    }

    @Test
    /*functional test*/
    public void testNegativeIntegerPara() throws Exception {
        String integerCaseUrl = endpoint + "/-10";
        String response = getResponseFromUrl(integerCaseUrl);
        String expected = "{\"Error\":\"Please use an integer more than 0 as parameter!\"}";
        assertEquals(expected, response);
    }

    private String getResponseFromUrl(String url) {
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            URL urldemo = new URL(url);
            URLConnection yc = urldemo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            while((line = in.readLine()) != null) {
               sb.append(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
