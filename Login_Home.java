package jr.minor2.jiit.com.findall;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import jr.minor2.jiit.com.findall.Police.Admin_Act;
import mehdi.sakout.fancybuttons.FancyButton;

public class Login_Home extends AppCompatActivity implements View.OnClickListener {
    FancyButton login,signup,admin;
    MaterialEditText email,pass;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__home);
        login=(FancyButton)findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        admin=(FancyButton)findViewById(R.id.btn_admin);
        admin.setOnClickListener(this);
        signup=(FancyButton)findViewById(R.id.btn_signup2);
        signup.setOnClickListener(this);
        email=(MaterialEditText)findViewById(R.id.login_mail);
        pass=(MaterialEditText)findViewById(R.id.login_pass);
        Place_Data.user_mail="";
        Place_Data.user_name="";
        login.setEnabled(false);
        request_permisssion();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void request_permisssion() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE
                },
                1);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Boolean check1=false,check2=false;
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    check1=true;
                    //Log.e("value", "Permission Granted, Now you can use local drive .");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Call Permission Denied",Toast.LENGTH_SHORT).show();
                    //Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
        if(check1 || check2){
            login.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.btn_login){
            String smail="",spass="";
            smail=email.getText().toString().trim();
            spass=pass.getText().toString().trim();
            Boolean check1=true,check2=true;
            if(spass.length()<6){
                Toast.makeText(this,"Password Length should be more than 6",Toast.LENGTH_SHORT).show();
                check1=false;
            }
            if(!smail.contains("@") || !smail.contains(".com")){
                Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show();
                check2=false;
            }
            if(check1 && check2){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("email",smail);
                editor.commit();
                new Cloud_Handle(this).execute("login",smail,spass);
            }
        }
        else if(id==R.id.btn_signup2){
            startActivity(new Intent(this,SignUp_Act.class));
        }
        else if(id==R.id.btn_admin){
            startActivity(new Intent(this, Admin_Act.class));
        }
    }
}
