package com.example.strtrk.hungrymanhelp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button buttonGPS;
    private TextView textGPS;
    private LocationListener mlocListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        res = PackageManager.PERMISSION_GRANTED;

        LocationManager mlocManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);



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

        /* Use the LocationManager class to obtain GPS locations */






        textGPS = (TextView) findViewById(R.id.text_GPSdisplay);

    }

    public class MyLocationListener implements LocationListener {






        @Override
        public void onLocationChanged(Location loc) {
            loc.getLatitude();
            loc.getLongitude();
            String Text = "My current location is: " +
                    "Latitud = " + loc.getLatitude() +
                    "Longitud = " + loc.getLongitude();
            Toast.makeText(getApplicationContext(),
                    Text,
                    Toast.LENGTH_SHORT).show();
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
