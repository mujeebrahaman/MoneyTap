package demo.android.moneytap.com.moneytap.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import demo.android.moneytap.com.moneytap.R;
import demo.android.moneytap.com.moneytap.utils.Constants;

public class WebViewContainerActivity extends BaseActivity {

    private WebView webView;

    private String mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_container);

        initBundleValues();
        if (TextUtils.isEmpty(mSearchText)) {
            finish();
        }
        setToolbarTitle(mSearchText);
        setDisplayHomeAsUpEnabled(true);
        setHomeButtonEnabled(true);
        setDefaultDisplayHomeAsUpEnabled(true);
        setDisplayShowTitleEnabled(true);

        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initViews() {
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        loadPdf();
    }

    private void loadPdf() {
        webView.loadUrl("https://en.wikipedia.org/wiki/" + mSearchText);
    }

    private void initBundleValues() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            mSearchText = getIntent().getExtras().getString(Constants.SEARCH_TEXT);
        }
    }
}
