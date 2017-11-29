package com.yarachkin.litetext.interpreter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ClientTest {
    private String expression;
    private Client client;

    @BeforeMethod
    public void setUp() throws Exception {
        expression = "2 3 8 * - -5 + ";
        client = new Client();
    }

    @Test
    public void calculateTest() throws Exception {
        client.parse(expression);
        assertEquals(client.calculate(), -27.0);
    }
}