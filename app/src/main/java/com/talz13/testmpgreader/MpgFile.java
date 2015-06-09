package com.talz13.testmpgreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Jeff Byrom on 5/26/15.
 */
public class MpgFile {
    private File mFile;

    public MpgFile(File file) {
        mFile = file;
    }

    public boolean TestFile() {
        return mFile.exists() && mFile.isFile() && mFile.canRead();
    }

    public String ReadHeaderLine() {
        String line = null;
        if (TestFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(mFile))) {
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

    public String ReadLastDataLine() {
        String line = null;
        long lastLineStart = FindLastLineStart();
        if (TestFile()) {
            try (RandomAccessFile r = new RandomAccessFile(mFile, "r")) {
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

    public String ReadFirstDataLine() {
        String line = null;
        if (TestFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(mFile))) {
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

    public long FindLastLineStart() {
        long start = 0;
        if (TestFile()) {
            try (RandomAccessFile r = new RandomAccessFile(mFile, "r")) {
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
