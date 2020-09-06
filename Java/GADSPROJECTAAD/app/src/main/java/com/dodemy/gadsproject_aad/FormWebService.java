package com.dodemy.gadsproject_aad;

import android.widget.EditText;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FormWebService {
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> completeWebService(
            @Field("entry.1824927963") EditText email,
            @Field("entry.1877115667") EditText name,
            @Field("entry.2006916086") EditText lastName,
            @Field("entry.284483984") EditText projectLink

    );
}
