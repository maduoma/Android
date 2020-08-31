package com.dodemy.googlespreadsheetformwithretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SpreadsheetQuestionsWebService {

    @POST("1FAIpQLSdb5tVNimLtDWn6qb7wLoDDDKcTkzdALRRXAOWpIPxRqFbyVg/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.1313674706") String name,
            @Field("entry.863642098") String supportName,
            @Field("entry.741503563") String emergency,
            @Field("entry.59929728") String mobileNo

    );
}
