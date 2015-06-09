package com.talz13.testmpgreader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.Comparator;
import java.util.Map;


public class MainActivity extends Activity {

    private Button mUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUpdateButton = (Button)findViewById(R.id.update_button);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                for (File file : new File(Environment.getExternalStorageDirectory(), getString(R.string.mpg_folder)).listFiles()) {
                    MpgFile mpgFile = new MpgFile(file);
                    MpgParse mpgParse = new MpgParse(getResources().getStringArray(R.array.csv_fields));

                    mpgParse.parseHeaderLine(mpgFile.ReadHeaderLine());
                    updateSummaryText(mpgParse.parseDataLine(mpgFile.ReadLastDataLine()));
                }
            }
        });
    }

//    public void updateHeaderLineText(String text) {
//        ((TextView)findViewById(R.id.headerLineValue)).setText(text);
//    }
//
//    public void updateFirstDataLineText(String text) {
//        ((TextView)findViewById(R.id.firstDataLineValue)).setText(text);
//    }

    public void updateSummaryText(Map<String, String> inputMap) {
        TextView summaryTextView = (TextView)findViewById(R.id.lastDataLineValue);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : inputMap.entrySet()) {
            if (sb.length() != 0) {
                sb.append(System.lineSeparator());
            }
            sb.append(entry.getKey()).append(": ").append(entry.getValue());
        }
//        if (summaryTextView.length() )
        ((TextView)findViewById(R.id.lastDataLineValue)).append(sb.toString());
    }

//    private Comparator<File> compareFileLastModified {
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
