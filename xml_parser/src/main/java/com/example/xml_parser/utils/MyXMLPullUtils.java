package com.example.xml_parser.utils;

import com.example.xml_parser.bean.App;

import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class MyXMLPullUtils {

    OutputStream output = null;
    private List<App> all = null;

    public MyXMLPullUtils(OutputStream output, List<App> all) {
        this.output = output;
        this.all = all;
    }

    public void save() throws Exception {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//创建工厂
        XmlSerializer xs = factory.newSerializer();
        xs.setOutput(this.output, "UTF-8");//使用输出流将序列化的XML输出到指定位置
        xs.startDocument("UTF-8",true);  //开启文档
        xs.startTag(null,"apps");//开始标签
        Iterator<App> iterator = this.all.iterator();
        while (iterator.hasNext()) {
            App app = iterator.next();
            xs.startTag(null, "app");

            xs.startTag(null, "id");//开始标签
            xs.text(app.getId()+"");//设置标签的内容的值
            xs.endTag(null, "id");//结束标签

            xs.startTag(null, "name");
             xs.text(app.getName());
            xs.endTag(null, "name");

            xs.startTag(null, "version");
            xs.text(app.getVersion() + "");
            xs.endTag(null, "version");

            xs.endTag(null, "app");

           /* xs.startTag(null, "app");
            xs.attribute(null, "id", app.getId() + "");
            xs.attribute(null, "name", app.getName() + "");
            xs.attribute(null, "version", app.getVersion() + "");
            xs.endTag(null, "app");*/

        }
        xs.endTag(null, "apps");
        xs.endDocument();

        xs.flush();
        output.flush();
        output.close();


//        output.write();



    }

}
