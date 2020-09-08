package com.dodemy.googleformspostwithretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
    @FormUrlEncoded // annotation used in POST type requests
    @POST("1FAIpQLSfMY89ZHoLKCtBgZyzIsPHMmapkJZ4Q-nBiopy4RXC9dzpnSw/formResponse")
        // API endpoints
    Call<SubmitFormResponse> registration(@Field("entry.1209542025") String name,
                                          @Field("entry.1487899751") String email,
                                          @Field("entry.1064900972") String password,
                                          @Field("entry.187022656") String loginType);
    // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class
}
