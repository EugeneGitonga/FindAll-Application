package jr.minor2.jiit.com.findall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import jr.minor2.jiit.com.findall.Police.Police_Act;
import mehdi.sakout.fancybuttons.FancyButton;

public class Home_Page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    ImageButton mech,hosp,pol;
    TextView tname1,tuser1;
    String name="",user="";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        tname1=(TextView)findViewById(R.id.drawer1);
        tuser1=(TextView)findViewById(R.id.drawer2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mech=(ImageButton) findViewById(R.id.btn_mech);
        hosp=(ImageButton) findViewById(R.id.btn_hosp);
        pol=(ImageButton)findViewById(R.id.btn_pol);

        Picasso.get().load(R.drawable.pichosp).fit().centerCrop().into(hosp);
        Picasso.get().load(R.drawable.picpol).fit().centerCrop().into(pol);
        Picasso.get().load(R.drawable.picmech).fit().centerCrop().into(mech);

        mech.setOnClickListener(this);
        pol.setOnClickListener(this);
        hosp.setOnClickListener(this);


         sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
         name=sharedPreferences.getString("name","Lakshya");
         user=sharedPreferences.getString("user","laskya002@gmail.com");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_past) {
            startActivity(new Intent(this,myBooking.class));
            // Handle the camera action
        }
        else if (id == R.id.nav_pack) {
            startActivity(new Intent(this,Packages.class));
            // Handle the camera action
        }
        else if (id == R.id.nav_signout) {
            Place_Data.user_mail="";
            Place_Data.user_name="";
            Toast.makeText(this,"Session Logged Out",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,Login_Home.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(Place_Data.vendor.size()>0){
            Place_Data.vendor.clear();
            Place_Data.vehicle.clear();
            Place_Data.date.clear();

        }
        Place_Data.sel_rate="";
        Place_Data.sel_log=0.0;
        Place_Data.sel_log=0.0;
        Place_Data.sel_address="";
        Place_Data.sel_name="";
        if(Place_Data.rate.size()>0){
            Place_Data.rate.clear();
        }
        if(Place_Data.pic.size()>0){
            Place_Data.pic.clear();
        }
        if(Place_Data.name.size()>0){
            Place_Data.name.clear();
        }
        if(Place_Data.address.size()>0){
            Place_Data.address.clear();
        }
        int id=view.getId();
        if(id==R.id.btn_mech){
            //new Cloud_Handle(this).execute("place");
            new Place_API_Handle(this).execute("mechanic",28.5243,77.3525);
            //startActivity(new Intent(this,Login_Home.class));
        }
        else if(id==R.id.btn_pol){
            new Place_API_Handle(this).execute("police",28.5243,77.3525);
            //startActivity(new Intent(this,Login_Home.class));
        }
        else{
            new Place_API_Handle(this).execute("hospital",28.5243,77.3525);
            //startActivity(new Intent(this,Police_Act.class));
        }
        //startActivity(new Intent(this, Police_Act.class));
    }
}
