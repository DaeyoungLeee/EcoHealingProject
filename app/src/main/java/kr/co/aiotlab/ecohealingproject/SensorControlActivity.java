package kr.co.aiotlab.ecohealingproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class SensorControlActivity extends AppCompatActivity {

    private Button checkButton;
    static String txt_port;
    static String txt_ip;
    private WebView webView_sensor;
    private String cctv_IP;
    // 뷰페이저
    private ViewPager viewPager;
    private PagerAdapter viewTextAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sensorcontrol);

        SharedPreferences ip = getSharedPreferences("IP_files", MODE_PRIVATE);
        SharedPreferences port = getSharedPreferences("PORT_files", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("CCTV", Context.MODE_PRIVATE);
        cctv_IP = sharedPreferences.getString("CCTVIP", "0");
        txt_ip = ip.getString("IP", "0");
        txt_port = port.getString("PORT", "0");

        webView_sensor = findViewById(R.id.webview_sensor);
        WebSettings set = webView_sensor.getSettings();
        set.setJavaScriptEnabled(true);
        webView_sensor.loadUrl("http://" + cctv_IP);

        // ViewPager
        List<Fragment> list = new ArrayList<>();
        list.add(new SensorControl1_Fragment());
        list.add(new SensorControl2_Fragment());
        list.add(new SensorControl3_Fragment());
        // list.add(new SensorControl4_Fragment());

        String[] tabsTitle = new String[] {"조명", "방범", "콘센트"};

        viewPager = findViewById(R.id.viewPager_sensorControl);

        viewTextAdapter = new TextAdapter(getSupportFragmentManager(), list, tabsTitle);

        viewPager.setAdapter(viewTextAdapter);
        final PagerSlidingTabStrip tabsStrip = findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

    }


    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onStop() {
        super.onStop();



    }



}
