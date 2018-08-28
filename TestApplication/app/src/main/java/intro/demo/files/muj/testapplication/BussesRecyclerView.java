package intro.demo.files.muj.testapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class BussesRecyclerView extends RecyclerView.Adapter<BussesRecyclerView.MyViewHolder>{


    JSONArray jsonArray;
    Context mContext;

    BussesRecyclerView(JSONArray jsonArray,Context mContext){
        this.jsonArray = jsonArray;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tracks, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

       for(int j=0;j<jsonArray.length();j++){
           try {
               JSONArray Satisfation = new JSONArray(jsonArray. get(i).toString());

               myViewHolder.textView.setText("Satisfation Level = " + Satisfation.get(3));

           } catch (JSONException e) {
               e.printStackTrace();
           }
       }


        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("Clicked","Clicked");
                Toast.makeText(mContext,"Open maps to continue Journey",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return jsonArray.length();

    }

    public void filterList(JSONArray jsonArray){
        jsonArray = jsonArray;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.direction);
        }
    }
}
