package com.talz13.testmpgreader;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jeff Byrom on 6/8/15.
 */
public class MpgParse {
    private Map<String, Integer> mColumnList;

    public MpgParse(String[] columnList) {
        mColumnList = new LinkedHashMap<>();
        for (String column : columnList) {
            this.mColumnList.put(column, -1);
        }
    }

    public void parseHeaderLine(String csvHeader) {
        String[] splitLine = csvHeader.split(",");

        for (int i = 0; i < splitLine.length; i++) {
            if (mColumnList.containsKey(splitLine[i].trim())) {
                mColumnList.put(splitLine[i].trim(), i);
            }
        }
    }

    public Map<String, String> parseDataLine(String csvDataLine) {
        String[] splitLine = csvDataLine.split(",");
        LinkedHashMap<String, String> returnMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : mColumnList.entrySet()) {
            if (entry.getValue() >= 0) {
                returnMap.put(entry.getKey(), splitLine[entry.getValue()]);
            }
        }
        return returnMap;
    }

    public Map<String, Integer> getMap() {
        return mColumnList;
    }
}
