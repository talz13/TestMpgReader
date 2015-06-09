package com.talz13.testmpgreader;

import android.test.AndroidTestCase;

import java.util.LinkedHashMap;

/**
 * Created by Jeff Byrom on 6/8/15.
 */
public class MpgParseTests extends AndroidTestCase {

    public void testMpgParseConstructor() {
        LinkedHashMap<String, Integer> expectedMap = new LinkedHashMap();
        expectedMap.put("GPS Time", -1);
        expectedMap.put("Longitude", -1);
        expectedMap.put("Latitude", -1);
        expectedMap.put("Trip average MPG(mpg)", -1);
        expectedMap.put("Trip Distance(miles)", -1);
        expectedMap.put("Trip Time(Since journey start)(s)", -1);

        MpgParse mpgParser = new MpgParse(getContext().getResources().getStringArray(R.array.csv_test_fields));

        assertEquals(expectedMap, mpgParser.getMap());
    }

    public void testParseHeaderLine() {
        LinkedHashMap<String, Integer> expectedMap = new LinkedHashMap();
        expectedMap.put("GPS Time", 0);
        expectedMap.put("Longitude", 2);
        expectedMap.put("Latitude", 3);
        expectedMap.put("Trip average MPG(mpg)", 13);
        expectedMap.put("Trip Distance(miles)", 14);
        expectedMap.put("Trip Time(Since journey start)(s)", 16);

        MpgParse mpgParser = new MpgParse(getContext().getResources().getStringArray(R.array.csv_test_fields));
        mpgParser.parseHeaderLine("GPS Time, Device Time, Longitude, Latitude,GPS Speed (Meters/second), Horizontal Dilution of Precision, Altitude, Bearing, G(x), G(y), G(z), G(calibrated),Miles Per Gallon(Instant)(mpg),Trip average MPG(mpg),Trip Distance(miles),Fuel used (trip)(gal),Trip Time(Since journey start)(s),Trip time(whilst stationary)(s)");

        assertEquals(expectedMap, mpgParser.getMap());
    }

    public void testParseDataLine() {
        LinkedHashMap<String, String> expectedMap = new LinkedHashMap();
        expectedMap.put("GPS Time", "Fri May 22 07:00:26 EDT 2015");
        expectedMap.put("Longitude", "-81.99970712");
        expectedMap.put("Latitude", "41.42961112");
        expectedMap.put("Trip average MPG(mpg)", "-");
        expectedMap.put("Trip Distance(miles)", "-");
        expectedMap.put("Trip Time(Since journey start)(s)", "0");

        MpgParse mpgParser = new MpgParse(getContext().getResources().getStringArray(R.array.csv_test_fields));
        mpgParser.parseHeaderLine("GPS Time, Device Time, Longitude, Latitude,GPS Speed (Meters/second), Horizontal Dilution of Precision, Altitude, Bearing, G(x), G(y), G(z), G(calibrated),Miles Per Gallon(Instant)(mpg),Trip average MPG(mpg),Trip Distance(miles),Fuel used (trip)(gal),Trip Time(Since journey start)(s),Trip time(whilst stationary)(s)");
        LinkedHashMap<String, String> testMap = (LinkedHashMap)mpgParser.parseDataLine("Fri May 22 07:00:26 EDT 2015,22-May-2015 07:00:25.730,-81.99970712,41.42961112,0.5,38.0,46.0,171.8,-9.25839233,-0.5112915,3.53204346,0.03712408,-,-,-,-,0,0");

        assertEquals(expectedMap, testMap);
    }
}
