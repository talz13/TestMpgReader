package com.talz13.testmpgreader;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by talz13 on 5/26/15.
 */
public class MpgFile {

    private final Context context;
    private File myFile;
    private FileReader fileReader;
//    private RandomAccessFile

    public MpgFile(Context context) {
        this.context = context;
    }

    public boolean OpenFile(String filename) {
        if (myFile == null)
            myFile = new File(filename);
        if (myFile.exists() && myFile.isFile() && myFile.canRead()) {
            try {
//                fileReader = new FileReader(myFile);
//                fileReader
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean CloseFile() {
        if (myFile != null ) {
            return true;
        }
        return false;
    }

    public String ReadHeaderLine(String filename) {
        String headerLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            headerLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headerLine;
    }

    public String ReadLastDataLine(String filename) {
        return null;
    }

    public String ReadFirstDataLine(String filename) {
        return null;
    }

    public Context getContext() {
        return context;
    }
}
