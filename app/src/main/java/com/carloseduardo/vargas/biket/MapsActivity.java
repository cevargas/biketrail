package com.carloseduardo.vargas.biket;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.carloseduardo.vargas.biket.dao.PercursoDAO;
import com.carloseduardo.vargas.biket.dao.RotaDAO;
import com.carloseduardo.vargas.biket.models.Percurso;
import com.carloseduardo.vargas.biket.models.Rota;
import com.carloseduardo.vargas.biket.utils.Utils;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe Activity de mapeamento da rota
 * Persiste o percurso no BD
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String TAG = MapsActivity.class.getSimpleName();

    private GoogleMap gMap;
    private LocationManager lm;
    private Location myLocation;
    private ArrayList<LatLng> points;
    private Rota rota = new Rota();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        points = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //new RotaDAO(this).delete();
        //List<Rota> rotasList = new RotaDAO(this).list();
        //System.out.println(rotasList.toString());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            if (!gMap.isMyLocationEnabled())
                gMap.setMyLocationEnabled(true);

            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    points.add(latLng);
                    redrawLine();

                    //salva referencia do percurso
                    savePercurso(location);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Log.d(TAG, "onProviderDisabled");
                    Toast.makeText(
                            getApplicationContext(),
                            "GPS está inativo.",
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Log.d(TAG, "onProviderEnabled");
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.d(TAG, "onStatusChanged");
                }
            });

            myLocation = getLastKnownLocation();

            if (myLocation != null) {
                LatLng userLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14), 1500, null);

                //add marcador inicial no mapa
                //addMarker();

                //salva referencia da rota
                saveRota();
            }
        }
    }

    private Location getLastKnownLocation() {
        lm = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            Location l = lm.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private void redrawLine(){
        //clears all Markers and Polylines
        gMap.clear();

        PolylineOptions options = new PolylineOptions().width(10).color(Color.CYAN).geodesic(true);
        for (int i = 0; i < points.size(); i++) {
            LatLng point = points.get(i);
            options.add(point);
        }
        //add Polyline
        gMap.addPolyline(options);
    }

    private void addMarker() {

        double currentLatitude = myLocation.getLatitude();
        double currentLongitude = myLocation.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("Você iniciou aqui!");
        gMap.addMarker(options);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void saveRota(){
        //salva start da rota
        rota.setSituacao(0);
        rota.setDataHoraSync(null);
        rota.setSync(false);
        rota.setTotalPercurso(0.0);
        rota.setStartLongitude(myLocation.getLongitude());
        rota.setStartLatitude(myLocation.getLatitude());
        Long id = new RotaDAO(this).save(rota);
        rota.setId(id);
    }

    private void savePercurso(Location location){

        if(rota != null && rota.getId() != null) {
            //salva referencia do percurso
            Percurso percurso = new Percurso();
            percurso.setId_rota(rota.getId());
            percurso.setLongitude(location.getLongitude());
            percurso.setLatitude(location.getLatitude());
            percurso.setSpeed(String.valueOf(location.getSpeed()));
            new PercursoDAO(this).save(percurso);
        }
    }
}
