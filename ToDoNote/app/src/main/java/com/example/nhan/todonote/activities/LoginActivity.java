package com.example.nhan.todonote.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhan.todonote.R;
import com.example.nhan.todonote.RegisterInterface;
import com.example.nhan.todonote.models.LoginModel;
import com.example.nhan.todonote.models.RegisterModel;
import com.example.nhan.todonote.networks.LoginInterface;
import com.example.nhan.todonote.networks.ServiceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @OnClick(R.id.btn_login)
    public void onClickLogin(){
        login();
    }
    @OnClick(R.id.btn_register)
    public void onClickRegister(){
        register();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null) {
            autoLogin();
        }
    }

    private void login() {
        ServiceFactory serviceFactory = new ServiceFactory();
        LoginInterface service = serviceFactory.createService(LoginInterface.class);
        Call<LoginModel> call = service.login(edtUsername.getText().toString()
                , edtPassword.getText().toString());
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.body().getResult() == 1){
                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.commit();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }

    private void autoLogin() {
        ServiceFactory serviceFactory = new ServiceFactory();
        LoginInterface service = serviceFactory.createService(LoginInterface.class, this);
        Call<LoginModel> call = service.login(edtUsername.getText().toString()
                , edtPassword.getText().toString());
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.body().getResult() == 1){
                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.commit();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }

    private void register(){
        Log.d("test", "onClick");
        ServiceFactory serviceFactory = new ServiceFactory();
        RegisterInterface service = serviceFactory.createService(RegisterInterface.class);
        Call<RegisterModel> call = service.registration(edtUsername.getText().toString()
                , edtPassword.getText().toString());
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                Log.d("test", response.code() + "");
                    if(response.body().getResult() == 1){
                        Toast.makeText(LoginActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
