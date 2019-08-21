package com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event.Status;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;

public class HelperTest {

    @Test
    public void testConvertStringJson() throws Exception {
        // Setup
        final String jsonStr = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}";
        final Event expectedResult = new Event();
        expectedResult.setId("scsmbstgra");
        expectedResult.setState(Status.STARTED);
        expectedResult.setHost("12345");
        expectedResult.setType("APPLICATION_LOG");
        expectedResult.setTimestamp(new Timestamp(1491377495212L));

        final Event result = Helper.convertStringJson(jsonStr);
        // Verify the results
//        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetTimestampDifference() {
        // Setup
        final Timestamp a = new Timestamp(1491377495212L);
        final Timestamp b = new Timestamp(1491377495217L);
        final long expectedResult = 5;

        // Run the test
        final long result = Helper.getTimestampDifference(a, b);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
