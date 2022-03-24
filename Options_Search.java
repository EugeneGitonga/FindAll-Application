package jr.minor2.jiit.com.findall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import mehdi.sakout.fancybuttons.FancyButton;

public class Options_Search extends AppCompatActivity implements View.OnClickListener{
    ImageButton mech,hosp,pol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__search);
        mech=(ImageButton) findViewById(R.id.btn_mech);
        hosp=(ImageButton)findViewById(R.id.btn_hosp);
        pol=(ImageButton)findViewById(R.id.btn_pol);
        mech.setOnClickListener(this);
        pol.setOnClickListener(this);
        hosp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
