package android.lucamps.googlemapsapitest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {
    /*private final int LOCATION_PERMISSION = 1;

    public void requestLocationPermission(){
        //Vefifica se é necessário pedir permissão
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //verifica se precisa explicar
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "Permita o aplicativo a usar GPS!",Toast.LENGTH_LONG).show();

                //pede permissão
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_PERMISSION:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
            }
        }
    }*/

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

        close_bt.setOnClickListener(view -> {
            finish();
        });

    }
}