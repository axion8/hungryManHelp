package com.example.strtrk.hungrymanhelp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button buttonGPS;
    private TextView textGPS, textLat, textLon, textAlt, textTime;
    private LocationListener mlocListener;
    private int getLocationPermission;
    private double myLat, myLon, myAlt, myTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*CHECK IF WE HAVE LOCATION PERMISSION*/
        if (ContextCompat.checkSelfPermission(this,
                "android.permission.ACCESS_FINE_LOCATION")
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    "android.permission.ACCESS_FINE_LOCATION")) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{"android.permission.ACCESS_FINE_LOCATION"},
                        getLocationPermission);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }/*END: CHECK IF WE HAVE LOCATION PERMISSION*/

        /*LocationManager that query location everytime it changes*/
        LocationManager mlocManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);


        /*Declare a textBox where we display information/status of things*/
        textGPS = (TextView) findViewById(R.id.text_GPSdisplay);
        textLat = (TextView) findViewById(R.id.textLat);
        textLon = (TextView) findViewById(R.id.textLon);
        textAlt = (TextView) findViewById(R.id.textAlt);
        textTime = (TextView) findViewById(R.id.textTime);
        buttonGPS = (Button) findViewById(R.id.buttonGPS);
        buttonGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textGPS.getText() == "Phase1") {
                    textGPS.setText("TESING 1-2-3");
                } else {
                    textGPS.setText("Phase1");
                }

            }
        });//End setOnClickListener

    }

    /*WORKING HORSE OF GETTING GPS LOC*/
    /*Where we take an action everytime the GPS updates its location*/
    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            myLat = loc.getLatitude();
            myLon = loc.getLongitude();
            myTime = loc.getTime();
            myAlt = loc.getAltitude();

            String Text = "My current location is: " +
                    "Latitud = " + loc.getLatitude() +
                    "Longitud = " + loc.getLongitude();
            textGPS.setText(Text);
            textLat.setText("Lat: "+String.valueOf(myLat));
            textLon.setText("Lon: "+String.valueOf(myLon));
            textAlt.setText("Alt: "+String.valueOf(myAlt));
            textTime.setText("Time: "+String.valueOf(myTime));

        }

        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(),
                    "Gps Enabled",
                    Toast.LENGTH_SHORT).show();
        }

        public void onProviderDisabled(String provider) {

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }   /* End of Class MyLocationListener */
}
