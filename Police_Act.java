package jr.minor2.jiit.com.findall.Police;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import jr.minor2.jiit.com.findall.Place_Data;
import jr.minor2.jiit.com.findall.R;

public class Police_Act extends AppCompatActivity {
    public static RecyclerView recyclerView_pol;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        recyclerView_pol=(RecyclerView)findViewById(R.id.recycler_search);
        textView=(TextView)findViewById(R.id.cat_title);
        recyclerView_pol.setLayoutManager(new LinearLayoutManager(this));
        Place_Data.Police_Act_obj=this;
        recyclerView_pol.setAdapter(new Adapter2(this, Place_Data.name,Place_Data.address,Place_Data.pic));

    }

    @Override
    protected void onStart() {
        super.onStart();
        /*if(Place_Data.catgory.equals("police")  && Place_Data.name.size()==0){
            Place_Data.name.add("Police Station Sector 110");
            Place_Data.name.add("Police Chowki Sultanpur");
        }*/
        String temp="Nearby ";
        if(Place_Data.category.equals("police")){
            temp+="Police Stations";
        }
        else if(Place_Data.category.equals("hospital")){
            temp+="Hospitals";
        }
        else{
            temp+="Mechanics ";
        }
        textView.setText(temp);

    }
}
