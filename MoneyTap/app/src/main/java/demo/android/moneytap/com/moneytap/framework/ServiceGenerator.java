package demo.android.moneytap.com.moneytap.framework;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import demo.android.moneytap.com.moneytap.BuildConfig;
import demo.android.moneytap.com.moneytap.framework.data.remote.CloudService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*To get retrofit instance*/
public class ServiceGenerator {
    private static Retrofit retrofit;
    private static Retrofit.Builder restBuilder;

    public static CloudService getRestService(String newApiBaseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30,TimeUnit.SECONDS);
        }
        restBuilder = new Retrofit.Builder()
                .baseUrl(newApiBaseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = restBuilder.build();
        return retrofit.create(CloudService.class);
    }

    public static APIError parseError(Response<?> response){
        Converter<ResponseBody, APIError> converter = retrofit.responseBodyConverter(APIError.class, new Annotation[0]);
        APIError error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }
        return error;
    }

    public static class APIError{
        private String error = "";

        public String message() {
            return error;
        }
    }
}
