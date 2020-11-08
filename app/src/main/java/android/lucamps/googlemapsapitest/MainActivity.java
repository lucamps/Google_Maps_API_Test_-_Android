package android.lucamps.googlemapsapitest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button apartment_bt = findViewById(R.id.bt_apartment);
        final Button department_bt = findViewById(R.id.bt_department);
        final Button pf_bt = findViewById(R.id.bt_pf);
        final Button close_bt = findViewById(R.id.bt_close);

        apartment_bt.setOnClickListener(view -> {
            Intent it = new Intent (getBaseContext(), MapsActivity.class);
            it.putExtra("local","APT");
            startActivity(it);
        });

        department_bt.setOnClickListener(view -> {
            Intent it = new Intent (getBaseContext(), MapsActivity.class);
            it.putExtra("local","DPI");
            startActivity(it);
        });

        pf_bt.setOnClickListener(view -> {
            Intent it = new Intent (getBaseContext(), MapsActivity.class);
            it.putExtra("local","PF");
            startActivity(it);
        });

        close_bt.setOnClickListener(view -> finish());

    }
}