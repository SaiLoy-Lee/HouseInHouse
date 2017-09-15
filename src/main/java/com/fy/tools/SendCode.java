//package com.fy.tools;
//
///**
// * Created by ryp on 2017/9/14.
// *
// * 短信验证码验证
// */
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.dysmsapi.transform.v20170525.SendSmsResponseUnmarshaller;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.http.FormatType;
//import com.aliyuncs.http.HttpResponse;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import com.fy.pojo.SMessage;
//
//import java.nio.charset.Charset;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//public class SendCode {
//
//
//    public static int sendSms(SMessage sMessage) throws ClientException, InterruptedException{
//        String type = sMessage.getsMessageType();
//        if(type.equals("1")||type=="1"){
//            int mobile_code = (int) ((Math.random()*9+1)*100000);
//            SendSmsResponse response = sendSmsVerificationCode(sMessage,mobile_code);
//            return mobile_code;
//        }else if(type.equals("2")||type=="2"){
//            SendSmsResponse response = sendSmsCancel(sMessage);
//            return 0;//0表示成功
//        }else if (type.equals("3")||type=="3"){
//            SendSmsResponse response= sendSmsPayment(sMessage);
//
//            return  0;
//
//        }else{
//            return 1;//表示失败
//        }
//
//
//    }
//
//
//    //产品名称:云通信短信API产品,开发者无需替换
//    static final String product = "Dysmsapi";
//    //产品域名,开发者无需替换
//    static final String domain = "dysmsapi.aliyuncs.com";
//
//    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
//    static final String accessKeyId = "LTAIuiBPEAGp3ePe";
//    static final String accessKeySecret = "VPRaNlnNC9svlQrAjBkdf8JxLqfnfC";
//
//    public static SendSmsResponse sendSmsVerificationCode (SMessage sMessage,int code) throws ClientException {
//
//
//        String phoneNumber=sMessage.getsMessageCell();
//
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改
//
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret
//
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改
//
//        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改
//
//        SendSmsRequest request = new SendSmsRequest();//不必修改
//
//
//        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码
//
//        request.setSignName("房中房");//此处填写已申请的短信签名
//
//        request.setTemplateCode("SMS_95760031");//此处填写获得的短信模版CODE
//
//        request.setTemplateParam("{\"code\":\""+code+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码
//
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改
//
//        return sendSmsResponse;
//    }
//
//
//    public static SendSmsResponse sendSmsCancel(SMessage sMessage) throws ClientException {
//
//        String phoneNumber=sMessage.getsMessageCell();
//        String name=sMessage.getsMessageRecipients();
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改
//
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret
//
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改
//
//        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改
//
//        SendSmsRequest request = new SendSmsRequest();//不必修改
//
//        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码
//
//        request.setSignName("房中房");//此处填写已申请的短信签名
//
//        request.setTemplateCode("SMS_95710024");//此处填写获得的短信模版CODE
//
//        request.setTemplateParam("{\"name\":\""+name+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码
//
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改
//
//        return sendSmsResponse;
//    }
//
//    public static SendSmsResponse sendSmsPayment(SMessage sMessage) throws ClientException {
//
//        String phoneNumber=sMessage.getsMessageCell();
//        String name=sMessage.getsMessageRecipients();
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改
//
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret
//
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改
//
//        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改
//
//        SendSmsRequest request = new SendSmsRequest();//不必修改
//
//        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码
//
//        request.setSignName("房中房");//此处填写已申请的短信签名
//
//        request.setTemplateCode("SMS_96165019");//此处填写获得的短信模版CODE
//
//        request.setTemplateParam("{\"name\":\""+name+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码
//
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改
//
//        return sendSmsResponse;
//    }
//
//
//
//
//}