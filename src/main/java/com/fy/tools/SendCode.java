package com.fy.tools;


/**
 * Created by ryp on 2017/9/14.
 *
 * 短信验证码验证
 */
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.dysmsapi.transform.v20170525.SendSmsResponseUnmarshaller;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fy.mapper.MessageMapper;
import com.fy.mapper.UserMapper;
import com.fy.pojo.SMessage;
import com.fy.service.MessageService;
import com.fy.service.UserService;
import com.google.inject.spi.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class SendCode {

    @Autowired
    private MessageService messageService;


    public  int sendSms(SMessage sMessage) throws ClientException, InterruptedException {
        String type = sMessage.getHhSmessageType();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        df.format(date);

        String uuid = UUID.randomUUID().toString();


        if (type.equals("1") || type == "1") {
            int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
            SendSmsResponse response = sendSmsVerificationCode(sMessage, mobile_code);

            String name = sMessage.getHhSmessageRecipients();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent("您好，您的验证码为：" + mobile_code + "，请牢记，千万不要告诉别人哦。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return mobile_code;
        } else if (type.equals("2") || type == "2") {
            SendSmsResponse response = sendSmsCancel(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent("尊敬的" + name + "您好，您的账号已被停用，请您确认，如有疑问请联系管理员。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;//0表示成功
        } else if (type.equals("3") || type == "3") {
            SendSmsResponse response = sendSmsPayment(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent(name + "您好，您租住的房屋租住时间即将到期，请提前5天续租房屋，逾期将收取滞纳金，房中房不接受转账，谨防诈骗。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;

        } else if (type.equals("4") || type == "4") {
            SendSmsResponse response = sendSmsWillCancel(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent("尊敬的" + name + "您好，您的账号即将停用，如有疑问请联系管理员。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;
        } else if (type.equals("5") || type == "5") {
            SendSmsResponse response = sendSmsOrdersGenerate(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            String order = sMessage.getHhSmessageOrdersId();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent("尊敬的" + name + "您好，您的订单已成功生成，订单号为" + order + "，请您确认，如有疑问请联系管理员。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;
        } else if(type.equals("6") || type == "6"){
            SendSmsResponse response =sendSmsOrdersCancel(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            String order = sMessage.getHhSmessageOrdersId();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent("尊敬的"+name+"，您好，您的订单号"+order+"已成功取消。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;
        } else if(type.equals("7") || type == "7"){
            SendSmsResponse response =sendSmsUserQuit(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            String userId = sMessage.getHhSmessageUserID();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent(name+"管理员您好，"+userId+"用户申请退房，请审核。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;
        }else if (type.equals("10") || type == "10") {
            SendSmsResponse response = sendSmsAuditingOrders(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            String order = sMessage.getHhSmessageOrdersId();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent("尊敬的"+name+"管理员，您好，"+order+"订单已生成，请审核。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;

        } else if(type.equals("8") || type == "8"){
            SendSmsResponse response =sendSmsUserQuit(sMessage);

            String name = sMessage.getHhSmessageRecipients();
            String userId = sMessage.getHhSmessageUserID();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageContent(name+"管理员，您好，"+userId+"用户的房源信息录入已提交，请审核。");
            sMessage.setHhSmessageIsOk("true");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 0;
        }else {
            String name = sMessage.getHhSmessageRecipients();
            sMessage.setHhSmessageId(uuid);
            sMessage.setHhSmessageIsOk("false");
            sMessage.setCreateBy(name);
            sMessage.setCreateTime(date);

            System.out.print(sMessage.toString());

            messageService.saveMessage(sMessage);

            return 1;//表示失败
        }



    }




    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIuiBPEAGp3ePe";
    static final String accessKeySecret = "VPRaNlnNC9svlQrAjBkdf8JxLqfnfC";

    //短信验证
    public static SendSmsResponse sendSmsVerificationCode (SMessage sMessage,int code) throws ClientException {


        String phoneNumber=sMessage.getHhSmessageCell();
        String name =sMessage.getHhSmessageRecipients();

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改


        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96435034");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"code\":\""+code+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }

    //账号停用通知
    public static SendSmsResponse sendSmsCancel(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name=sMessage.getHhSmessageRecipients();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96560024");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }

    //账号即将停用通知
    public static SendSmsResponse sendSmsWillCancel(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name=sMessage.getHhSmessageRecipients();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96560033");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }


    //缴费通知
    public static SendSmsResponse sendSmsPayment(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name=sMessage.getHhSmessageRecipients();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96440023");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }

    //订单成功生成通知
    public static SendSmsResponse sendSmsOrdersGenerate(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name =sMessage.getHhSmessageRecipients();
        String order=sMessage.getHhSmessageOrdersId();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96605034");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\", \"OrdersId\":\""+order+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }

    //通知管理员审核订单
    public static SendSmsResponse sendSmsAuditingOrders(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name =sMessage.getHhSmessageRecipients();
        String order=sMessage.getHhSmessageOrdersId();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96735006");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\", \"order\":\""+order+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }

    //通知用户订单取消成功
    public static SendSmsResponse sendSmsOrdersCancel(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name =sMessage.getHhSmessageRecipients();
        String order=sMessage.getHhSmessageOrdersId();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96840015");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\", \"order\":\""+order+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }

    //通知管理员用户需要退房
    public static SendSmsResponse sendSmsUserQuit(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name =sMessage.getHhSmessageRecipients();
        String userId=sMessage.getHhSmessageUserID();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96720017");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\", \"userId\":\""+userId+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }
    //通知管理员审核用户提交的房源信息录入
    public static SendSmsResponse sendSmsHouseForUser(SMessage sMessage) throws ClientException {

        String phoneNumber=sMessage.getHhSmessageCell();
        String name =sMessage.getHhSmessageRecipients();
        String userId=sMessage.getHhSmessageUserID();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);//"***"分别填写自己的AccessKey ID和Secret

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);//不必修改

        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改

        SendSmsRequest request = new SendSmsRequest();//不必修改

        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码

        request.setSignName("房中房");//此处填写已申请的短信签名

        request.setTemplateCode("SMS_96650046");//此处填写获得的短信模版CODE

        request.setTemplateParam("{\"name\":\""+name+"\", \"userId\":\""+userId+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改

        return sendSmsResponse;
    }

}