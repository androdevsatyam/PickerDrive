package com.connection;

import com.model.FormModel;
import com.model.TransactionModel;
import com.model.UserModel;
/*

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
*/

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ConMethod {

    @FormUrlEncoded
    @POST("/userRegisterApi")
    void register(@Field("name") String name, @Field("email") String email, @Field("phone") String phone, @Field("password") String pass, Callback<UserModel> callback);

    @FormUrlEncoded
    @POST("/checkLoginApi")
    void login(@Field("username") String name, @Field("password") String pass , Callback<UserModel> callback);

    @FormUrlEncoded
    @POST("/insertBooking")
    void insertform(@Field("form_type") String formtype,@Field("user_id") String id,@Field("name") String name,@Field("date") String date,@Field("loading_date") String LOADING_DATE,@Field("mobile") String MOBILE,@Field("address") String ADDRESS,@Field("company_name") String COMPANY_NAME,@Field("car_name") String CAR_NAME,@Field("vehicle_weight_capacity") String VEHICLE_WEIGHT_CAPACITY,@Field("vehicle_no") String VEHICLE_NO,@Field("vehicle_type") String VEHICLE_TYPE,@Field("labour_available") String LABOUR_AVAILABLE,@Field("labour_requirement") String LABOUR_REQUIREMENT,@Field("material_detail") String MATERIAL_DETAIL,@Field("material_weight")String MATERIAL_WEIGHT,@Field("storage_time") String STORAGE_TIME,@Field("transport_type")String TRANSPORT_TYPE,@Field("from") String FROM,@Field("to") String TO,@Field("requirement") String REQUIREMENT, Callback<FormModel> callback);

    @FormUrlEncoded
    @POST("/getBookings")
    void getFormData(@Field("user_id") String userid,@Field("city") String city,@Field("date") String date,
                     @Field("form_type") String form_type, Callback<TransactionModel> callback);

}
