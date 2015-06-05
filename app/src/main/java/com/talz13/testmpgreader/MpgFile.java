package com.talz13.testmpgreader;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by talz13 on 5/26/15.
 */
public class MpgFile {

    private final Context context;
//    private File myFile;

    public MpgFile(Context context) {
        this.context = context;
    }

    public boolean TestFile(String filename) {
        File myFile = new File(filename);
        if (myFile.exists() && myFile.isFile() && myFile.canRead()) {
            System.out.println("File found!");
            return true;
        }
        System.out.println("File not found!");
        return false;
    }

    public String ReadHeaderLine(String filename) {
        String line = null;
        if (TestFile(filename)) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                line = br.readLine();
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    public String ReadLastDataLine(String filename) {
        String line = null;
        long lastLineStart = FindLastLineStart(filename);
        if (TestFile(filename)) {
            try (RandomAccessFile r = new RandomAccessFile(filename, "r")) {
                r.seek(lastLineStart);
                line = r.readLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    public String ReadFirstDataLine(String filename) {
        String line = null;
        if (TestFile(filename)) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                br.readLine();  // readLine() to skip over first line
                line = br.readLine();
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    public Context getContext() {
        return context;
    }

    public long FindLastLineStart(String filename) {
        long start = 0;
        if (TestFile(filename)) {
            try (RandomAccessFile r = new RandomAccessFile(filename, "r")) {
                r.seek(r.length() - 2); // Skipping last 2 bytes to ignore trailing line separator
                int check;
                long fp = r.getFilePointer();
                while (fp > 0) {
                    check = r.read();
                    if (check == 0x0a) {
                        start = r.getFilePointer();
                        break;
                    } else {
                        r.seek(--fp);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return start;
    }
}
