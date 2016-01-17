package com.example.administrator.xmlrestest_06_66;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button)findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.books);
                try{
                    StringBuilder stringBuilder = new StringBuilder("");
                    while(xmlResourceParser.getEventType()!=XmlResourceParser.END_DOCUMENT){
                        if(xmlResourceParser.getEventType()==XmlResourceParser.START_TAG){
                            String tagName = xmlResourceParser.getName();
                            if(tagName.equals("book")){
                                //根据属性名来获取属性值
//                                String price = xmlResourceParser.getAttributeValue(null,"price");
                                String price = xmlResourceParser.getAttributeValue(0);
                                stringBuilder.append("价格：");
                                stringBuilder.append(price);
                                String date = xmlResourceParser.getAttributeValue(1);
                                stringBuilder.append("    出版日期"+date);
                                stringBuilder.append(" 书名："+xmlResourceParser.nextText());

                            }
                            stringBuilder.append("\n");

                        }
                        xmlResourceParser.next();

                    }
                    EditText show = (EditText)findViewById(R.id.show);
                    show.setText(stringBuilder.toString());


                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
