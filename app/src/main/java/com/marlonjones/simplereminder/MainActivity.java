package com.marlonjones.simplereminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(null, null, new MaterialDialog.InputCallback()
                {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        //notification
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(input.toString())); //BigText
                            builder.setOngoing(true); //Make persistent
                            builder.setSmallIcon(R.drawable.ic_note);
                            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                            builder.setContentTitle("Remember!");
                            builder.setContentText(input.toString()); //Get text from dialog input
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            notificationManager.notify(NOTIFICATION_ID, builder.build());
                        //toast
                        Toast.makeText(MainActivity.this, "Reminder Created and set as Ongoing Notification.",
                                Toast.LENGTH_SHORT).show();
                        //Close Activity
                        finish();
                    }
                }).show();
    }
}
