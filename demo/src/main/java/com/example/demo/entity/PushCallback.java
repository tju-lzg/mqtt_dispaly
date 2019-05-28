package com.example.demo.entity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PushCallback implements MqttCallback {

    @Autowired
    private MqttPushClient mqttPushClient;
    SystemConstants SystemConstants=new SystemConstants();

//    @Autowired
//    private DeviceDao deviceDao;

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");

        mqttPushClient.connect(SystemConstants.Host_URL + ":" + SystemConstants.Port, "consumer" + SystemConstants.CLIENT_ID, SystemConstants.USERNAME, SystemConstants.PASSWORD);
        mqttPushClient.subscribe("lzgxrs");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload()));
    }
}
