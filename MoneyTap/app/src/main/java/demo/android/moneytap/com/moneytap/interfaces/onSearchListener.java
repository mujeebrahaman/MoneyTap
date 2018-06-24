package demo.android.moneytap.com.moneytap.interfaces;

/**
 * Created by mujeeb on 24-Jun-18.
 */

public interface onSearchListener {
    void onSearch(String query);
    void searchViewOpened();
    void searchViewClosed();
    void onCancelSearch();
    void onSubmit(String query);
}
