package com.example.rosst.Seventh;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main5Activity extends AppCompatActivity {

    private static final int MY_LOCATION_REQUEST_CODE = 0;
    private LocationManager locationManager;
    private List<Locate> locate;
    private MapView mapView;
    private Location myLocation;
    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            resume();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        locate = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Locate>>() {
        }.getType();
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput("banks.json");
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            locate = gson.fromJson(streamReader, itemsListType);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this, "Application need access to the location", Toast.LENGTH_LONG).show();
                }
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
            }
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, listener);
            resume();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (requestCode == MY_LOCATION_REQUEST_CODE) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, listener);
                    resume();
                } else {
                    Toast.makeText(getApplication(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void resume() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        List<Float> distance = new ArrayList<>();
        for (int i = 0; i < locate.size(); i++) {
            Location location = new Location("endLocation");
            location.setLatitude(locate.get(i).getLatitude());
            location.setLongitude(locate.get(i).getLongitude());
            distance.add(myLocation.distanceTo(location) / 1000);
        }
        final int numMinDistance = distance.indexOf(Collections.min(distance));
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(new LatLng(myLocation.getLatitude(),
                        myLocation.getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .title("My Position"));
                googleMap.addMarker(new MarkerOptions().position(new LatLng(locate.get(numMinDistance).getLatitude(),
                        locate.get(numMinDistance).getLongitude())).title(locate.get(numMinDistance).getName()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locate.get(numMinDistance).getLatitude(),
                        locate.get(numMinDistance).getLongitude()), 15));
                mapView.onResume();
            }
        });
    }

}
