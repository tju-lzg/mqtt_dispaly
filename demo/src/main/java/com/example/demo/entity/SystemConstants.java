package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemConstants {
    public static  String Host_URL;
    public static String Port;
    public static String CLIENT_ID;
    public static String USERNAME;
    public static String PASSWORD;
    public  SystemConstants(String a,String b,String c,String d,String e){
        Host_URL=a;
        Port=b;
        CLIENT_ID=c;
        USERNAME=d;
        PASSWORD=e;
    }
}
