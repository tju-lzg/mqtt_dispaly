package com.example.demo;

import com.example.demo.entity.MqttPushClient;
import com.example.demo.entity.ParamConfig;
import com.example.demo.entity.SerialPortUtils;
import com.example.demo.entity.SystemConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
@MapperScan({"com.example.demo.dao"})
public class DemoApplication {
	MqttPushClient mqttPushClient=new MqttPushClient();
	SystemConstants SystemConstants=new SystemConstants("tcp://m2m.eclipse.org","1883","e084cb6b098b4f6796763baa7fdc70bc","lizhigang","123456");
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	SerialPortUtils serialPort = new SerialPortUtils();
	// 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
	ParamConfig paramConfigure = new ParamConfig("COM4", 9600, 0, 8, 1);
	// 初始化设置,打开串口，开始监听读取串口数据
		try {
			serialPort.init(paramConfigure);
			// 调用串口操作类的sendComm方法发送数据到串口
			serialPort.sendComm("page main");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			String str=br.readLine();
			while(str!=null){
				serialPort.sendComm(str);
				str=br.readLine();
			}
			// 关闭串口
//			serialPort.closeSerialPort();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	@PostConstruct
	public void consumeMqttClient() {

		mqttPushClient.connect(SystemConstants.Host_URL + ":" + SystemConstants.Port, "consumer" + SystemConstants.CLIENT_ID, SystemConstants.USERNAME, SystemConstants.PASSWORD);
		mqttPushClient.subscribe("lzgxrs",1);
		mqttPushClient.publish("lzgxrs","helloworld");
	}
}
