
package demo.android.moneytap.com.moneytap.framework.data.remote;

import demo.android.moneytap.com.moneytap.framework.response.GetContentData;
import demo.android.moneytap.com.moneytap.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by mujeeb on 26/01/17.
 */
/* Interface Api service */
public interface CloudService {

    @Headers({
            Constants.CONTENT_TYPE_JSON
    })
    @POST(Constants.GET_CONTENT_URL)
    @FormUrlEncoded
    Call<GetContentData> getListDetails(
            @Field("action") String action,
            @Field("format") String format,
            @Field("prop") String prop,
            @Field("formatversion") String formatversion,
            @Field("titles") String titles);

}
