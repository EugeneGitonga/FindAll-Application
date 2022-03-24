package jr.minor2.jiit.com.findall;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class SignUp_Act extends AppCompatActivity implements View.OnClickListener{
    FancyButton signup;
    MaterialEditText mail,pass,name;
    SharedPreferences sharedPreferences;
    private Object FancyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        mail=(MaterialEditText)findViewById(R.id.sign_mail);
        pass=(MaterialEditText)findViewById(R.id.sign_pass);
        name=(MaterialEditText)findViewById(R.id.sign_name);
        signup=(FancyButton)findViewById(R.id.btn_signup);
        signup.setOnClickListener(this);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
    }


    @Override
    public void onClick(View view) {
        String spass="",sname="",smail="";
        Boolean check1=true,check2=true,check3=true;
        spass=pass.getText().toString().trim();
        sname=name.getText().toString().trim();
        smail=mail.getText().toString().trim();
        if(spass.length()<6){
            Toast.makeText(this,"Password Length should be more than 6",Toast.LENGTH_SHORT).show();
            check1=false;
        }
        if(!smail.contains("@") || !smail.contains(".com")){
            Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show();
            check2=false;
        }
        if(sname.equals("") || smail.equals("") || spass.equals("")){
            Toast.makeText(this,"The fields cannot be left blank",Toast.LENGTH_SHORT).show();
            check3=false;
        }
        if(check1 && check2 && check3 ){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("email",smail);
            editor.apply();
            new Cloud_Handle(this).execute("signup",smail,spass,sname);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Place_Data.user_name="";
        Place_Data.user_mail="";
    }

}
