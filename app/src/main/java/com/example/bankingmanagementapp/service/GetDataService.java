package com.example.bankingmanagementapp.service;

import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.model.Statement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface GetDataService {

    @GET("/balance/{name}")
    Call<List<Balance>> getBalance(@Path("name") String name);

    @GET("/getAllAccount")
    Call<List<Account>> getAccount();

    @GET("/appLogin/{accountno}")
    Call<List<Account>> getAccountByAccountNo(@Path("accountno") String accountno);

    @GET("/depositBalance/{amount}/{id}")
    Call <String> updateDepositAmount(@Path("amount") int amount ,@Path("id") int id);

    @GET("/allbalance")
    Call<List<Balance>> getAllBalance();


}
