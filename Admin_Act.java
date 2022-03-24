package jr.minor2.jiit.com.findall.Police;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import jr.minor2.jiit.com.findall.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class Admin_Act extends AppCompatActivity implements View.OnClickListener {

    FancyButton adm_login;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        adm_login=(FancyButton)findViewById(R.id.btn_login_admin);
        edit=(EditText)findViewById(R.id.passkey);
        adm_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String pass=edit.getText().toString();
        if(pass.equals("1234")){
            startActivity(new Intent(this,Admin_Booking.class));
        }
        else{
            Toast.makeText(this,"Incorrect Passkey",Toast.LENGTH_SHORT).show();
        }
    }
}
