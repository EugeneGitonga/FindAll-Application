package jr.minor2.jiit.com.findall;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class myBooking extends AppCompatActivity {
    public static TextView text;
    public static RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        text=(TextView)findViewById(R.id.past_stat);
        recyclerView=(RecyclerView)findViewById(R.id.past_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String smail="";
        smail=sharedPreferences.getString("email","gamerkanpur@gmail.com");

        new Cloud_Handle(this).execute("getbook",smail);
    }
}
