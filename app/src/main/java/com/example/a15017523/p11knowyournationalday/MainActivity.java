package com.example.a15017523.p11knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String message = "";
    String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lv);
        values = new String[]{"Singapore is 52 years old",
        "9 August is National Day",
        "I love Singapore"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(arrayAdapter);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.access_code, null);
        final EditText etPassphrase = (EditText) passPhrase
                .findViewById(R.id.editTextPassPhrase);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Login")
                .setView(passPhrase)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(etPassphrase.getText().toString() == "738964");
                    }
                })
                .setNegativeButton("No Access Code", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.sendToFriend) {
            for ( int i = 0; i < values.length; i ++) {
                String text =values[i].toString();
                message += text + ", ";
            }
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout sendToFriend =
                    (LinearLayout) inflater.inflate(R.layout.send_to_friend, null);
            final TextView textViewSMS = (TextView) sendToFriend
                    .findViewById(R.id.tvSMS);
            final TextView textViewEMail = (TextView) sendToFriend
                    .findViewById(R.id.tvEmail);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    .setView(sendToFriend);
            textViewEMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, "hndeathchair@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Know Your National Day");
                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    startActivity(Intent.createChooser(intent, "Send Email"));
                }
            });
            textViewSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("sms_body","your desired message");
                    startActivity(smsIntent);

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if(item.getItemId() == R.id.action_quit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Not Really", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
