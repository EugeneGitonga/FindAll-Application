package jr.minor2.jiit.com.findall.Police;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import jr.minor2.jiit.com.findall.Place_Data;
import jr.minor2.jiit.com.findall.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class Police_Details extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    GoogleMap mMap;
    double PIx = 3.141592653589793;
    double RADIUS = 6378.16;
    FancyButton call;
    TextView dist,addr,rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police__details);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);
        call=(FancyButton)findViewById(R.id.call_pol);
        call.setOnClickListener(this);
        dist=(TextView)findViewById(R.id.det_dist);
        rate=(TextView)findViewById(R.id.det_rate);
        addr=(TextView)findViewById(R.id.det_addr);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String title;
        Double lat,log;
        lat=Place_Data.sel_lat;
        log=Place_Data.sel_log;
        LatLng location = new LatLng(lat,log);
        mMap.addMarker(new MarkerOptions().position(location).title(Place_Data.sel_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 8.5f ) );
    }

    @Override
    protected void onStart() {
        super.onStart();
        String dist1=Double.toString(DistanceBetweenPlaces(Place_Data.sel_log,Place_Data.sel_lat,77.3639,28.6208));
        int index_dot=dist1.indexOf(".");
        String distance="Distance- "+dist1.substring(0,index_dot+2)+" Km";
        dist.setText(distance);
        addr.setText(Place_Data.sel_address);

    }

    public double Radians(double x)
    {
        return x * PIx / 180;
    }
    public  double DistanceBetweenPlaces(
            double lon1,
            double lat1,
            double lon2,
            double lat2)
    {
        double dlon = Radians(lon2 - lon1);
        double dlat = Radians(lat2 - lat1);

        double a = (Math.sin(dlat / 2) * Math.sin(dlat / 2)) + Math.cos(Radians(lat1)) * Math.cos(Radians(lat2)) * (Math.sin(dlon / 2) * Math.sin(dlon / 2));
        double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return angle * RADIUS;
    }


    @Override
    public void onClick(View view) {
        String nos="";
        if(Place_Data.category.equals("police")){
            nos="tel:100";
        }
        else{
            nos="tel:102";
        }

        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(nos));
        startActivity(dialIntent);
    }
}
