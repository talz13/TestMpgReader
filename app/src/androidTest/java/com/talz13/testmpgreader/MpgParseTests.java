package com.talz13.testmpgreader;

import android.test.AndroidTestCase;

import java.util.HashMap;

/**
 * Created by Jeff Byrom on 6/8/15.
 */
public class MpgParseTests extends AndroidTestCase {

    public void testMpgParseConstructor() {
        HashMap<String, Integer> expectedHashMap = new HashMap();
        expectedHashMap.put("GPS Time", -1);
        expectedHashMap.put("Longitude", -1);
        expectedHashMap.put("Latitude", -1);
        expectedHashMap.put("Trip average MPG(mpg)", -1);
        expectedHashMap.put("Trip Distance(miles)", -1);
        expectedHashMap.put("Trip Time(Since journey start)(s)", -1);

        MpgParse mpgParser = new MpgParse(getContext().getResources().getStringArray(R.array.csv_fields));

        assertEquals(expectedHashMap, mpgParser.GetHashMap());
    }

    public void testParseHeaderLine() {
        HashMap<String, Integer> expectedHashMap = new HashMap();
        expectedHashMap.put("GPS Time", 0);
        expectedHashMap.put("Longitude", 2);
        expectedHashMap.put("Latitude", 3);
        expectedHashMap.put("Trip average MPG(mpg)", 13);
        expectedHashMap.put("Trip Distance(miles)", 14);
        expectedHashMap.put("Trip Time(Since journey start)(s)", 16);

        MpgParse mpgParser = new MpgParse(getContext().getResources().getStringArray(R.array.csv_fields));
        mpgParser.ParseHeaderLine("GPS Time, Device Time, Longitude, Latitude,GPS Speed (Meters/second), Horizontal Dilution of Precision, Altitude, Bearing, G(x), G(y), G(z), G(calibrated),Miles Per Gallon(Instant)(mpg),Trip average MPG(mpg),Trip Distance(miles),Fuel used (trip)(gal),Trip Time(Since journey start)(s),Trip time(whilst stationary)(s)");

        assertEquals(expectedHashMap, mpgParser.GetHashMap());
    }

    public void testParseDataLine() {
        HashMap<String, String> expectedHashMap = new HashMap();
        expectedHashMap.put("GPS Time", "Fri May 22 07:00:26 EDT 2015");
        expectedHashMap.put("Longitude", "-81.99970712");
        expectedHashMap.put("Latitude", "41.42961112");
        expectedHashMap.put("Trip average MPG(mpg)", "-");
        expectedHashMap.put("Trip Distance(miles)", "-");
        expectedHashMap.put("Trip Time(Since journey start)(s)", "0");

        MpgParse mpgParser = new MpgParse(getContext().getResources().getStringArray(R.array.csv_fields));
        mpgParser.ParseHeaderLine("GPS Time, Device Time, Longitude, Latitude,GPS Speed (Meters/second), Horizontal Dilution of Precision, Altitude, Bearing, G(x), G(y), G(z), G(calibrated),Miles Per Gallon(Instant)(mpg),Trip average MPG(mpg),Trip Distance(miles),Fuel used (trip)(gal),Trip Time(Since journey start)(s),Trip time(whilst stationary)(s)");
        HashMap<String, String> testHashMap = mpgParser.ParseDataLine("Fri May 22 07:00:26 EDT 2015,22-May-2015 07:00:25.730,-81.99970712,41.42961112,0.5,38.0,46.0,171.8,-9.25839233,-0.5112915,3.53204346,0.03712408,-,-,-,-,0,0");

        assertEquals(expectedHashMap, testHashMap);
    }
}
