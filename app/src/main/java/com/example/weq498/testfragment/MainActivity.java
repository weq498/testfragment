/*write by bao
2015/07/31 add function fragment
2015/08/05 04:16 add function network
 */
package com.example.weq498.testfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.LogRecord;


public class MainActivity extends ActionBarActivity {
    private Button btn1, send_btn;
    private EditText textsend;
    private TextView textview2;
    private URL url;
    public static final int KEY_READ = 0;
    private String HttpURL = "http://120.114.131.185/Android2DB/dataset.php";
    private String[] key = new String[1];
    private Handler handler1;

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //network
        textsend = (EditText) findViewById(R.id.textsend);
        textview2 = (TextView) findViewById(R.id.textView2);
        send_btn = (Button) findViewById(R.id.send_btn);
        postsend(HttpURL, KEY_READ);
        handler1 = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case KEY_READ:
                        key[0] = msg.obj.toString(); //™Bš¬
                        break;
                    default:
                        textview2.setText("No data translate");
                        break;
                }
                super.handleMessage(msg);
            }
        };
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                postsend p = new postsend(HttpURL);
                String resultData = p.get_data(textsend.getText().toString());
                */
            }
        });


        //fragment
        final FragmentManager fm = getFragmentManager();
        final fragment1 fragment1 = new fragment1();
        Bundle bundle = new Bundle();
        bundle.putStringArray("data", key);
        fragment1.setArguments(bundle);
        fm.beginTransaction().addToBackStack(null).add(R.id.fra_layout, fragment1).commit();
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment2 fragment2 = new fragment2();
                fm.beginTransaction().addToBackStack(null).replace(R.id.fra_layout, fragment2).commit();
            }
        });

    }

    public void postsend(final String inputstrings, final int s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resultData = "";
                try {
                    url = new URL(HttpURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                if (url != null) {
                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoInput(true);
                        urlConnection.setDoOutput(true);
                        DataOutputStream dataout = new DataOutputStream(urlConnection.getOutputStream());
                        dataout.writeBytes("data1=" + textsend.getText().toString());
                        dataout.flush();
                        dataout.close();
                        InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
                        BufferedReader buffer = new BufferedReader(in);
                        String inputLine = null;
                        while ((inputLine = buffer.readLine()) != null) {
                            resultData += inputLine + "\n";
                        }
                        in.close();
                        Message msg = Message.obtain(handler1, KEY_READ, resultData);
                        msg.sendToTarget();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


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

    public void finish() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
