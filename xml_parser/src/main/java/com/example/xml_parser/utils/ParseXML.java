package com.example.xml_parser.utils;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ParseXML {

    //第一个参数表示要解析的XML字符串，第二个参数表示要反射的XML字符串对应的那个Java对象的 包名称+类名称 的全路径
    public static List<StringBuilder> parseXMLWithPull(String xmlString, String xmlBean) {
        //最终解析的结果
        List<StringBuilder> result = new ArrayList<>();

        //XML中的属性
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
