package com.zrj.edumsm.service.impl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

// 导入对应SMS模块的client
import com.tencentcloudapi.sms.v20210111.SmsClient;

// 导入要请求接口对应的request response类
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.zrj.edumsm.service.MsmService;
import com.zrj.edumsm.util.RandomUtil;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MsmServiceImpl
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/30
 * @Version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phoneNumbers,String code) {
        try {
            Credential credential = new Credential("AKIDHOI0UjkW4OYP8WheNs89wTlymLfkaCgp", "zjEUVIq9E4f1A4j9KP7aonY4LNAi27xZ");
            //HttpProfile这是http的配置文件操作，比如设置请求类型(post,get)或者设置超时时间了、还有指定域名了
            //最简单的就是实例化该对象即可，它的构造方法已经帮我们设置了一些默认的值
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            //实例化一个客户端配置对象,这个配置可以进行签名（使用私钥进行加密的过程），对方可以利用公钥进行解密
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            //实例化要请求产品(以sms为例)的client对象
            SmsClient smsClient = new SmsClient(credential, "ap-guangzhou", clientProfile);
            //实例化request封装请求信息
            SendSmsRequest request = new SendSmsRequest();

            /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
            // 应用 ID 可前往 [短信控制台](https://console.cloud.tencent.com/smsv2/app-manage) 查看
            String sdkAppId = "1400715811";
            request.setSmsSdkAppId(sdkAppId);
            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名 */
            String signName = "葵心天意个人公众号";
            request.setSignName(signName);
            /* 模板 ID: 必须填写已审核通过的模板 ID */
            // 模板 ID 可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-template) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-template) 的正文模板管理查看
            String templateId = "1494203";
            request.setTemplateId(templateId);
            //web层生成随机验证码code
            /* 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空 */
            String[] templateParamSet = {code, "5"};
            request.setTemplateParamSet(templateParamSet);
            String[] phoneNumberSet = {"+86"+phoneNumbers};
            request.setPhoneNumberSet(phoneNumberSet);

            SendSmsResponse response = smsClient.SendSms(request);
            System.out.println(SendSmsResponse.toJsonString(response));
            return true;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return false;
        }
    }
}
