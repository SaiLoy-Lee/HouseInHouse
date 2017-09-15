package com.fy.tools;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Created by ryp on 2017/9/13.
 *
 * 短信验证码验证，
 */
public class Sendsms {


    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    public static int senMsg(String phonenum) {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

        int mobile_code = (int)((Math.random()*9+1)*100000);

        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
//        String content = new String("您好，您的验证码是：" + mobile_code + "-达内之家");
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "C03692730"),
                new NameValuePair("password", "b192e71b2b859905d6eb005a08ea9cb8"), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", phonenum),
                new NameValuePair("content", content),
        };


        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();

            System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mobile_code;

    }

    public static void main(String[] args)  {

        int code =senMsg("13161382721");
        System.out.print(code);


    }

}
