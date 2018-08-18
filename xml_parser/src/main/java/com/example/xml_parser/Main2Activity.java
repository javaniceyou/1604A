package com.example.xml_parser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xml_parser.bean.AdminUser;
import com.example.xml_parser.bean.ConfigUsers;
import com.example.xml_parser.bean.apps;
import com.thoughtworks.xstream.XStream;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    String str = "<ConfigUser>\n" +
            "    <type>ADMIN</type>\n" +
            "    <users>\n" +
            "        <AdminUser>\n" +
            "            <name>zhaoyb</name>\n" +
            "            <pwd>abc123</pwd>\n" +
            "            <ip>\n" +
            "                <string>127.0.0.1</string>\n" +
            "                <string>203.119.80.128</string>\n" +
            "            </ip>\n" +
            "        </AdminUser>\n" +
            "        <AdminUser>\n" +
            "            <name>liangqunxing</name>\n" +
            "            <pwd>abc123</pwd>\n" +
            "            <ip>\n" +
            "                <string>127.0.0.1</string>\n" +
            "                <string>202.173.100.126</string>\n" +
            "            </ip>\n" +
            "        </AdminUser>\n" +
            "        <AdminUser>\n" +
            "            <name>liuyu</name>\n" +
            "            <pwd>abc123</pwd>\n" +
            "            <ip>\n" +
            "                <string>127.0.0.1</string>\n" +
            "                <string>203.119.80.108</string>\n" +
            "            </ip>\n" +
            "        </AdminUser>\n" +
            "    </users>\n" +
            "</ConfigUser>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


//        InputStream xmlInputStream = new ClassPathResource("admin.xml").getInputStream();
            XStream xStream = new XStream();
            xStream.alias("AdminUser", AdminUser.class);
            xStream.alias("ConfigUser", ConfigUsers.class);
            ConfigUsers users = (ConfigUsers) xStream.fromXML(str);
            List<AdminUser> adminUsers = users.getUsers();
            for (AdminUser adminUser : adminUsers) {

            Log.i("XStream", "解析的内容是：" + adminUser.getName() + "," + adminUser.getPwd());
        }

    }
}
