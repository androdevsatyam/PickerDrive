package com.hpweb.pickerdrive;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.connection.Connection;
import com.model.FormModel;
import com.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;

public class Helper {

    ProgressDialog dialog;
    Context context;
    public static String status = "", key_id = "ID", key_name = "NAME", key_mob = "MOBILE", key_email = "EMAIL";

    public Helper(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    public static ArrayAdapter<String> getCities(Context context) {
        String[] cities = context.getResources().getStringArray(R.array.city);

        ArrayAdapter<String> adap = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, cities);
        return adap;
    }

    public static void setDate(TextView date, int dayOfMonth, int month, int year){
        date.setText(year+"-"+month+"-"+dayOfMonth);
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
//        2022-04-26
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public void showProgress(String msg) {
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void stopProgress() {
        dialog.cancel();
    }

    public static void makeToast(Context context, String msg, int Lenght) {
        Toast.makeText(context, msg, Lenght).show();
    }

    public static DatePickerDialog getDatePickerDialog(Context context) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.MyTimePickerDialogTheme, null, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        return datePickerDialog;
    }

    public static void setDataLocal(Context context, UserModel userModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Users", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key_name, userModel.getName());
        editor.putString(key_id, userModel.getUser());
        editor.putString(key_mob, userModel.getPhone());
        editor.putString(key_email, userModel.getEmail());
        editor.apply();
    }

    public static String getDataLocal(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Users", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "NO");
    }


    public static String submitForm(Context context, String formType, String id, FormModel formModel) {

        Connection.getToken().insertform(formType, id, formModel.getName(), formModel.getDate(), formModel.getDate(), formModel.getMOBILE(), formModel.getADDRESS(), formModel.getCOMPANY_NAME(), formModel.getCAR_NAME(), formModel.getVEHICLE_WEIGHT_CAPICITY(), formModel.getVEHICLE_NO(), formModel.getVEHICLE_TYPE(),formModel.getLabour_available(),formModel.getLabour_requirement(), formModel.getMATERIAL_DETAIL(), formModel.getMATERIAL_WEIGHT(), formModel.getSTORAGE_TIME(), formModel.getTRANSPORT_TYPE(), formModel.getFROM(), formModel.getTO(), formModel.getREQUIREMENT(), new Callback<FormModel>() {
            @Override
            public void success(FormModel formModel, retrofit.client.Response response) {
                if (formModel.getStatus().equalsIgnoreCase("true")) {
                    status = "submitted";
                    Helper.makeToast(context, "Form Submitted", Toast.LENGTH_SHORT);
                } else {
                    status = formModel.getMsg();
                    Helper.makeToast(context, formModel.getMsg(), Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                status = error.getLocalizedMessage();
                Helper.makeToast(context, "Try After Sometime", Toast.LENGTH_SHORT);

            }
        });

        return status;
    }

}
