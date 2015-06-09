package com.talz13.testmpgreader;

import android.test.AndroidTestCase;

import java.util.LinkedHashMap;

/**
 * Created by Jeff Byrom on 6/9/15.
 */
public class MpgRowTests extends AndroidTestCase {
    public void testConstructor() {
        LinkedHashMap<String, String> expectedMap = new LinkedHashMap();
        expectedMap.put("GPS Time", "Fri May 22 07:00:26 EDT 2015");
        expectedMap.put("Longitude", "-81.99970712");
        expectedMap.put("Latitude", "41.42961112");
        expectedMap.put("Trip average MPG(mpg)", "-");
        expectedMap.put("Trip Distance(miles)", "-");
        expectedMap.put("Trip Time(Since journey start)(s)", "0");

        MpgRow mpgRow = new MpgRow(expectedMap);

        assertEquals(expectedMap, mpgRow.getAll());
    }

    public void testGets() {
        LinkedHashMap<String, String> expectedMap = new LinkedHashMap();
        expectedMap.put("GPS Time", "Fri May 22 07:00:26 EDT 2015");
        expectedMap.put("Longitude", "-81.99970712");
        expectedMap.put("Latitude", "41.42961112");
        expectedMap.put("Trip average MPG(mpg)", "-");
        expectedMap.put("Trip Distance(miles)", "-");
        expectedMap.put("Trip Time(Since journey start)(s)", "0");

        MpgRow mpgRow = new MpgRow(expectedMap);

        assertEquals(expectedMap.get("GPS Time"), mpgRow.get("GPS Time"));
        assertEquals(expectedMap.get("Longitude"), mpgRow.get("Longitude"));
        assertEquals(expectedMap.get("Latitude"), mpgRow.get("Latitude"));
        assertEquals(expectedMap.get("Trip average MPG(mpg)"), mpgRow.get("Trip average MPG(mpg)"));
        assertEquals(expectedMap.get("Trip Distance(miles)"), mpgRow.get("Trip Distance(miles)"));
        assertEquals(expectedMap.get("Trip Time(Since journey start)(s)"), mpgRow.get("Trip Time(Since journey start)(s)"));
    }
}
