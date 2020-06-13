package com.huatec.hiot_cloud.data;


import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.devicelist.bean.DeviceBean;
import com.huatec.hiot_cloud.ui.devicelist.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口
 */
public interface NetworkService {

    String baseUrl = "http://114.67.88.191:8080";

    @POST("/auth/login")
    Observable<ResultBase<LoginResultDTO>> login(@Query("username") String userName, @Query("password") String password,
                                                 @Query("loginCode") String loginCode);

    @GET("/user/one")
    Observable<ResultBase<UserBean>> getUserInfo(@Header("Authorization") String authorization);


    @PUT("/user/email")
    Observable<ResultBase<String>> updateEmail(@Query("email") String email, @Header("Authorization") String authorization);

    @PUT("/user/password ")
    Observable<ResultBase<String>> updatePassword(@Query("oldpassword") String oldpassword, @Query("newpassword") String newpassword,
                                                  @Query("confirmpassword") String confirmpassword, @Header("Authorization") String authorization);

    @POST("/user/register")
    Observable<ResultBase<UserBean>> register(@Body UserBean userBean);

    @POST("/user/img")
    @Multipart
    Observable<ResultBase<String>> uploadImg(@Part MultipartBody.Part file,
                                             @Header("Authorization") String authorization);

    @POST("/auth/logout")
    Observable<ResultBase> logout(@Header("Authorization") String authorization);

    @POST("/holder/device/{device_pk}")
    Observable<ResultBase> bindDevice(@Path("device_pk") String device_pk, @Header("Authorization") String authorization);

    @GET("/holder/user")
    Observable<ResultBase<List<DeviceBean>>> listBindDevice(@Query("bonding") int bonding, @Header("Authorization") String authorization);
}
