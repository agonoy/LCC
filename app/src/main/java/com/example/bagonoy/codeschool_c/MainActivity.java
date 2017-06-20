package com.example.bagonoy.codeschool_c;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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


import static android.Manifest.permission_group.LOCATION;


public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView textView;
    EditText editTextPhone;
    EditText editTextMessage;
    Button button;

    final int MAX_NUMBER__SMS_SENT = 2;

    private String phonNum, msg;  // SMS, holds data
    SmsManager smsMgrTwo = null;  // SMS, used to pass data
    int count = 0;                // SMS, keeps track of message sent button and number putton pused.

    boolean setOnTouchSwitch = true;  // initial state of setOnTouch

    LocationManager locationManager;  // GPS
    String mapProvider;               // GPS

    Double longCoor = 0.0;          // GPS -long  use to pass
    Double lattCoor = 0.0;         // Gps   Lat use to pass

    String dataOfCoordNameConvertor;        // data of coordinate and name to send via SMS


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        Uri uri = Uri.parse("smsto:5556");
//        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
//        it.putExtra("sms_body", "Here you can set the SMS text to be sent");
//        it.setType("vnd.android-dir/mms-sms");
//        startActivity(it);






        // Start coding here.. down below. .
        // Toast.makeText(MainActivity.this, "Begin of Program", Toast.LENGTH_LONG).show();
//=========================================================================================
        //starT CODE HERE. .

        // if this is called, GPS permission will be access by user.
        chckGPSPermission();


        // Makes use of the GPS.  This will  display LONG and LAT and send it via this awesome
        // tech called SMS. . . .still working on it
        myLocationSOS();


//============================ DONT DELETE ============================
        // this button is the medical button
        final ImageButton btnSOS = (ImageButton) findViewById(R.id.btnSOS);    // button
        final Button btnSnd = (Button) findViewById(R.id.sndBtn);              // button
        final Button disable = (Button) findViewById(R.id.dsblButton);       // dslbButton


        // Reset when SOS after it has been disabled.
        disable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
            }
        });


        //  Will save data
        btnSnd.setOnClickListener(new OnClickListener() {  // btnSnd

            @Override
            public void onClick(View view) {
                EditText addrTxt =
                        (EditText) MainActivity.this.findViewById(R.id.etText);  // display
                EditText msgTxt =
                        (EditText) MainActivity.this.findViewById(R.id.etMsgText);  // display
                try {
                    updateData(
                            addrTxt.getText().toString(), msgTxt.getText().toString());
                    //	Toast.makeText(MainActivity.this, "SMS being drop", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "ERROR!!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }//end Onclick
        });  //nothing in between.


//  this is the second call for permission on SMS. . .
// This one might just be useless. . .  <------- delete in future time.  1 & 2.
        // Part 1 of 2:  check permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {


            Log.d("This App", "Permission is not granted, requesting");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            button.setEnabled(true);
        } else {
            Log.d("This App", "Permission is granted");
           // postCenterToast("Permission to send SMS");
        }


        // This code is for pressing and holding down the botton, finally sending
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
                            //*****    NOTE:  THIS IS TO SEND SMS, future tech    **************

                          //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {

                                smsMgrTwo = SmsManager.getDefault();
                         //   }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {



                                // adding all coordinates and message to be sent as dataOfCoordNameConvertor
                               dataOfCoordNameConvertor = msg + " HELP!! " + "  " + "http://www.google.com/maps/place/"+dataOfCoordNameConvertor;

                                smsMgrTwo.sendTextMessage(phonNum, null, dataOfCoordNameConvertor, null, null);
                                count++;  // count how many sms sent. . MAX 1
                                //    if (count == MAX_NUMBER__SMS_SENT) {

//                                final Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        //Do something after after 3000 seconds
//                                        //  btnSOS.setEnabled(false);  // disabled button
//                                    }
//                                }, 3000);  // 2 seconds

                                postCenterToast("Button Disabled.");

                                //     }
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
            }// end onTouch
        });
    } // End OnCreate...


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


    //========== send SMS   <--------- still working on it
    private void sendSMS() {
        try {

            //  NOTE:  THIS IS TO SEND SMS

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                smsMgrTwo = SmsManager.getDefault();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                smsMgrTwo.sendTextMessage(phonNum, null, msg, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //============ Send Message via sms Old Techonolgy
    private void updateData(String address, String message) {
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

    //============== custom TOAST message display CENTER left portion of screen
    void postCenterToast(String sayWhat) {

        Context context = getApplicationContext();
        CharSequence text = sayWhat;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Displays in Center Left
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
    }


//=============== GPS ============================

    //  1 of 2:implement GPS
    public void myLocationSOS() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mapProvider = locationManager.getBestProvider(criteria, false);

        if (mapProvider != null && !mapProvider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mapProvider);
            locationManager.requestLocationUpdates(mapProvider, 15000, 1, this);

           // convert decimal to string format
           dataOfCoordNameConvertor = locationStringFromLocation(location);


            //creating location in string to pass
            longCoor = location.getLongitude();
            lattCoor = location.getLatitude();


            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Location not Found.", Toast.LENGTH_SHORT).show();
        }

    }

    // 2 of 2 implement GPS
    @Override
    public void onLocationChanged(Location location) {


        TextView longitude = (TextView) findViewById(R.id.longView);
        TextView latitude = (TextView) findViewById(R.id.latView);

        longitude.setText("Current Longitude:\n" + location.getLongitude());
        latitude.setText("Current Latitude:" + location.getLatitude());

        //  String sfd = longitude.toString();  // this ts to convert. .





        // send location to MSN

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }



    public void sendConCatGPSCoordinate(){  // add parameter if main data is separated or cant find
    }


    public void chckGPSPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Allow access to GPS Location, location binary is 0b111,
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, LOCATION}, 0b111);

            Log.d("This App", "Permission is not granted, requesting");  // SMS number is 123.  123 for this High Tech
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            //  button.setEnabled(false);
        } else {
            Log.d("This App", "Permission is granted");
           // postCenterToast("Permission to send SMS");
        }


    }


//    final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//        @Override
//        public void run() {
//            //Do something after after 2000 seconds
//        }
//    }, 2000);  // 2 seconds


// testing if committing is actually committing



// Converts to GPS decimal to string format and returns a string
    public static String locationStringFromLocation(final Location location) {
        return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) +
                "," + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
    }






}// END OF MainActivity
