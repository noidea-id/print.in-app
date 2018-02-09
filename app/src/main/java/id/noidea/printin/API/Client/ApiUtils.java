package id.noidea.printin.API.Client;

import id.noidea.printin.API.Interface.InterfaceLogin;

/**
 * Created by daniellindp on 09/02/2018.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://print-in.herokuapp.com/api/";

    public static InterfaceLogin getLogin() {
        return RetrofitApiUtils.getClient(BASE_URL).create(InterfaceLogin.class);
    }
}
