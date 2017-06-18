package com.example.bagonoy.codeschool_c;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.R.id.message;


public class MainActivity extends AppCompatActivity {

    private String phonNum, msg;  // holds data
    SmsManager smsMgrTwo = null;  // used to pass data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ImageButton btnSOS = (ImageButton)findViewById(R.id.btnSOS);
        Button btnSnd = (Button)findViewById(R.id.sndBtn);

//--------------------------
        btnSnd.setOnClickListener(new OnClickListener(){  // btnSnd

            @Override
            public void onClick(View view) {
                EditText addrTxt =
                        (EditText)MainActivity.this.findViewById(R.id.etText);
                EditText msgTxt =
                        (EditText)MainActivity.this.findViewById(R.id.etMsgText);
                try {
                    sndMsg(
                            addrTxt.getText().toString(),msgTxt.getText().toString());
                    //	Toast.makeText(MainActivity.this, "SMS being drop", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "What the $#$#@!!! SMS",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }//end Onclick
        });  //nothing in between

        btnSOS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {


//          5555



                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    // increaseSize();
                    Toast.makeText(MainActivity.this, "HOLDING BUTTON",
                            Toast.LENGTH_LONG).show();

                } else {

                        // resetSize();
                        Toast.makeText(MainActivity.this, "SOS sent!!!!",
                                Toast.LENGTH_LONG).show();

                        // SEnD SOS message here with exception


                    try {

                        smsMgrTwo = SmsManager.getDefault();
                        smsMgrTwo.sendTextMessage(phonNum, null, msg, null, null);
                    }catch ( Exception e){
                        e.printStackTrace();
                    }





                    // works but who do you send. .



                }
                return false;
            }
        });

    } // End OnCreate. .5554
    //---------------------------
    private void sndMsg(String address, String message)throws Exception
    {
//		SmsManager smsMgr = null;
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
//			smsMgr = SmsManager.getDefault();
//		}

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {

        // update message;
        phonNum = address;
        msg = message;

        Toast.makeText(MainActivity.this, "Saved. . .",
                Toast.LENGTH_LONG).show();

        // smsMgr.sendTextMessage(address, null, message, null, null);
        // }
    }

}