package kr.co.aiotlab.ecohealingproject.Service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import kr.co.aiotlab.ecohealingproject.Main_User_Interface.MainActivity;
import kr.co.aiotlab.ecohealingproject.R;

import static kr.co.aiotlab.ecohealingproject.App.CHANNEL_ID;

public class AppService_push extends Service {
    public static final String TAG = "Service_push";
    int set_temp;
    public static final String CHANNEL_FIRE = "push_fire_channel";

    JSONObject jsonObject, jsonObject_fire;
    int dust;
    String temp, humi;
    String jsonMQTT, jsonMQTT_fire;
    String clientId = MqttClient.generateClientId();
    Notification notification_fire;
    Notification notification;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences set_temp_pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        set_temp = Integer.parseInt(set_temp_pref.getString("SETTEMP", "0"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
// Service 실행중이면 상태표시바에 어떻게 보일지 표시해주는 세팅
                        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, notificationIntent, 0);

                        notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setContentTitle("푸시알람이 실행중입니다.")
                                .setContentIntent(pendingIntent)
                                .setSmallIcon(R.drawable.aiotlablogo)
                                .build();
                        startForeground(1, notification);

                        //불꽃감지 상태
                        notification_fire = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setContentTitle("불꽃이 감지되었습니다!")
                                .setContentIntent(pendingIntent)
                                .setSmallIcon(R.drawable.fire)
                                .build();

                        String clientId_fire = MqttClient.generateClientId();
                        final MqttAndroidClient client_fire =
                                new MqttAndroidClient(getApplicationContext(), "tcp://222.113.57.108:1883",
                                        clientId_fire);
                        //mqtt 연결 옵션
                        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                        mqttConnectOptions.setAutomaticReconnect(true);
                        mqttConnectOptions.setKeepAliveInterval(10);
                        try {
                            final IMqttToken token = client_fire.connect(mqttConnectOptions);

                            token.setActionCallback(new IMqttActionListener() {
                                @Override
                                public void onSuccess(IMqttToken asyncActionToken) {
                                    // We are connected
                                    Log.d(TAG, "onSuccess: ");

                                    String topic = "Sensor/Fire_Motion";

                                    int qos = 1;
                                    try {
                                        IMqttToken subToken = client_fire.subscribe(topic, qos);
                                        subToken.setActionCallback(new IMqttActionListener() {
                                            @Override
                                            public void onSuccess(IMqttToken asyncActionToken) {
                                                // The message was published
                                                Log.d(TAG, "onSuccess: ");
                                                client_fire.setCallback(new MqttCallback() {
                                                    @Override
                                                    public void connectionLost(Throwable cause) {
                                                        Log.d(TAG, "connectionLost:Fire" + String.valueOf(cause));

                                                    }

                                                    @Override
                                                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                                                        //Json 파싱
                                                        jsonMQTT_fire = new String(message.getPayload());
                                                    }

                                                    @Override
                                                    public void deliveryComplete(IMqttDeliveryToken token) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onFailure(IMqttToken asyncActionToken,
                                                                  Throwable exception) {
                                                // The subscription could not be performed, maybe the user was not
                                                // authorized to subscribe on the specified topic e.g. using wildcards
                                                Log.d(TAG, "onFailure: ");
                                            }
                                        });
                                    } catch (MqttException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                    // Something went wrong e.g. connection timeout or firewall problems
                                    Log.d(TAG, "onFailure: ");

                                }
                            });
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }


                        // MQTT set (Temperature)
                        final Notification notification_temp = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setContentTitle("고온 알림. 온도가 " + set_temp + "도를 초과했습니다.")
                                .setContentIntent(pendingIntent)
                                .setSmallIcon(R.drawable.aiotlablogo)
                                .build();


                        final MqttAndroidClient client =
                                new MqttAndroidClient(getApplicationContext(), "tcp://222.113.57.108:1883",
                                        clientId);
                        try {

                            final IMqttToken token = client.connect(mqttConnectOptions);
                            token.setActionCallback(new IMqttActionListener() {
                                @Override
                                public void onSuccess(IMqttToken asyncActionToken) {
                                    // We are connected

                                    int qos = 1;
                                    try {
                                        IMqttToken subToken = client.subscribe("Sensor/Dust_DHT22", qos);
                                        subToken.setActionCallback(new IMqttActionListener() {
                                            @Override
                                            public void onSuccess(IMqttToken asyncActionToken) {
                                                // The message was published
                                            }

                                            @Override
                                            public void onFailure(IMqttToken asyncActionToken,
                                                                  Throwable exception) {
                                                // The subscription could not be performed, maybe the user was not
                                                // authorized to subscribe on the specified topic e.g. using wildcards

                                            }
                                        });
                                    } catch (MqttException e) {
                                        e.printStackTrace();
                                    }
                                    client.setCallback(new MqttCallback() {
                                        @Override
                                        public void connectionLost(Throwable cause) {
                                            Log.d(TAG, "connectionLost:Temperature");
                                        }

                                        @Override
                                        public void messageArrived(String topic, MqttMessage message) throws Exception {
                                            Log.d(TAG, "MQTT Service messageArrived");
                                            //데이터 가져와서 비교
                                            final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                            final Boolean push_temp_state = sharedPreferences.getBoolean("push_temperature", false);

                                            if (push_temp_state == true) {
                                                //Json 파싱
                                                jsonMQTT = new String(message.getPayload());
                                                jsonParse();
                                                if (Float.parseFloat(temp) > set_temp) {
                                                    startForeground(2, notification_temp);
                                                }
                                            } else {
                                                Log.d(TAG, "clearPayload");
                                                message.clearPayload();

                                            }
                                        }

                                        @Override
                                        public void deliveryComplete(IMqttDeliveryToken token) {

                                        }
                                    });

                                }

                                @Override
                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                    // Something went wrong e.g. connection timeout or firewall problems
                                    // MQTT 수신 실패시 토스트 메시지 띄우기
                                    new Thread() {
                                        public void run() {

                                        }
                                    }.start();
                                }
                            });


                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }).start();


        return START_STICKY;
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "MQTT 수신 실패", Toast.LENGTH_SHORT).show();
        }
    };

    private void jsonParse() {
        try {
            jsonObject = new JSONObject(jsonMQTT);
            temp = jsonObject.getString("temperature");
            int windchilltemp = jsonObject.getInt("windchill");
            humi = jsonObject.getString("humidity");
            dust = jsonObject.getInt("dust");

            Log.d(TAG, "jsonParse: temp = " + temp);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
