package com.example.xml_parser;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xml_parser.bean.App;
import com.example.xml_parser.utils.MyXMLPullUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String xmlString = "<apps>\n" +
            "    <app>\n" +
            "\t    <id>1</id>\n" +
            "\t\t<name>google map</name>\n" +
            "\t\t<version>1.0</version>\n" +
            "\t</app>\n" +
            "\t\n" +
            "\t <app>\n" +
            "\t    <id>2</id>\n" +
            "\t\t<name>google play</name>\n" +
            "\t\t<version>2.0</version>\n" +
            "\t</app>\n" +
            "\t\n" +
            "\t<app>\n" +
            "\t    <id>3</id>\n" +
            "\t\t<name>chrome</name>\n" +
            "\t\t<version>3.0</version>\n" +
            "\t</app>\n" +
            "\t\n" +
            "</apps>";
    private String TAG = "AppCompatActivity";
    OutputStream outputStream;
    List<App> all = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 3; i++) {
            App app = new App();
            app.setId(i);
            app.setName("app" + i);
            app.setVersion(i);
            all.add(app);
        }


        parseXMLWithPull(xmlString,"com.example.xml_parser.bean.App");

        try {
            String baseUrl  = Environment.getExternalStorageDirectory().toString();
            String path = baseUrl + File.separator + "apps.xml";
            File file = new File( path);
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //MyXMLPullUtils(OutputStream output, List<App> all)
      /*  MyXMLPullUtils myXMLPullUtils = new MyXMLPullUtils(outputStream, all);
        try {
            myXMLPullUtils.save();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }



    private List<StringBuilder>  parseXMLWithPull(String xmlString,String xmlBean) {
        List<StringBuilder> result = new ArrayList<>();

        List<String> xmlAttribute = new ArrayList<>();
        try {
            Class<?> c = Class.forName(xmlBean);
            Field[] declaredFields = c.getDeclaredFields();//获取类的所有属性
            for (int i = 0; i < declaredFields.length; i++) {
                String name = declaredFields[i].getName();
                xmlAttribute.add(name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//创建解析工厂
            XmlPullParser xmlPullParser = factory.newPullParser();//获取单个的解析类

            StringReader stringReader = new StringReader(xmlString);
            xmlPullParser.setInput(stringReader);//设置要解析的XML的数据源
            int eventType = xmlPullParser.getEventType();//获取事件类型
            /*String id = "";
            String name = "";
            String version = "";*/

            int size = xmlAttribute.size();
            while (eventType != XmlPullParser.END_DOCUMENT) {//如果没有到达文档的结束位置 ，就一直循环遍历
                String nodeName = xmlPullParser.getName();//获取每个节点的名称
                switch (eventType) {

                    case XmlPullParser.START_TAG:
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < size; i++) {
                            if (xmlAttribute.get(i).equals(nodeName)) {
                                String s = xmlPullParser.nextText();

                                stringBuilder.append(s);

                            }

                        }
                        result.add(stringBuilder);


                        break;
                    case XmlPullParser.END_TAG:

                        break;
                   /* case XmlPullParser.START_TAG: {//起始标签
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{//结束标签
                        if ("app".equals(nodeName)) {
                            Log.i(TAG, "id = " + id);
                            Log.i(TAG, "name = " + name);
                            Log.i(TAG, "version = " + version);

                        }



                        break;
                    }*/
                    default:
                        break;

                }

                eventType= xmlPullParser.next();//表示把游标下移动


            }
//            Log.i("xmlAttribute", stringBuilder.toString());
            Log.i("xmlAttribute", "结果是：" + result );



        } catch (Exception e) {
            e.printStackTrace();
        }
         return result;
    }
}
