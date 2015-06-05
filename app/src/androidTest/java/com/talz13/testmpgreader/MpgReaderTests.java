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
        File file = new File(Environment.getExternalStorageDirectory(), getContext().getString(R.string.test_file));
        MpgFile mpgFile = new MpgFile(file);

        boolean openedFile = mpgFile.TestFile();

        assertEquals(true, openedFile);
    }

    public void testReadingHeaderLine() {
        File file = new File(Environment.getExternalStorageDirectory(), getContext().getString(R.string.test_file));
        MpgFile mpgFile = new MpgFile(file);

        String headerLine = mpgFile.ReadHeaderLine();

        assertEquals("GPS Time, Device Time, Longitude, Latitude,GPS Speed (Meters/second), Horizontal Dilution of Precision, Altitude, Bearing, G(x), G(y), G(z), G(calibrated),Miles Per Gallon(Instant)(mpg),Trip average MPG(mpg),Trip Distance(miles),Fuel used (trip)(gal),Trip Time(Since journey start)(s),Trip time(whilst stationary)(s)", headerLine);
    }

    public void testReadFirstDataLine() {
        File file = new File(Environment.getExternalStorageDirectory(), getContext().getString(R.string.test_file));
        MpgFile mpgFile = new MpgFile(file);

        String firstLine = mpgFile.ReadFirstDataLine();

        assertEquals("Fri May 22 07:00:26 EDT 2015,22-May-2015 07:00:25.730,-81.99970712,41.42961112,0.5,38.0,46.0,171.8,-9.25839233,-0.5112915,3.53204346,0.03712408,-,-,-,-,0,0", firstLine);
    }

    public void testFindLastLineStart() {
        File file = new File(Environment.getExternalStorageDirectory(), getContext().getString(R.string.test_file));
        MpgFile mpgFile = new MpgFile(file);

        long lastLineStart = mpgFile.FindLastLineStart();

        assertEquals(300261, lastLineStart);
    }

    public void testReadLastDataLine() {
        File file = new File(Environment.getExternalStorageDirectory(), getContext().getString(R.string.test_file));
        MpgFile mpgFile = new MpgFile(file);

        String lastLine = mpgFile.ReadLastDataLine();

        assertEquals("Fri May 22 07:24:48 EDT 2015,22-May-2015 07:24:47.180,-82.15115221,41.4215944,0.0,6.0,178.0,0.0,-0.16299438,4.13757324,9.83526611,-0.00290112,255,33.63421249,9.9491272,0.29580379,1456,134.2539978", lastLine);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();


    }
}