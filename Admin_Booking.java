package jr.minor2.jiit.com.findall.Police;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jr.minor2.jiit.com.findall.Login_Home;
import jr.minor2.jiit.com.findall.R;

public class Admin_Booking extends AppCompatActivity implements View.OnClickListener {
    Button adm3,adm2,adm1,rej1,rej2,rej3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__booking);
        adm1=(Button)findViewById(R.id.acc1);
        rej1=(Button)findViewById(R.id.rej1);

        adm2=(Button)findViewById(R.id.acc2);
        rej2=(Button)findViewById(R.id.rej2);

        adm3=(Button)findViewById(R.id.acc3);
        rej3=(Button)findViewById(R.id.rej3);

        adm1.setOnClickListener(this);
        adm2.setOnClickListener(this);
        adm3.setOnClickListener(this);
        rej1.setOnClickListener(this);
        rej2.setOnClickListener(this);
        rej3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Sucess");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), Login_Home.class));
            }
        });
        builder.create().show();
    }
}
