package jr.minor2.jiit.com.findall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.net.URL;
import java.nio.charset.StandardCharsets;

import dmax.dialog.SpotsDialog;
import jr.minor2.jiit.com.findall.Police.Adapter2;
import jr.minor2.jiit.com.findall.Police.Emergency;
import jr.minor2.jiit.com.findall.Police.Police_Act;

import static java.lang.Integer.max;
import static jr.minor2.jiit.com.findall.Police.Police_Act.recyclerView_pol;
import static jr.minor2.jiit.com.findall.myBooking.recyclerView;

public class Place_API_Handle extends AsyncTask {
    Context context;
    AlertDialog alertDialog;
    String choice,link,encode,temp_data,read_data,user_name;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog=new SpotsDialog(context,"Connecting");
        alertDialog.show();
        /*
        */
    }

    public Place_API_Handle(Context context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        choice=objects[0].toString();
        double lat=Double.parseDouble(objects[1].toString());
        double log=Double.parseDouble(objects[2].toString());

        if(choice.equals("police")){
            Place_Data.category="police";
            link="https://maps.googleapis.com/maps/api/place/search/json?location="+Double.toString(lat)+","+Double.toString(log)+"&rankby=distance&types=police&sensor=false&key=AIzaSyC-Cm10ULMMTjTuT9sCWVO9xKr_ZMTq1uo";
        }
        else if(choice.equals("hospital")){
            Place_Data.category="hospital";
            link="https://maps.googleapis.com/maps/api/place/search/json?location="+Double.toString(lat)+","+Double.toString(log)+"&rankby=distance&types=doctor&sensor=false&key=AIzaSyB6YhGLJAJuvi3xUNR2PK8HTvl6AbvuDVY";
        }
        else if(choice.equals("mechanic")){
            Place_Data.category="mechanic";
            link="https://maps.googleapis.com/maps/api/place/search/json?location="+Double.toString(lat)+","+Double.toString(log)+"&rankby=distance&types=car_repair&sensor=false&key=AIzaSyB6YhGLJAJuvi3xUNR2PK8HTvl6AbvuDVY";
        }
        read_data="";
        try{
            URL url= null;
            url = new URL(link);
            HttpURLConnection httpURLConnection= null;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            InputStream inputStream= null;
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));

            while((temp_data=bufferedReader.readLine())!=null){
                read_data+=temp_data;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        alertDialog.dismiss();
        if(!read_data.equals("")){
            String plink="",pname="",address="",rate="";
            String lat="";
            String log="";

            if(!read_data.contains("error_message")){
                if(Place_Data.name.size()!=0){
                    Place_Data.name.clear();
                    Place_Data.rate.clear();
                    Place_Data.lat.clear();
                    Place_Data.log.clear();
                    Place_Data.pic.clear();
                }
                try {
                    JSONObject json=new JSONObject(read_data);
                    JSONArray results=json.getJSONArray("results");
                    for(int i=0; i<results.length(); i++){
                        JSONObject police=results.getJSONObject(i);
                        plink=police.getString("icon");
                        pname=police.getString("name");
                        address=police.getString("vicinity");
                        lat=(police.getJSONObject("geometry").getJSONObject("viewport").getJSONObject("northeast").getString("lat"));
                        log=(police.getJSONObject("geometry").getJSONObject("viewport").getJSONObject("northeast").getString("lng"));

                        //temp3=police.getJSONObject("geometry").getJSONObject("viewport").getJSONObject("northeast").getString("lat").toString();
                        //break;

                        Place_Data.name.add(pname);
                        Place_Data.pic.add(plink);
                        Place_Data.address.add(address);
                        Place_Data.lat.add(Double.parseDouble(lat));
                        Place_Data.log.add(Double.parseDouble(log));

                    }
                    /*if(choice.equals("hospital")){
                        if(Emergency.hname.size()==0){
                            for(int i=0; i<max(Place_Data.name.size(),4); i++){
                                Emergency.hname.add(Place_Data.name.get(i));
                                Emergency.haddress.add(Place_Data.address.get(i));
                                Emergency.hpic.add(Place_Data.pic.get(i));
                                Emergency.hlat.add(Place_Data.lat.get(i));
                                Emergency.hlog.add(Place_Data.log.get(i));
                            }
                        }
                    }
                    else if(choice.equals("police")){
                        if(Emergency.pname.size()==0){
                            for(int i=0; i<max(Place_Data.name.size(),4); i++){
                                Emergency.pname.add(Place_Data.name.get(i));
                                Emergency.paddress.add(Place_Data.address.get(i));
                                Emergency.ppic.add(Place_Data.pic.get(i));
                                Emergency.plat.add(Place_Data.lat.get(i));
                                Emergency.plog.add(Place_Data.log.get(i));
                            }
                        }
                    }
                    else if(choice.equals("mechanic")){
                        if(Emergency.mname.size()==0){
                            for(int i=0; i<max(Place_Data.name.size(),4); i++){
                                Emergency.mname.add(Place_Data.name.get(i));
                                Emergency.maddress.add(Place_Data.address.get(i));
                                Emergency.mpic.add(Place_Data.pic.get(i));
                                Emergency.mlat.add(Place_Data.lat.get(i));
                                Emergency.mlog.add(Place_Data.log.get(i));
                            }
                        }
                    }*/


                }
                catch (JSONException e) {
                    Toast.makeText(context,e.getMessage()+" Check your Internet Connection",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context,"Error 404 Try Again After Sometime or check Internet Connection",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                context.startActivity(new Intent(context, Police_Act.class));
            }
            else{
                Toast.makeText(context,"Server overload please try after 7 seconds",Toast.LENGTH_SHORT).show();
            }
        }
        else{

        }
        //Toast.makeText(context,read_data,Toast.LENGTH_SHORT).show();
    }
}
