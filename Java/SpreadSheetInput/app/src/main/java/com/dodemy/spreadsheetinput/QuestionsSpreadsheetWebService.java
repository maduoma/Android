package com.dodemy.spreadsheetinput;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsSpreadsheetWebService {

    @POST("1FAIpQLScolwkVA8YQWSbEHSW4voRUAqnolNQQOxRSOtkyn-DUUMAlEA/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.1235110475") String name,
            @Field("entry.315130497") String answerQuestionCat
    );

}
