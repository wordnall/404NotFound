package network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YummlyApiClient {

    private final String apiKey = "ec3e34e0bb6801670dbd3dbd02ce7608";
    private final String appId = "4911b643";

    private static OkHttpClient client;

    private YummlyApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url();

                HttpUrl modUrl = url.newBuilder()
                    .addQueryParameter("_app_key", apiKey)
                    .addQueryParameter("_app_id", appId)
                    .build();

                Request modRequest  = request.newBuilder()
                    .url(modUrl).build();
                return chain.proceed(modRequest);
            }
        });
        client = builder.build();
    }

    public OkHttpClient client() {
        return client;
    }

    public static class Builder {
        private static YummlyApiClient client = new YummlyApiClient();

        public YummlyApiClient build() {
            YummlyApiClient output = client;
            // reset client for next time
            client = new YummlyApiClient();
            return output;
        }
    }

}
