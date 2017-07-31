package com.marlonjones.simplereminder;
/**
 * Java Class made by Marlon Jones (MJonesDev) on 12/1/2016.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.InputType;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


public class MainActivity extends AppCompatActivity {
//TODO - Change some code to Kotlin
    public static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .autoDismiss(false)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .negativeText(R.string.remind_time)
                .positiveText(R.string.set_note)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .input(null, null, new MaterialDialog.InputCallback()

                {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                            Intent confirmIntent = new Intent(getApplicationContext(), ConfirmActivity.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                                    0, confirmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(input.toString())); //BigText
                            builder.setContentIntent(pendingIntent);
                            builder.setSmallIcon(R.drawable.ic_small);
                            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_large));
                            builder.setContentTitle("Remember!");
                            builder.setOngoing(true);
                            builder.setContentText(input.toString()); //Get text from dialog input
                            notificationManager.notify(NOTIFICATION_ID, builder.build());
                        Toast.makeText(MainActivity.this, R.string.confirm, Toast.LENGTH_LONG).show();
                        finish();
                    }

                    //Close app when dialog is dismissed (ex: click outside of dialog)
                }).dismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
               finish();

            }
        }).show();
    }
    public void onBackPressed(){
        finish();
    }

}
