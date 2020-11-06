package android.lucamps.googlemapsapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button myLocation_bt = findViewById(R.id.bt_myLocation);

        myLocation_bt.setOnClickListener(view -> {
            Intent it = new Intent (getBaseContext(), MapsActivity.class);
            startActivity(it);
        });
    }
}