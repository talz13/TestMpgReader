package com.talz13.testmpgreader;

//import android.app.Application;
//import android.test.ApplicationTestCase;

import android.os.Environment;
import android.test.AndroidTestCase;

import java.io.File;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

public class MpgReaderTests extends AndroidTestCase {
    public void testTestFile() {
        MpgFile mpgFile = new MpgFile(getContext());
        File file = new File(Environment.getExternalStorageDirectory(), mpgFile.getContext().getString(R.string.TestFile));

        boolean openedFile = mpgFile.TestFile(file.getPath());

        assertEquals(true, openedFile);
    }

    public void testReadingHeaderLine() {
        MpgFile mpgFile = new MpgFile(getContext());
        File file = new File(Environment.getExternalStorageDirectory(), mpgFile.getContext().getString(R.string.TestFile));

        String headerLine = mpgFile.ReadHeaderLine(file.getPath());

        assertEquals("GPS Time, Device Time, Longitude, Latitude,GPS Speed (Meters/second), Horizontal Dilution of Precision, Altitude, Bearing, G(x), G(y), G(z), G(calibrated),Miles Per Gallon(Instant)(mpg),Trip average MPG(mpg),Trip Distance(miles),Fuel used (trip)(gal),Trip Time(Since journey start)(s),Trip time(whilst stationary)(s)", headerLine);
    }

    public void testReadFirstDataLine() {
        MpgFile mpgFile = new MpgFile(getContext());
        File file = new File(Environment.getExternalStorageDirectory(), mpgFile.getContext().getString(R.string.TestFile));

        String firstLine = mpgFile.ReadFirstDataLine(file.getPath());

        assertEquals("Fri May 22 07:00:26 EDT 2015,22-May-2015 07:00:25.730,-81.99970712,41.42961112,0.5,38.0,46.0,171.8,-9.25839233,-0.5112915,3.53204346,0.03712408,-,-,-,-,0,0", firstLine);
    }

    public void testFindLastLineStart() {
        MpgFile mpgFile = new MpgFile(getContext());
        File file = new File(Environment.getExternalStorageDirectory(), mpgFile.getContext().getString(R.string.TestFile));

        long lastLineStart = mpgFile.FindLastLineStart(file.getPath());

        assertEquals(300261, lastLineStart);
    }

    public void testReadLastDataLine() {
        MpgFile mpgFile = new MpgFile(getContext());
        File file = new File(Environment.getExternalStorageDirectory(), mpgFile.getContext().getString(R.string.TestFile));

        String lastLine = mpgFile.ReadLastDataLine(file.getPath());

        assertEquals("Fri May 22 07:24:48 EDT 2015,22-May-2015 07:24:47.180,-82.15115221,41.4215944,0.0,6.0,178.0,0.0,-0.16299438,4.13757324,9.83526611,-0.00290112,255,33.63421249,9.9491272,0.29580379,1456,134.2539978", lastLine);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();


    }
}