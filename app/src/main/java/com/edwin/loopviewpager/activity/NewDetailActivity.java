package com.edwin.loopviewpager.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edwin.loopviewpager.R;
import com.edwin.loopviewpager.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager的新闻详情
 */
public class NewDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ViewPager mVp;

    private RelativeLayout activity_new_detail;

    private List<String> mList;

    private NewsAdapter mAdapter;

    private TextView pageNm;

    private TextView pageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toobar);
        mVp = (ViewPager) findViewById(R.id.mVp);
        activity_new_detail = (RelativeLayout) findViewById(R.id.activity_new_detail);
        pageNm = (TextView) findViewById(R.id.pageNm);
        pageInfo = (TextView) findViewById(R.id.pageInfo);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("外交部");

        mList = new ArrayList<>();
        mList.add("http://mm.howkuai.com/wp-content/uploads/2016a/09/01/01.jpg");
        mList.add("http://mm.howkuai.com/wp-content/uploads/2016a/08/02/03.jpg");
        mList.add("http://mm.howkuai.com/wp-content/uploads/2016a/08/31/01.jpg");
        mList.add("http://mm.howkuai.com/wp-content/uploads/2016a/08/28/01.jpg");
        mList.add("http://mm.howkuai.com/wp-content/uploads/2016a/08/02/01.jpg");

        pageNm.setText("1/" + mList.size());

        mAdapter = new NewsAdapter(this, mList);
        mVp.setAdapter(mAdapter);

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageNm.setText((position + 1)+ "/" + mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
