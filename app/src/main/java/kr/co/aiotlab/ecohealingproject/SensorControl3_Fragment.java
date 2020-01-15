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

public class SensorControl3_Fragment extends Fragment {

    View view;

    private Switch power_switch1, power_switch2, power_switch3, power_switch4, power_switch5, power_switch6, power_switch7;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sensor_control_3, container, false);

        power_switch1 = view.findViewById(R.id.power_switch1);
        power_switch2 = view.findViewById(R.id.power_switch2);
        power_switch3 = view.findViewById(R.id.power_switch3);
        power_switch4 = view.findViewById(R.id.power_switch4);
        power_switch5 = view.findViewById(R.id.power_switch5);
        power_switch6 = view.findViewById(R.id.power_switch6);
        power_switch7 = view.findViewById(R.id.power_switch7);


        // 콘센트 제어 스위치
        power_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                power_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (power_switch1.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("11");
                            }
                        } else if (!power_switch1.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("00");
                            }
                        }
                    }
                });
            }
        });

        power_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                power_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (power_switch2.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("AA");
                            }
                        } else if (!power_switch2.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("aa");
                            }
                        }
                    }
                });
            }
        });

        power_switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                power_switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (power_switch3.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("BB");
                            }
                        } else if (!power_switch3.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("bb");
                            }
                        }
                    }
                });
            }
        });

        power_switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                power_switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (power_switch4.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("CC");
                            }
                        } else if (!power_switch4.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("cc");
                            }
                        }
                    }
                });
            }
        });

        power_switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                power_switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (power_switch5.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("DD");
                            }
                        } else if (!power_switch5.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("dd");
                            }
                        }
                    }
                });
            }
        });

        power_switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                power_switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (power_switch6.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("EE");
                            }
                        } else if (!power_switch6.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("ee");
                            }
                        }
                    }
                });
            }
        });

        power_switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                power_switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (power_switch7.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("FF");
                            }
                        } else if (!power_switch7.isChecked()) {
                            if (txt_ip.equals("")) {
                                Toast.makeText(getContext(), "IP주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                new SocketProtocol().execute("ff");
                            }
                        }
                    }
                });
            }
        });

        return view;
    }
}
