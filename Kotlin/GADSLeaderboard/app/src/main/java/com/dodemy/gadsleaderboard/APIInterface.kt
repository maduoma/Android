package com.dodemy.gadsleaderboard

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIInterface {
    @FormUrlEncoded // annotation used in POST type requests
    @POST("/retrofit/register.php")
    //API's endpoints
    fun registration(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("logintype") logintype: String?
    ): Call<SignUpResponse?>? // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class
}


/*
   @Post("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
   @FormUrlEncoded
   fun merchartRest(
	@Field("entry.1824927963")emailAddress:String,
	@Field("entry.1877115667")name:String,
	@Field("entry.2006916086")lastName:String,
	@Field("entry.284483984")projectLink:String

   ):Call<Void>
}
 */