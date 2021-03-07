
package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.service.GetDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerList extends AppCompatActivity {

    ListView listView;
   /* String country [];*/
    List<Account> country;
    int [] img={R.drawable.thinking,R.drawable.thinking,R.drawable.thinking,R.drawable.thinking,R.drawable.thinking,R.drawable.thinking,R.drawable.thinking};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        GetDataService service = AppConnection.getRetrofitInstance().create(GetDataService.class);
        Call<List<Account>> call = service.getAccount();

        call.enqueue(new Callback<List<Account>>() {

            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

               country= response.body();
                CustomAdapter adepter = null;
                try {
                    adepter = new CustomAdapter(getApplicationContext(),country,img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.setAdapter(adepter);


               // Log.d("okkkkkkkkkkkkkk" ,country.toString());

                Log.d("okkkkkkkkkkkkkk" ,country.toString());




            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

            }
        });


        listView= findViewById(R.id.listViewId);

        /*country = new String [5];*/

        /*country[0]="BANGLADESH";
        country[1]="INDIA";
        country[2]="Bangladesh";
        country[3]="Bangladesh";
        country[4]="Bangladesh";
*/

       // Log.d("okkkkkkkkkkkkkk" ,country.toString());





    }
}
