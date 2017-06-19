package com.example.bagonoy.codeschool_c;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editTextPhone;
    EditText editTextMessage;
    Button button;

    final int MAX_NUMBER__SMS_SENT = 2;

    private String phonNum, msg;  // holds data
    SmsManager smsMgrTwo = null;  // used to pass data
    int count = 0; // keeps track of message sent button and number putton pused.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start coding here.. down below. .
       // Toast.makeText(MainActivity.this, "Begin of Program", Toast.LENGTH_LONG).show();
//=========================================================================================
        //starT CODE HERE. .





//============================ DONT DELETE ============================

        ImageButton btnSOS = (ImageButton) findViewById(R.id.btnSOS);    // button
        Button btnSnd = (Button) findViewById(R.id.sndBtn);              // button

        // This is what will happen after pressing save Botton.  Will save data
        btnSnd.setOnClickListener(new OnClickListener() {  // btnSnd

            @Override
            public void onClick(View view) {
                EditText addrTxt =
                        (EditText) MainActivity.this.findViewById(R.id.etText);  // display
                EditText msgTxt =
                        (EditText) MainActivity.this.findViewById(R.id.etMsgText);  // display
                try {
                    sndMsg(
                            addrTxt.getText().toString(), msgTxt.getText().toString());
                    //	Toast.makeText(MainActivity.this, "SMS being drop", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "ERROR!!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }//end Onclick
        });  //nothing in between


        // Part 1 of 2:  check permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("This App", "Permission is not granted, requesting");

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            button.setEnabled(false);
        } else {
            Log.d("This App", "Permission is granted");
            postCenterToast("Permission to send SMS");
        }


        // This code is for pressing and holding down the botton.
        btnSOS.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                // count how many text has been sent and after 2 sent. After 2 message sent, no
                // longer need button.
                 if (count < MAX_NUMBER__SMS_SENT) {


                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // increaseSize();
                        postCenterToast("***** HOLDING BUTTON ****** .");

                    } else {
                        postCenterToast("S.O.S SENT!!!");
                        try {

                            //  NOTE:  THIS IS TO SEND SMS

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
		                            smsMgrTwo = SmsManager.getDefault();
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {

                                smsMgrTwo.sendTextMessage(phonNum, null, msg, null, null);
                                count++;  // count how many sms sent. . MAX 3
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }// end 1st if statement ---> counting message sent
                else {
                    postCenterToast("You have already sent TWO MESSAGES.");
                }
                return false;
            }// end ontouch
        });
    } // End OnCreate. .55545


// ==========================  Below are functions ==========================================


//// 2 of 2: check Permission
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == 123) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.d("This APP", "Permission has been granted");
//                postCenterToast("PERMISSION GRANTED");
//                button.setEnabled(true);
//            } else {
//                postCenterToast("YOU CAN'T SEND SMS");
//                button.setEnabled(false);
//            }
//        }
//    }



    // Send Message via sms Old Techonolgy
    private void sndMsg(String address, String message) throws Exception {
        phonNum = address;
        msg = message;

        postCenterToast("Message Saved!!");
//		SmsManager smsMgr = null;
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
//			smsMgr = SmsManager.getDefault();
//		}
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
        // smsMgr.sendTextMessage(address, null, message, null, null);
        // }
        // update message;
    }

    // custom TOAST message display CENTER left portion of screen
    void postCenterToast(String sayWhat) {

        Context context = getApplicationContext();
        CharSequence text = sayWhat;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Displays in Center Left
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
    }


}// END OF Mainactivity
