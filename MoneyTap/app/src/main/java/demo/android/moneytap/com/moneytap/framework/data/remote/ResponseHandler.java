package demo.android.moneytap.com.moneytap.framework.data.remote;


import demo.android.moneytap.com.moneytap.framework.model.Model;

/**
 * Created by mujeeb on 26/01/17.
 */
/*Callback for responses*/
public interface ResponseHandler<M extends Model> {

    void onRequestFailure(String errorMessage);

    void onRequestSuccess(M model);
}
