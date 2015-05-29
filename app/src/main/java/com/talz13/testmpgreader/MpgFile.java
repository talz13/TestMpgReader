package com.talz13.testmpgreader;

import android.content.Context;

/**
 * Created by b010396 on 5/26/15.
 */
public class MpgFile {

    private final Context context;

    public MpgFile(Context context) {
        this.context = context;
    }

    public boolean OpenFile() {
        return false;
    }

    public String ReadHeaderLine() {
        return null;
    }

    public String ReadLastLine() {
        return null;
    }
}
