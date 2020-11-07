package android.lucamps.googlemapsapitest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private final LatLng APT = new LatLng(-20.751036, -42.869928);
    private final LatLng PF = new LatLng(-20.672017, -43.081624);
    private final LatLng DPI = new LatLng(-20.764962, -42.868489);
    public LatLng myLocation= new LatLng(-20.764960, -42.868487);;
    private GoogleMap mMap;

    private Marker myLocationMarker;

    public LocationManager lm;
    public Criteria criteria;
    public String provider;
    public int REQUISITION_TIME_LATLONG = 5000;
    public int DISTANCE_IN_METERS = 0;

    void setMyLocation(double lat, double lng) {
        myLocation = new LatLng(lat, lng);

        if(myLocationMarker != null)
            myLocationMarker.remove();

        myLocationMarker = mMap.addMarker(new MarkerOptions().position(myLocation).title("Minha Localização").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Location Manager
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        // Tests if has GPS
        PackageManager packageManager = getPackageManager();
        boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);

        // Sets criteria accuracy
        if (hasGPS) {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            Log.i("LOCATION", "Using GPS");
        } else {
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            Log.i("LOCATION", "Using internet");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    protected void updateLocation(){
        // Gets the best provider
        provider = lm.getBestProvider(criteria, true);

        if (provider == null) {
            Log.e("PROVIDER", "Provider not found!");
        } else {
            Log.i("PROVIDER", "Using: " + provider);

            // Gets location update
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lm.requestLocationUpdates(provider, REQUISITION_TIME_LATLONG, DISTANCE_IN_METERS, this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateLocation();
    }

    @Override
    protected void onDestroy() {
        // Stops location manager
        lm.removeUpdates(this);

        Log.w("PROVIDER", "Provider " + provider + " stopped!");
        super.onDestroy();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        // Creates a random point
        /*final Location randomPoint = new Location(provider);
        randomPoint.setLatitude(DPI.latitude);
        randomPoint.setLongitude(DPI.longitude);*/

        double lat = location.getLatitude();
        double lng = location.getLongitude();
        setMyLocation(lat, lng);

        //mMap.addMarker(new MarkerOptions().position(myLocation).title("Minha Localização").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        /*double speed = location.getSpeed() * 3.6; //converts into km/h
        double distance = location.distanceTo(randomPoint) / 1000; //converts into km*/

        /*DecimalFormat df = new DecimalFormat("0.##");

        Toast.makeText(this, "Latitude: " + lat +
                                            "\nLongitude: " + lng,
                                            Toast.LENGTH_LONG).show();
         */

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent it = getIntent();
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(APT).title(getString(R.string.apartment)));
        mMap.addMarker(new MarkerOptions().position(PF).title(getString(R.string.pf_house)));
        mMap.addMarker(new MarkerOptions().position(DPI).title(getString(R.string.department)));
        //updateLocation();
        //mMap.addMarker(new MarkerOptions().position(myLocation).title("Minha Localização").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        CameraUpdate update;
        switch (it.getStringExtra("local")){
            case "APT":
                update = CameraUpdateFactory.newLatLngZoom(APT,18);
                break;
            case "DPI":
                update = CameraUpdateFactory.newLatLngZoom(DPI,18);
                break;
            case "PF":
                update = CameraUpdateFactory.newLatLngZoom(PF,18);
                break;
            default:
                update = CameraUpdateFactory.newLatLngZoom(myLocation,20);
                break;
        }
        mMap.animateCamera(update);
    }

    public void onClick_Vicosa(View v){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(APT,18);
        mMap.animateCamera(update);
    }

    public void onClick_PF(View v){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(PF,18);
        mMap.animateCamera(update);
    }

    public void onClick_DPI(View v){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(DPI,18);
        mMap.animateCamera(update);
    }

    public void onClick_myLocalization(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLocation,18);
        mMap.animateCamera(update);
    }
}