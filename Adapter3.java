package jr.minor2.jiit.com.findall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import jr.minor2.jiit.com.findall.Police.Adapter2;

import static jr.minor2.jiit.com.findall.myBooking.recyclerView;

public class Adapter3 extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> vendor,date,vechicle;
    TextView book_name,book_prob,book_date;

    public Adapter3(Context context, ArrayList<String> vendor, ArrayList<String> date, ArrayList<String> vechicle) {
        this.context = context;
        this.vendor = vendor;
        this.date = date;
        this.vechicle = vechicle;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            book_name=(TextView)view.findViewById(R.id.book_name);
            book_prob=(TextView)view.findViewById(R.id.book_prob);
            book_date=(TextView) view.findViewById(R.id.book_date);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_book,parent,false);
        return new Adapter3.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        book_name.setText(vendor.get(position));
        book_prob.setText(vechicle.get(position));
        book_date.setText(date.get(position));
        //recyclerView.setAdapter(new Adapter3(context,Place_Data.vendor,Place_Data.date,Place_Data.vehicle));
    }

    @Override
    public int getItemCount() {
        return vendor.size();
    }
}
