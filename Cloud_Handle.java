package jr.minor2.jiit.com.findall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import dmax.dialog.SpotsDialog;

import static jr.minor2.jiit.com.findall.myBooking.recyclerView;
import static jr.minor2.jiit.com.findall.myBooking.text;

public class Cloud_Handle extends AsyncTask implements DialogInterface.OnClickListener {
    Context context;
    AlertDialog alertDialog;
    String choice;
    SharedPreferences sharedPreferences;
    String link,encode,temp_data,read_data,user_name,user_mail,sign_mail,sign_user;

    public Cloud_Handle(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog=new SpotsDialog(context,"Connecting");
        alertDialog.show();
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        choice=objects[0].toString();
        link="https://192.168.43.238/Script.php";
        switch (choice) {
            case "login": {
                user_mail = objects[1].toString();
                String pass = objects[2].toString();
                encode = URLEncoder.encode("type") + "=" + URLEncoder.encode("login") + "&" +
                        URLEncoder.encode("user_id") + "=" + URLEncoder.encode(user_mail) + "&" +
                        URLEncoder.encode("pass") + "=" + URLEncoder.encode(pass);
                break;
            }
            case "place":
                encode = "";
                break;
            case "getbook": {
                String user = objects[1].toString();
                encode = URLEncoder.encode("type") + "=" + URLEncoder.encode("getbook") + "&" +
                        URLEncoder.encode("user_id") + "=" + URLEncoder.encode(user);
                break;
            }
            case "signup": {
                String user = objects[1].toString();
                String pass = objects[2].toString();
                String name = objects[3].toString();
                sign_mail = user;
                sign_user = name;
                user_name = name;
                encode = URLEncoder.encode("type") + "=" + URLEncoder.encode("signup") + "&" +
                        URLEncoder.encode("user_id") + "=" + URLEncoder.encode(user) + "&" +
                        URLEncoder.encode("pass") + "=" + URLEncoder.encode(pass) + "&" +
                        URLEncoder.encode("user_name") + "=" + URLEncoder.encode(name);
                break;
            }
            case "dobook": {
                String user = objects[1].toString();
                String vendor = objects[2].toString();
                String vehicle = objects[3].toString();
                String prob = objects[4].toString();
                String date = objects[5].toString();
                encode = URLEncoder.encode("type") + "=" + URLEncoder.encode("dobook") + "&" +
                        URLEncoder.encode("user_id") + "=" + URLEncoder.encode(user) + "&" +
                        URLEncoder.encode("vendor") + "=" + URLEncoder.encode(vendor) + "&" +
                        URLEncoder.encode("vehicle") + "=" + URLEncoder.encode(vehicle) + "&" +
                        URLEncoder.encode("prob") + "=" + URLEncoder.encode(prob) + "&" +
                        URLEncoder.encode("date") + "=" + URLEncoder.encode(date);
                break;
            }
        }
        read_data="";
        try {
            URL url= null;
            url = new URL(link);
            HttpURLConnection httpURLConnection= null;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            OutputStream out = null;
            out = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= null;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            bufferedWriter.write(encode);
            bufferedWriter.flush();
            bufferedWriter.close();
            out.close();
            InputStream inputStream= null;
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            while((temp_data=bufferedReader.readLine())!=null){
                read_data+=temp_data;
            }


    }
    catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        alertDialog.dismiss();
            //Toast.makeText(context,read_data,Toast.LENGTH_SHORT).show();
            if(!read_data.equals("exists")){
                if(choice.equals("login")){
                    if(read_data.equals("not_exists")){
                        Toast.makeText(context,"No Account Found with this Email",Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,SignUp_Act.class));
                    }
                    else{
                /*SharedPreferences.Editor op= sharedPreferences.edit();
                op.putBoolean("login_status",true);
                op.putString("user-name",read_data);*/
                        Place_Data.user_name=read_data;
                        Place_Data.user_mail=user_mail;
                        context.startActivity(new Intent(context,Home_Page.class));
                    }
                }
                else if(choice.equals("signup")){
                    if(read_data.equals("exists")){
                        Toast.makeText(context,"Email Already registered with Us",Toast.LENGTH_SHORT).show();
                        //context.startActivity(new Intent(context,SignUp_Act.class));
                    }
                    else if(read_data.equals("success")){
                        Place_Data.user_mail=sign_mail;
                        Place_Data.user_name=sign_mail;
                /*SharedPreferences.Editor op= sharedPreferences.edit();
                op.putBoolean("login_status",true);
                op.putString("user-name",user_name);
                op.commit();*/
                        context.startActivity(new Intent(context,Home_Page.class));

                    }

                }
                else if(choice.equals("dobook")){
                    if(read_data.equals("success")){
                        AlertDialog.Builder b=new AlertDialog.Builder(context);
                        b.setTitle("Confirmation");
                        b.setMessage("Booking confirmed Please check your inbox\n Mechanic Estimated Time- 17mins");
                        b.setPositiveButton("OK",this);
                        b.create().show();
                    }

                }
                else if(choice.equals("getbook")){
                    if(Place_Data.date.size()>0){
                        Place_Data.date.clear();
                        Place_Data.vehicle.clear();
                        Place_Data.vendor.clear();
                    }
                    if(!read_data.equals("empty")){
                        text.setVisibility(View.INVISIBLE);
                        String temp[]=read_data.split("@");
                        for(int i=0; i<temp.length; i++){
                            String temp2[]=temp[i].split("#");
                            Place_Data.vendor.add(temp2[0]);
                            Place_Data.date.add(temp2[1]);
                            Place_Data.vehicle.add(temp2[3]);
                        }
                        recyclerView.setAdapter(new Adapter3(context,Place_Data.vendor,Place_Data.date,Place_Data.vehicle));

                    }
                }
                else if(choice.equals("place")){

                }
            }
            else{
                Toast.makeText(context,"Check Internet Connectivity",Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i==DialogInterface.BUTTON_POSITIVE){
            context.startActivity(new Intent(context,Home_Page.class));
        }
    }
}

