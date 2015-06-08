package com.talz13.testmpgreader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeff Byrom on 6/8/15.
 */
public class MpgParse {
    private HashMap<String, Integer> mColumnList;

    public MpgParse(String[] columnList) {
        mColumnList = new HashMap<>();
        for (String column : columnList) {
            this.mColumnList.put(column, -1);
        }
    }

    public void ParseHeaderLine(String line) {
        String[] splitLine = line.split(",");

        for (int i = 0; i < splitLine.length; i++) {
            if (mColumnList.containsKey(splitLine[i].trim())) {
                mColumnList.put(splitLine[i].trim(), i);
            }
        }
    }

    public HashMap<String, String> ParseDataLine(String line) {
        String[] splitLine = line.split(",");
        HashMap<String, String> returnMap = new HashMap<>();

        for (Map.Entry<String, Integer> entry : mColumnList.entrySet()) {
            returnMap.put(entry.getKey(), splitLine[entry.getValue()]);
        }
        return returnMap;
    }

    public String[] GetHeaderColumns() {
        if (mColumnList != null) {
            return mColumnList.keySet().toArray(new String[mColumnList.keySet().size()]);
        }
        return null;
    }

    public HashMap<String, Integer> GetHashMap() {
        return mColumnList;
    }
}
