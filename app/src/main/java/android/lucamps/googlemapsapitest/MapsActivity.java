package android.lucamps.googlemapsapitest;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private LatLng APT = new LatLng(-20.751036, -42.869928);
    private LatLng PF = new LatLng(-20.672017, -43.081624);
    private LatLng DPI = new LatLng(-20.764962, -42.868489);

    //private String APT_title = getString(R.string.apartment);
   /* private String PF_title = getString(R.string.pf_house);
    private String DPI_title = getString(R.string.department);*/

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        
        // Add a marker in Sydney and move the camera
        //LatLng temp = new LatLng(-34, 151);

        //LatLng temp = new LatLng(it.getDoubleExtra("lat",0.0), it.getDoubleExtra("lgn",0.0));

        mMap.addMarker(new MarkerOptions().position(APT).title(getString(R.string.apartment)));
        mMap.addMarker(new MarkerOptions().position(PF).title(getString(R.string.pf_house)));
        mMap.addMarker(new MarkerOptions().position(DPI).title(getString(R.string.department)));

        CameraUpdate update = null;
        if(it.getStringExtra("local").equals("APT")){
            update = CameraUpdateFactory.newLatLngZoom(APT,20);
        }
        assert update != null;
        mMap.animateCamera(update);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
    }


}