
package demo.android.moneytap.com.moneytap.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import demo.android.moneytap.com.moneytap.R;
import demo.android.moneytap.com.moneytap.adapter.ListItemsAdapter;
import demo.android.moneytap.com.moneytap.framework.data.remote.CloudDataConnector;
import demo.android.moneytap.com.moneytap.framework.data.remote.ResponseHandler;
import demo.android.moneytap.com.moneytap.framework.response.GetContentData;
import demo.android.moneytap.com.moneytap.interfaces.onSearchListener;
import demo.android.moneytap.com.moneytap.utils.CommonUtils;
import demo.android.moneytap.com.moneytap.utils.Constants;
import demo.android.moneytap.com.moneytap.utils.RecyclerTouchListener;
import demo.android.moneytap.com.moneytap.widgets.MaterialSearchView;

public class MainActivity extends BaseActivity implements onSearchListener, RecyclerTouchListener.ClickListener {

    private RecyclerView recyclerView;

    private TextView lblNoItemsView;

    private ProgressBar progressBarView;

    private LinearLayoutManager mLayoutManager;

    private ListItemsAdapter adapter;

    private ArrayList<GetContentData.Query.Page> data;

    private boolean mSearchViewAdded = false;

    private MaterialSearchView mSearchView;

    private WindowManager mWindowManager;

    private String mSearchQuery = "";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        lblNoItemsView = (TextView) findViewById(R.id.lblNoItemsView);
        progressBarView = (ProgressBar) findViewById(R.id.progressBarView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ListItemsAdapter(this, data);
        recyclerView.setAdapter(adapter);
        //Click Event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, this));

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mSearchView = new MaterialSearchView(this);
        mSearchView.setOnSearchListener(this);
        mSearchView.setHintText("Search...");

        toolbar.setVisibility(View.GONE);
        if (toolbar != null) {
            // Delay adding SearchView until Toolbar has finished loading
            toolbar.post(new Runnable() {
                @Override
                public void run() {
                    if (!mSearchViewAdded && mWindowManager != null) {
                        mWindowManager.addView(mSearchView, MaterialSearchView.getSearchViewLayoutParams(activity));
                        mSearchViewAdded = true;
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSearchView.display();
                openKeyboard();
            }
        }, 400);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
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

    @Override
    public void onSearch( String query ) {
        String test = "";
    }

    @Override
    public void searchViewOpened() {
        String test = "";
    }

    @Override
    public void searchViewClosed() {
        String test = "";
    }

    @Override
    public void onCancelSearch() {
        CommonUtils.hideKeyBoard(context, mSearchView);
        finish();
    }

    @Override
    public void onSubmit( String query ) {
        CommonUtils.hideKeyBoard(context, mSearchView);
        callSearchApi(query);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(context, WebViewContainerActivity.class);
        intent.putExtra(Constants.SEARCH_TEXT, mSearchQuery);
        startActivity(intent);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    private void callSearchApi( final String searchText ) {
        mSearchQuery = searchText;
        if (!CommonUtils.hasInternetConnection(context)) {
            Toast
                    .makeText(getApplicationContext(), "No Connectivity...", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // *******************************************************//
            // Get Rresponse from instance class or singleton class
            progressBarView.setVisibility(View.VISIBLE);
            CloudDataConnector.getInstance().getContentData(
                    searchText,
                    new ResponseHandler<GetContentData>() {
                        @Override
                        public void onRequestFailure( String errorMessage ) {
                            progressBarView.setVisibility(View.GONE);
                            setLblNoItemsText(View.VISIBLE);
                        }

                        @Override
                        public void onRequestSuccess( GetContentData model ) {
                            progressBarView.setVisibility(View.GONE);
                            setData(model.getQuery().getPages());
                        }
                    });
        }
    }

    private void setData( ArrayList<GetContentData.Query.Page> modelBase ) {
        clearList();
        if (modelBase == null || modelBase.isEmpty()) {
            setViewVisibility(View.GONE);
            setLblNoItemsText(View.VISIBLE);
            return;
        }
        setViewVisibility(View.VISIBLE);
        setLblNoItemsText(View.GONE);
        for (GetContentData.Query.Page model : modelBase) {
            data.add(data.size(), model);
            adapter.notifyItemChanged(data.size() - 1);
        }
    }

    private void clearList() {
        if (data.isEmpty()) {
            return;
        }
        data.clear();
        adapter.notifyDataSetChanged();
    }

    private void setViewVisibility( int recyclerVisible ) {
        recyclerView.setVisibility(recyclerVisible);
    }

    private void setLblNoItemsText( int visiblity ) {
        lblNoItemsView.setVisibility(visiblity);
        lblNoItemsView.setText("Oop's, No data found for search.");
    }

    private void openKeyboard() {
        mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(
                SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(),
                MotionEvent.ACTION_DOWN,
                0,
                0,
                0));
        mSearchView.getSearchView().dispatchTouchEvent(MotionEvent.obtain(
                SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(),
                MotionEvent.ACTION_UP,
                0,
                0,
                0));
    }
}
