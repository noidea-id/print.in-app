package id.noidea.printin.API.Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daniellindp on 09/02/2018.
 */

public class RetrofitApiUtils {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
