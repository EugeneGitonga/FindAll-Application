package jr.minor2.jiit.com.findall.Police;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import jr.minor2.jiit.com.findall.Place_Data;
import jr.minor2.jiit.com.findall.R;

public class Adapter2 extends RecyclerView.Adapter  {
    Context context;
    ArrayList<String> name,address,pic;
    TextView tname,addr;
    CircleImageView imageview;
    public Adapter2(Context context, ArrayList<String> name, ArrayList<String> address, ArrayList<String> pic) {
        this.context = context;
        this.name = name;
        this.address = address;
        this.pic = pic;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            tname=(TextView)view.findViewById(R.id.search_name);
            addr=(TextView)view.findViewById(R.id.search_place);
            imageview=(CircleImageView)view.findViewById(R.id.search_img);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_search,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Picasso.get().load(pic.get(position)).centerCrop().fit().into(imageview);
        tname.setText(name.get(position));
        addr.setText(address.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Place_Data.sel_name=name.get(position);
                Place_Data.sel_address=address.get(position);
                Place_Data.sel_lat=Place_Data.lat.get(position);
                Place_Data.sel_log=Place_Data.log.get(position);
                //Place_Data.sel_rate=Place_Data.rate.get(position);
                if(!Place_Data.category.equals("mechanic")){
                    context.startActivity(new Intent(context,Police_Details.class));
                }
                else {
                    context.startActivity(new Intent(context,Form_Filling.class));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return address.size();
    }
}
