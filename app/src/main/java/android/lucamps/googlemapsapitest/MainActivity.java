package android.lucamps.googlemapsapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button myLocation_bt = findViewById(R.id.bt_myLocation);
        final Button apartment_bt = findViewById(R.id.bt_apartment);
        final Button department_bt = findViewById(R.id.bt_department);
        final Button pf_bt = findViewById(R.id.bt_pf);

        myLocation_bt.setOnClickListener(view -> {
            Intent it = new Intent (getBaseContext(), MapsActivity.class);
            startActivity(it);
        });

        apartment_bt.setOnClickListener(view -> {
            Intent it = new Intent (getBaseContext(), MapsActivity.class);
            //-20.751036, -42.869928
            double lat = -20.751036;
            double lgn = -42.869928;
            it.putExtra("lat",lat);
            it.putExtra("lgn",lgn);
            it.putExtra("title", R.string.apartment);
            startActivity(it);
        });

        department_bt.setOnClickListener(view -> {

        });

        pf_bt.setOnClickListener(view -> {

        });

    }
}