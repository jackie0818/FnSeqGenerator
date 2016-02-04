package com.codeway.service;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by yixli on 2016/2/4.
 */
public class FnSeqServiceTest {

    private static IFnSeqService fnSeqService;
    @BeforeClass
    public static void initialize() {
        fnSeqService = new FnSeqOutputService();
    }

    @Test
    /*unit test*/
    public void testFnSeqOutput() throws Exception {
        String seq1 = fnSeqService.fnSeqOutput(0);
        assertEquals("", seq1);
        String seq2 = fnSeqService.fnSeqOutput(1);
        assertEquals("0", seq2);
        String seq3 = fnSeqService.fnSeqOutput(5);
        assertEquals("0 1 1 2 3", seq3);
        String seq4 = fnSeqService.fnSeqOutput(210);
        String[] fns = seq4.split(" ");
        assertEquals(210, fns.length);
        assertEquals("21327100234463183349497947550385773274930109", fns[209]);
    }

}
