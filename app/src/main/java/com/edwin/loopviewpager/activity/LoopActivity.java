package com.edwin.loopviewpager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edwin.loopviewpager.R;
import com.edwin.loopviewpager.base.BaseActivity;
import com.edwin.loopviewpager.fragment.DepthLoopViewPagerFragment;
import com.edwin.loopviewpager.fragment.EmptyLoopViewPagerFragment;
import com.edwin.loopviewpager.fragment.ListHeadViewFragment;
import com.edwin.loopviewpager.fragment.ZoomLoopViewPagerFragment;

import java.io.File;

/**
 * LoopActivity
 *
 * @author Edwin.Wu
 * @version 2016/11/8 00:15
 * @since JDK1.8
 */
public class LoopActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_loop_info;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_loop);
    }

    @Override
    protected void setUpView() {
        findViewById(R.id.button_onclick_empty).setOnClickListener(this);
        findViewById(R.id.button_onclick_depth).setOnClickListener(this);
        findViewById(R.id.button_onclick_zoom).setOnClickListener(this);
        findViewById(R.id.button_onclick_list).setOnClickListener(this);
        findViewById(R.id.button_news).setOnClickListener(this);
        findViewById(R.id.button_open).setOnClickListener(this);
        text_loop_info = (TextView) findViewById(R.id.text_loop_info);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        LoopViewPagerToEmpty();
    }

    private void LoopViewPagerToEmpty() {
        EmptyLoopViewPagerFragment instance = EmptyLoopViewPagerFragment.getInstance();

        replace(instance);

        Toast.makeText(mContext, "replace  Empty successful", Toast.LENGTH_SHORT).show();
        text_loop_info.setText("This is Empty style \n  Default Loader");
    }

    private void LoopViewPagerToDepth() {
        DepthLoopViewPagerFragment instance = DepthLoopViewPagerFragment.getInstance();

        replace(instance);

        Toast.makeText(mContext, "replace  Depth successful", Toast.LENGTH_SHORT).show();
        text_loop_info.setText("This is Depth style \n  Picasso Loader");
    }

    private void LoopViewPagerToZoom() {
        ZoomLoopViewPagerFragment instance = ZoomLoopViewPagerFragment.getInstance();

        replace(instance);

        Toast.makeText(mContext, "replace  Zoom successful", Toast.LENGTH_SHORT).show();
        text_loop_info.setText("This is Zoom style \n  Fresco Loader");
    }

    private void LoopViewPagerToListView() {
        ListHeadViewFragment instance = ListHeadViewFragment.getInstance();

        replace(instance);

        Toast.makeText(mContext, "replace ListView add HeadView", Toast.LENGTH_SHORT).show();
        text_loop_info.setText("replace ListView add HeadView \n  Glide Fresco");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_onclick_empty:
            LoopViewPagerToEmpty();
            break;
        case R.id.button_onclick_depth:
            LoopViewPagerToDepth();
            break;
        case R.id.button_onclick_zoom:
            LoopViewPagerToZoom();
            break;
        case R.id.button_onclick_list:
            LoopViewPagerToListView();
        case R.id.button_news:
            startActivity(new Intent(LoopActivity.this, NewDetailActivity.class));
            break;
        case R.id.button_open:
            File file = new File(Environment.getExternalStorageDirectory(), "123.pdf");
            openFile(file);
            break;
        }
    }

    private void replace(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 打开文件
     * 
     * @param file
     */
    private void openFile(File file) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        // 获取文件file的MIME类型
        String type = getMIMEType(file);
        // 设置intent的data和Type属性。
        intent.setDataAndType(/* uri */Uri.fromFile(file), type);
        // 跳转
        startActivity(intent); // 这里最好try一下，有可能会报错。
                               // //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。

    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     * 
     * @param file
     */
    private String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "")
            return type;
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private final String[][] MIME_MapTable = {
            // {后缀名，MIME类型}
            { ".3gp", "video/3gpp" }, { ".apk", "application/vnd.android.package-archive" },
            { ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" }, { ".bin", "application/octet-stream" },
            { ".bmp", "image/bmp" }, { ".c", "text/plain" }, { ".class", "application/octet-stream" },
            { ".conf", "text/plain" }, { ".cpp", "text/plain" }, { ".doc", "application/msword" },
            { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
            { ".xls", "application/vnd.ms-excel" },
            { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
            { ".exe", "application/octet-stream" }, { ".gif", "image/gif" }, { ".gtar", "application/x-gtar" },
            { ".gz", "application/x-gzip" }, { ".h", "text/plain" }, { ".htm", "text/html" }, { ".html", "text/html" },
            { ".jar", "application/java-archive" }, { ".java", "text/plain" }, { ".jpeg", "image/jpeg" },
            { ".jpg", "image/jpeg" }, { ".js", "application/x-JavaScript" }, { ".log", "text/plain" },
            { ".m3u", "audio/x-mpegurl" }, { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" },
            { ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" },
            { ".mov", "video/quicktime" }, { ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" },
            { ".mp4", "video/mp4" }, { ".mpc", "application/vnd.mpohun.certificate" }, { ".mpe", "video/mpeg" },
            { ".mpeg", "video/mpeg" }, { ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" }, { ".mpga", "audio/mpeg" },
            { ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" }, { ".pdf", "application/pdf" },
            { ".png", "image/png" }, { ".pps", "application/vnd.ms-powerpoint" },
            { ".ppt", "application/vnd.ms-powerpoint" },
            { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
            { ".prop", "text/plain" }, { ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" },
            { ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" },
            { ".tgz", "application/x-compressed" }, { ".txt", "text/plain" }, { ".wav", "audio/x-wav" },
            { ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" }, { ".wps", "application/vnd.ms-works" },
            { ".xml", "text/plain" }, { ".z", "application/x-compress" }, { ".zip", "application/x-zip-compressed" },
            { "", "*/*" } };

}
