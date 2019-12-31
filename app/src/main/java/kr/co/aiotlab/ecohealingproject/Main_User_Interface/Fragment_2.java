package kr.co.aiotlab.ecohealingproject.Main_User_Interface;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kr.co.aiotlab.ecohealingproject.R;
import kr.co.aiotlab.ecohealingproject.Main_User_Interface.Fragment2_UI.SecondFragmentBrightness;
import kr.co.aiotlab.ecohealingproject.Main_User_Interface.Fragment2_UI.SecondFragmentCO2;
import kr.co.aiotlab.ecohealingproject.Main_User_Interface.Fragment2_UI.SecondFragmentDust;
import kr.co.aiotlab.ecohealingproject.Main_User_Interface.Fragment2_UI.SecondFragmentHumidity;
import kr.co.aiotlab.ecohealingproject.Main_User_Interface.Fragment2_UI.SecondFragmentThermo;

public class Fragment_2 extends Fragment implements View.OnClickListener {

    private CardView btn_thermo, btn_humidity, btn_brightness, btn_dust;
    private TextView text_setTemperature, text_setDust, text_setHumidity, text_setbrightness, txt_nowtemp, txt_nowhumidity, txt_nowbrightness;
    private SwipeRefreshLayout swipe_frag2;
    private FirebaseDatabase mDatabase;
    private FrameLayout frameLayout2;

    public static Fragment_2 newInstance() {
        Fragment_2 f = new Fragment_2();
        return f;
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag2, container, false);

        btn_brightness = view.findViewById(R.id.btn_brightness);
        btn_dust = view.findViewById(R.id.btn_dust);
        btn_thermo = view.findViewById(R.id.btn_thermo);
        btn_humidity = view.findViewById(R.id.btn_humidity);
        text_setTemperature = view.findViewById(R.id.text_setTemperature);
        text_setDust = view.findViewById(R.id.text_setDust);
        text_setHumidity = view.findViewById(R.id.text_setHumidity);
        text_setbrightness = view.findViewById(R.id.text_setBrightness);
        swipe_frag2 = view.findViewById(R.id.swipe_frag2);
        txt_nowbrightness = view.findViewById(R.id.txt_nowbrightness);
        txt_nowhumidity = view.findViewById(R.id.txt_nowhumidity);
        txt_nowtemp = view.findViewById(R.id.txt_nowtemp);
        frameLayout2 = view.findViewById(R.id.frame_frag2);

        btn_thermo.setOnClickListener(this);
        btn_humidity.setOnClickListener(this);
        btn_dust.setOnClickListener(this);
        btn_brightness.setOnClickListener(this);

        // 설정 값 불러와서 반영
        SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String settemp = sharedPreferences2.getString("SETTEMP", "-");
        String sethumid = sharedPreferences2.getString("SETHUMID", "-");
        String setbright = sharedPreferences2.getString("SETBRIGHT", "-");
        String setdust = sharedPreferences2.getString("SETDUST", "-");
        String setco2 = sharedPreferences2.getString("SETCO2", "-");

        text_setTemperature.setText("21.6");
        text_setbrightness.setText("544");
        text_setDust.setText("78");
        text_setHumidity.setText("35.9");

        /** 온습도 실시간 가져오기 */
        mDatabase = FirebaseDatabase.getInstance();

        DatabaseReference humidRef = mDatabase.getReference("DHT11_Data").child("Humid").child("Humidity");
        DatabaseReference tempRef = mDatabase.getReference("DHT11_Data").child("Temp").child("Temperature");
        DatabaseReference cdsRef = mDatabase.getReference("CDS_Data").child("CDS").child("LUX");


        // Read from the database (humidity)
        humidRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txt_nowhumidity.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        // Read from the database (temperature)
        tempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txt_nowtemp.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        // Read from the database (CDS)
        cdsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txt_nowbrightness.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        //swipe refresh할 때 동작
        swipe_frag2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                showFragment(Fragment_2.newInstance());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_frag2.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        return view;
    }

    /**
     * 각각의 카드뷰가 클릭됐을 경우 동작
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thermo:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentThermo()).commit();
                frameLayout2.setBackgroundColor(Color.WHITE);
                break;
            case R.id.btn_humidity:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentHumidity()).commit();
                frameLayout2.setBackgroundColor(Color.WHITE);
                break;
            case R.id.btn_dust:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentDust()).commit();
                frameLayout2.setBackgroundColor(Color.WHITE);
                break;
            case R.id.btn_brightness:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentBrightness()).commit();
                frameLayout2.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    public void showFragment(Fragment f) {
        getFragmentManager().beginTransaction().replace(R.id.frame_main, f).commit();
    }
}
