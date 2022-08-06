package com.hpweb.pickerdrive;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.connection.Connection;
import com.hpweb.pickerdrive.databinding.ActivityLoginBinding;
import com.model.UserModel;

//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    String id, pas, name, email, phone;
    Helper helper;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        helper = new Helper(Login.this);
        db = new DB(this);

        binding.submit.setOnClickListener(v -> {
            id = binding.userId.getText().toString();
            pas = binding.password.getText().toString();
            if (check(id, pas)) {
                helper.showProgress("Logging in.....");
/*
                Con.getToken().login(id, pas, new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });     */


                Connection.getToken().login(id, pas, new Callback<UserModel>() {
                    @Override
                    public void success(UserModel userModel, retrofit.client.Response response) {
                        if (userModel.getStatus().equalsIgnoreCase("true")) {
                            db.setUser(userModel);
                            helper.stopProgress();
                            startActivity(new Intent(Login.this, DashBoard.class));
                            finish();
                        } else {
                            helper.stopProgress();
                            Helper.makeToast(Login.this, userModel.getMsg(), Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        helper.stopProgress();
                        Helper.makeToast(Login.this, "Try After Sometime", Toast.LENGTH_SHORT);
                    }
                });
            }
        });

        binding.signup.setOnClickListener(v -> {
            if (checkSignUP()) {
                helper.showProgress("Signup .....");
                Connection.getToken().register(binding.name.getText().toString(), binding.email.getText().toString(), binding.phone.getText().toString(), binding.signuppassword.getText().toString(), new Callback<UserModel>() {
                    @Override
                    public void success(UserModel userModel, Response response) {
                        if (userModel.getStatus().equalsIgnoreCase("true")) {
                            db.setUser(userModel);
                            helper.stopProgress();
                            startActivity(new Intent(Login.this, DashBoard.class));
                            finish();
                        } else {
                            helper.stopProgress();
                            Helper.makeToast(Login.this, userModel.getMsg(), Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        helper.stopProgress();
                        Helper.makeToast(Login.this, "Try After Sometime", Toast.LENGTH_SHORT);
                    }
                });

            }
        });

        binding.chRegister.setOnClickListener(v -> {
            binding.head.setText(getString(R.string.signup));
            binding.loginForm.setVisibility(View.GONE);
            binding.signupForm.setVisibility(View.VISIBLE);
        });

        binding.chLogin.setOnClickListener(v -> {
            binding.head.setText(getString(R.string.login));
            binding.loginForm.setVisibility(View.VISIBLE);
            binding.signupForm.setVisibility(View.GONE);
        });
    }

    private boolean checkSignUP() {
        if (binding.name.getText().toString().isEmpty() || binding.name.getText().toString().length() < 3) {
            binding.name.setError("Invalid Name");
            binding.name.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {
            binding.email.setError("Invalid Email");
            binding.email.requestFocus();
            return false;
        } else if (binding.phone.getText().toString().isEmpty() || binding.phone.getText().toString().length() < 9) {
            binding.phone.setError("Invalid Mobile");
            binding.phone.requestFocus();
            return false;
        } else if (binding.signuppassword.getText().toString().isEmpty()) {
            binding.signuppassword.setError("Password must be 8 Character");
            binding.signuppassword.requestFocus();
            return false;
        } else if (binding.signuprepassword.getText().toString().isEmpty()) {
            binding.signuprepassword.setError("Re enter Password");
            binding.signuprepassword.requestFocus();
            return false;
        } else if (!binding.signuprepassword.getText().toString().equalsIgnoreCase(binding.signuppassword.getText().toString())) {
            binding.signuprepassword.setError("Password & Re Password Must Be Same");
            binding.signuppassword.setError("Password & Re Password Must Be Same");
            return false;
        } else
            return true;
    }

    private boolean check(String userid, String pass) {
        if (userid.isEmpty()) {
            binding.userId.setError("Invalid UserId");
            return false;
        } else if (pass.isEmpty() || pass.length() < 6) {
            binding.password.setError("Invalid Password");
            return false;
        } else
            return true;
    }
}
