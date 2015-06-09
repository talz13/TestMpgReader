package com.talz13.testmpgreader;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jeff Byrom on 6/8/15.
 */
public class MpgRow {
    private Map<String, String> mRowData;

    public MpgRow(Map<String, String> inputData) {
        mRowData = new LinkedHashMap<>();
        mRowData.putAll(inputData);
    }

    public String get(String index) {
        return this.mRowData.get(index);
    }

    public Map<String, String> getAll() {
        return mRowData;
    }
}
