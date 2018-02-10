package id.noidea.printin.API.Interface;

import id.noidea.printin.API.Item.Login.ItemLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by daniellindp on 09/02/2018.
 */

public interface InterfaceLogin {

    @POST("login")
    @FormUrlEncoded
    Call<ItemLogin> login(@Field("username") String username,
                          @Field("password") String password);

}
