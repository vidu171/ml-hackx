package intro.demo.files.muj.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView buss;


    EditText toGo, toStart;

    private JSONArray allJSON;
    BussesRecyclerView  bussesRecyclerView;

    Button button  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toGo = findViewById(R.id.go);
        toStart = findViewById(R.id.start);
        button = findViewById(R.id.route);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray finalJSON = new JSONArray();

                for (int i =0;i<allJSON.length();i++){
                    try {
                        JSONArray tempJson = new JSONArray(allJSON.get(i).toString());

                        if(tempJson.get(1).toString().contains(toStart.getText().toString())&& tempJson.get(2).toString().contains(toGo.getText().toString()))
                            finalJSON.put(tempJson);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                bussesRecyclerView.filterList(finalJSON);

            }
        });


        try {
            allJSON = new JSONArray(readJSONFromAsset());
            } catch (JSONException e) {
            e.printStackTrace();
        }

        bussesRecyclerView= new BussesRecyclerView(allJSON,this);
        buss = findViewById(R.id.buss);
        buss.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        buss.setNestedScrollingEnabled(false);
        buss.setAdapter(bussesRecyclerView);

    }

    private void filter(String string, int textChangedFrom) {
        JSONArray jsonArray;
        JSONArray finalJSON = new JSONArray();
        //1 to go 2 to start
        String otherString;
        switch (textChangedFrom){
            case 1: otherString = toStart.getText().toString();
                    for (int i=0; i<allJSON.length();i++){
                        try {
                            jsonArray= new JSONArray(allJSON.get(i).toString());
                            if(jsonArray.get(1).toString().contains(otherString) && jsonArray.get(2).toString().contains(string))
                                finalJSON.put(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    bussesRecyclerView.filterList(finalJSON);

                break;
            case 2:
                otherString = toGo.getText().toString();
                for (int i=0; i<allJSON.length();i++){
                    try {
                        jsonArray= new JSONArray(allJSON.get(i).toString());
                        if(jsonArray.get(2).toString().contains(otherString) && jsonArray.get(1).toString().contains(string))
                            finalJSON.put(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                bussesRecyclerView.filterList(finalJSON);
                break;
        }

    }


    private String readJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("dataset.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
