package jr.minor2.jiit.com.findall;

import java.util.AbstractQueue;
import java.util.ArrayList;

import jr.minor2.jiit.com.findall.Police.Police_Act;

public class Place_Data {
    public static ArrayList<String> pic=new ArrayList<String>();
    public static ArrayList<String> name=new ArrayList<String>();
    public static ArrayList<String> address=new ArrayList<String>();

    public static ArrayList<Double> lat=new ArrayList<Double>();
    public static ArrayList<Double> log=new ArrayList<Double>();
    public static String sel_name,sel_address,category;
    public static double sel_lat,sel_log;
    public static ArrayList<String> rate=new ArrayList<String>();
    public static String sel_rate;
    public static ArrayList<String> vendor=new ArrayList<String>();
    public static ArrayList<String> date=new ArrayList<String>();
    public static ArrayList<String> vehicle=new ArrayList<String>();
    public static String user_name="",user_mail="";
    public  static Police_Act Police_Act_obj;
    //public static int sel_address;
}
