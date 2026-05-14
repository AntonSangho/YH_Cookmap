package com.cookandroid.cookmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap gMap;
    MapFragment mapFrag;
    GroundOverlayOptions videoMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("구글지도 활용");

        mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap map) {
        gMap = map;
//        gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.568256, 126.897240), 13));
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng point) {
                videoMark = new GroundOverlayOptions().image(
                        BitmapDescriptorFactory.fromResource(R.drawable.presence_video_busy)).position(point, 400f, 400f);
                gMap.addGroundOverlay(videoMark);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성지도");
        menu.add(0, 2, 0, "일반지도");
        SubMenu sMenu = menu.addSubMenu("유명한 장소 바로가기 >>");
        sMenu.add(0,3,0,"제주도 어디");
        sMenu.add(0,4,0,"광주 어디 ");
        sMenu.add(0,5,0,"서울 어디 ");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case 2:
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.658256, 126.897240), 13));
                return true;
        }
        return false;
    }
}