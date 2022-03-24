package jr.minor2.jiit.com.findall;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Packages extends AppCompatActivity implements View.OnClickListener {
    CardView card1,card2,card3;
    AlertDialog ad;
    AlertDialog.Builder b;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);
        card3=(CardView)findViewById(R.id.card3);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        b=new AlertDialog.Builder(this);
        msg="";

    }


    @Override
    public void onClick(View view) {
        b.setTitle("Confirmation");
        b.setMessage("Purchase confirmed Please check your inbox");
        b.setPositiveButton("OK",null);
        b.create().show();
       // b.create().show();
    }
}
