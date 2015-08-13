package sample.lobar.jp.intentsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static final String SAMPLE_ACTION_NAME = "ring.sample.ACTION";
    public static final String SAMPLE_EXTRA_KEY = "ring.sample.EXTRA_KEY";

    ListView mListView;
    ArrayAdapter <String> mArrayAdapter;
    int receiveCount = 0;

    BroadcastReceiver mReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equalsIgnoreCase(SAMPLE_ACTION_NAME)) {

                String msg = "Gesture Received! " + receiveCount++;
                if (intent.hasExtra(SAMPLE_EXTRA_KEY)){
                    msg += " :" + intent.getStringExtra(SAMPLE_EXTRA_KEY);
                }

                mArrayAdapter.add(msg);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item);
        mListView.setAdapter(mArrayAdapter);

        registerReceiver(mReceiver, new IntentFilter("ring.sample.ACTION"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}