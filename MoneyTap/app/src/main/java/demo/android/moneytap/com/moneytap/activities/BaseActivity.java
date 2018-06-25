
package demo.android.moneytap.com.moneytap.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import demo.android.moneytap.com.moneytap.R;

/**
 * Created by mujeeb on 24-Jun-18.
 */
public class BaseActivity extends AppCompatActivity {

    protected ViewGroup fullView;

    protected Toolbar toolbar;

    protected FrameLayout frameLayout;

    protected Activity activity;

    protected Context context;

    protected String TAG = "Base Empty";

    @Override
    public void setContentView( int layoutResID ) {
        fullView = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        frameLayout = (FrameLayout) fullView.findViewById(R.id.activity_content_base);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(fullView);
        context = activity = this;
        toolbar = (Toolbar) findViewById(R.id.toolbarBase);
        setSupportActionBar(toolbar);
    }

    public void setToolbarTitle( String title ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setToolbarTitle( int title ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setToolbarSubtTitle( String title ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(title);
        }
    }

    public void setToolbarSubtTitle( int title ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(title);
        }
    }

    public void hideToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void setBackNavigationIcon( Drawable drawable ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setIcon(drawable);
        }
    }

    public void setBackNavigationIcon( int icon ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setIcon(icon);
        }
    }

    public void setDisplayHomeAsUpEnabled( boolean enable ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        }
    }

    public void setHomeButtonEnabled( boolean enable ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(enable);
        }
    }

    public void setDefaultDisplayHomeAsUpEnabled( boolean enable ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(enable);
        }
    }

    public void setDisplayShowTitleEnabled( boolean enable ) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(enable);
        }
    }
}
