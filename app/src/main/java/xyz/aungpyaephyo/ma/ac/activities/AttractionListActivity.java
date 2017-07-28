package xyz.aungpyaephyo.ma.ac.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.ma.ac.R;
import xyz.aungpyaephyo.ma.ac.adapters.AttractionAdapter;
import xyz.aungpyaephyo.ma.ac.data.models.AttractionsModel;
import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;
import xyz.aungpyaephyo.ma.ac.events.DataEvent;
import xyz.aungpyaephyo.ma.ac.viewholders.AttractionViewHolder;

public class AttractionListActivity extends BaseActivity
        implements AttractionViewHolder.ControllerAttractionItem {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_attractions)
    RecyclerView rvAttractions;

    private AttractionAdapter mAttractionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_title_mm));
        }

        mAttractionAdapter = new AttractionAdapter(getApplicationContext(), this);
        rvAttractions.setAdapter(mAttractionAdapter);

        LinearLayoutManager verticalSingleColumnLM = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvAttractions.setLayoutManager(verticalSingleColumnLM);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attraction_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapAttraction(AttractionVO attraction, ImageView ivAttraction) {

    }

    @OnClick(R.id.fab)
    public void onTapSearchFab(View view) {
        Snackbar.make(view, "Search will come later", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAttractionsLoadedEvent(DataEvent.AttractionsLoadedEvent event) {
        mAttractionAdapter.setNewData(event.getAttractionList());
    }
}
