package com.niktek.androidtutorial.mapsApi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.niktek.androidtutorial.R;

import java.util.List;
import java.util.Locale;

public class MapInfo extends AppCompatActivity implements LocationListener {

    TextView location_data;
    Button get_location;
    LocationManager locationManager;
    FragmentManager manager;
    Boolean getOnceStatusTrue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_info);

        manager = getSupportFragmentManager();
        location_data = findViewById(R.id.location_data);
        get_location = findViewById(R.id.get_location);

        // Runtime Permissions
        if (ContextCompat.checkSelfPermission(MapInfo.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapInfo.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        get_location.setOnClickListener(v -> {
            getOnceStatusTrue = true;
            getLocation();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack("progress_bar")
                    .replace(R.id.progress_bar, new Loader()).commit();
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, MapInfo.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (getOnceStatusTrue) {
            getOnce(location);
            getOnceStatusTrue = false;
        }
    }

    private void getOnce(@NonNull Location location) {
        Toast.makeText(this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(MapInfo.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            manager.popBackStack("progress_bar", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            location_data.setText(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}