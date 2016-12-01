package com.marlonjones.simplereminder;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.input_hint, R.string.input_hint, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        //notification
                        NotificationManager notificationManager = (NotificationManager)
                                getSystemService(NOTIFICATION_SERVICE);
                        //toast
                        Toast.makeText(MainActivity.this, "Reminder Created. Set as Ongoing Notification",
                                Toast.LENGTH_SHORT).show();
                        //close app
                        finish();
                    }
                }).show();
    }
}
