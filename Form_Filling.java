package jr.minor2.jiit.com.findall.Police;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jr.minor2.jiit.com.findall.Cloud_Handle;
import jr.minor2.jiit.com.findall.Place_Data;
import jr.minor2.jiit.com.findall.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class Form_Filling extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText name,phone,prob;
    Spinner spinner;
    FancyButton btn;
    String str_name,str_user,vehicle,date;
    ArrayList<String> spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form__filling);
        name=(EditText)findViewById(R.id.form_name);
        prob=(EditText)findViewById(R.id.form_prob);
        phone=(EditText)findViewById(R.id.form_phone);
        FancyButton btn=(FancyButton)findViewById(R.id.btn_form);
        spinner=(Spinner)findViewById(R.id.spinner);
        spin=new ArrayList<>();
        spin.add("4 Wheeler");
        spin.add("2 Wheeler");
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,spin));
        btn.setOnClickListener(this);
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        str_name=sp.getString("user_name","eugenegitonga");
        str_user=sp.getString("email","eugenegitonga31@gmail.com");
        name.setText(str_name);
        name.setEnabled(false);
        vehicle=spin.get(0);
    }

    @Override
    public void onClick(View view) {
        String sphone=phone.getText().toString();
        String sprob=prob.getText().toString();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String date = df.format(c);
        new Cloud_Handle(this).execute("dobook",str_user, Place_Data.sel_name,vehicle,sprob,date);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        vehicle=spin.get(i);
    }
}
