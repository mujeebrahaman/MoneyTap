
package demo.android.moneytap.com.moneytap.framework.data.remote;

import demo.android.moneytap.com.moneytap.framework.ServiceGenerator;
import demo.android.moneytap.com.moneytap.framework.response.GetContentData;
import demo.android.moneytap.com.moneytap.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mujeeb on 26/01/17.
 */
/* to call web api */
public class CloudDataConnector {

    private static final String TAG = CloudDataConnector.class.getName();

    private static CloudService mServiceEndpoint;

    private static CloudDataConnector instance = null;

    public static CloudDataConnector getInstance() {
        if (null == instance) {
            instance = new CloudDataConnector();
        }
        return instance;
    }

    public CloudDataConnector() {
        mServiceEndpoint = ServiceGenerator.getRestService(Constants.BASE_STUB_URL);
    }

    /**
     * Method calling Retrofit api for getting the response for get Questions
     * List
     *
     * @param responseHandler : Response Handler
     */
    public void getContentData(final String searchText, final ResponseHandler<GetContentData> responseHandler) {
        mServiceEndpoint.getListDetails(
                "query",
                "json",
                "pageimages|pageterms",
                "2",
                searchText).enqueue(new Callback<GetContentData>() {
            @Override
            public void onResponse(Call<GetContentData> call, Response<GetContentData> response) {
                if (response != null && response.isSuccessful()) {
                    responseHandler.onRequestSuccess(response.body());
                } else {
                    ServiceGenerator.APIError error = ServiceGenerator.parseError(response);
                    responseHandler.onRequestFailure(error.message());
                }
            }

            @Override
            public void onFailure(Call<GetContentData> call, Throwable t) {
                responseHandler.onRequestFailure(t.getMessage());
            }
        });
    }
}
