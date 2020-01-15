package kr.co.aiotlab.ecohealingproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static kr.co.aiotlab.ecohealingproject.SensorControlActivity.txt_ip;

public class SensorControl1_Fragment extends Fragment {

    View view;

    // 제어 스위치
    private Switch led_switch1, led_switch2, led_switch3, led_switch4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sensor_control_1, container, false);

        led_switch1 = view.findViewById(R.id.LEDswitch1);
        led_switch2 = view.findViewById(R.id.LEDswitch2);
        led_switch3 = view.findViewById(R.id.LEDswitch3);
        led_switch4 = view.findViewById(R.id.LEDswitch4);

        //switch1 이 ON됐을 때와 OFF됐을 때 동작
        led_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (led_switch1.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("1");
                    }
                } else if (!led_switch1.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("0");
                    }
                }
            }
        });
        //switch2 이 ON됐을 때와 OFF됐을 때 동작
        led_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (led_switch2.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("5");
                    }
                } else if (!led_switch2.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("4");
                    }
                }
            }
        });
        //switch3 이 ON됐을 때와 OFF됐을 때 동작
        led_switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (led_switch3.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        //명령 추가
                    }
                } else if (!led_switch3.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        //명령 추가
                    }
                }
            }
        });
        //switch4 이 ON됐을 때와 OFF됐을 때 동작
        led_switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (led_switch4.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        //명령 추가
                    }
                } else if (!led_switch4.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        //명령 추가
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
