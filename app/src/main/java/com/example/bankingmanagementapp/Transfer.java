package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.service.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transfer extends AppCompatActivity {

    EditText editTextUsername,editTextName,editTextAccountNo,
            editTextAvailableBalance,editTextTransferAmount,editTextDebitAccCurrentBalance,
            editTextCreditAccNo,editTextCreditAccCurrentBalance,editTextcreditAccTotalBalance;
    Button  buttonSearch,buttonTotal,buttonShow,buttonTransfer;

    int id;
    GetDataService service,service2;
    Call<List<Balance>> call;
    //Call <String> call2;

    public  void clearFild(){

        editTextUsername.setText("");
        editTextName.setText("");
        editTextAccountNo.setText("");
        editTextAvailableBalance.setText("");
        editTextTransferAmount.setText("");
        editTextDebitAccCurrentBalance.setText("");
        editTextCreditAccNo.setText("");
        editTextCreditAccCurrentBalance.setText("");
        editTextcreditAccTotalBalance.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        editTextUsername = (EditText) findViewById(R.id.transfeSearchUsernameId);
        buttonSearch = (Button) findViewById(R.id.transferSearchId);

        editTextName = (EditText) findViewById(R.id.transferNameId);
        editTextAccountNo = (EditText) findViewById(R.id.transferAccountNoId);
        editTextAvailableBalance = (EditText) findViewById(R.id.transferAvailableBalanceId);
        editTextTransferAmount = (EditText) findViewById(R.id.transferAmountId);
        buttonTotal = (Button) findViewById(R.id.transferTotalAmountButtonId);

        editTextDebitAccCurrentBalance = (EditText) findViewById(R.id.transferDebitAccCurrentAmountId);
        editTextCreditAccNo = (EditText) findViewById(R.id.transferCreditAccNoId);
        editTextCreditAccCurrentBalance = (EditText) findViewById(R.id.creditAccCurrentBalanceId);
        editTextcreditAccTotalBalance = (EditText) findViewById(R.id.creditAccTotalBalanceId);

        buttonShow = (Button) findViewById(R.id.transferShowButtonId);
        buttonTransfer = (Button) findViewById(R.id.transferButtonId);



        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = editTextUsername.getText().toString();
                //copy
                if( !userName .isEmpty()){

                    service = AppConnection.getRetrofitInstance().create(GetDataService.class);
                    call = service.getBalance(userName);

                    call.enqueue(new Callback<List<Balance>>() {

                        @Override
                        public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {

                            if(response.body().size()>0){
                                List<Balance> balances = response.body();
                                id=balances.get(0).getId();
                                String name = balances.get(0).getName();
                                String accountNo = balances.get(0).getAccountNo();
                                String availableBalance = String.valueOf(balances.get(0).getAmmount());

                                editTextName.setText(name);
                                editTextAccountNo.setText(accountNo);
                                editTextAvailableBalance.setText(availableBalance);

                                Log.d("Okayyyyyyyy Withdraw" ,balances.toString());

                            }else {

                                Toast.makeText(Transfer.this,"Username not found",Toast.LENGTH_SHORT).show();
                                clearFild();
                            }


                        }

                        @Override
                        public void onFailure(Call<List<Balance>> call, Throwable t) {


                        }
                    });
                }else{

                    Toast.makeText(Transfer.this,"User Name Is Empty",Toast.LENGTH_SHORT).show();
                    clearFild();

                }
                //end
                buttonTotal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int availableBalance  = Integer.parseInt(editTextAvailableBalance.getText().toString());
                        String amount =editTextTransferAmount.getText().toString();

                        if( ! amount.isEmpty()){

                            int tAmmount = Integer.parseInt(amount);

                            if(availableBalance>tAmmount && tAmmount>0){

                                int dtAccountCurrentbalance = availableBalance-tAmmount;
                                editTextDebitAccCurrentBalance.setText(String.valueOf(dtAccountCurrentbalance));

                            }else{

                                Toast.makeText(Transfer.this,"You Have Not Avable Balance",Toast.LENGTH_SHORT).show();

                            }



                        }else{

                            Toast.makeText(Transfer.this,"Tranasfer Amount Is Empty",Toast.LENGTH_SHORT).show();
                            clearFild();

                        }



                    }
                });



            }
        });




    }
}
