package kr.co.aiotlab.ecohealingproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static kr.co.aiotlab.ecohealingproject.SensorControlActivity.txt_ip;

public class SensorControl2_Fragment extends Fragment {

    View view;

    private Switch siren_switch1, siren_switch2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sensor_control_2, container, false);

        siren_switch1 = view.findViewById(R.id.Sirenswitch1);
        siren_switch2 = view.findViewById(R.id.Sirenswitch2);

        //싸이렌 버튼
        siren_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (siren_switch1.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("7");
                    }
                } else if (!siren_switch1.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("6");
                    }
                }
            }
        });

        siren_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (siren_switch2.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("3");
                    }
                } else if (!siren_switch2.isChecked()) {
                    if (txt_ip.equals("")) {
                        Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        new SocketProtocol().execute("2");
                    }
                }
            }
        });

        return view;
    }
}
