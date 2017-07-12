package com.ssm.messgepush.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.ssm.messgepush.property.ConfigProperties;
import com.ssm.messgepush.service.AppPushService;

@Service
public class AppPushServiceImpl implements AppPushService {
	
	@Autowired
	private ConfigProperties configProperties;

	@Override
	public void pushMessageToAll() {
		
		IGtPush push = new IGtPush(configProperties.getUrl(), configProperties.getAppKey(), configProperties.getMasterSecret());

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(configProperties.getAppId());
        template.setAppkey(configProperties.getAppKey());
        template.setTitle("欢迎使用个推!");
        template.setText("这是一条推送消息~");
        template.setUrl("http://getui.com");

        List<String> appIds = new ArrayList<String>();
        appIds.add(configProperties.getAppId());

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
		
	}

	@Override
	public void pushMessageToSingle() {
	    String CID = "5abfe84bf25dfc9d5fcbd69126a33423";
	    
		IGtPush push = new IGtPush(configProperties.getUrl(), configProperties.getAppKey(), configProperties.getMasterSecret());
        LinkTemplate template = linkTemplateDemo(configProperties.getAppId(),configProperties.getAppKey());
        template.setUrl("http://www.baidu.com");
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(configProperties.getAppId());
        target.setClientId(CID);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
		
	}
	 public static LinkTemplate linkTemplateDemo(String appId,String appKey) {
	        LinkTemplate template = new LinkTemplate();
	        // 设置APPID与APPKEY
	        template.setAppId(appId);
	        template.setAppkey(appKey);

	        Style0 style = new Style0();
	        // 设置通知栏标题与内容
	        style.setTitle("这个是单独推送的");
	        style.setText("这是一单独条推送消息~");
	        // 配置通知栏图标
	        style.setLogo("icon.png");
	        // 配置通知栏网络图标
	        style.setLogoUrl("");
	        // 设置通知是否响铃，震动，或者可清除
	        style.setRing(true);
	        style.setVibrate(true);
	        style.setClearable(true);
	        template.setStyle(style);

	        // 设置打开的网址地址
	        template.setUrl("http://www.baidu.com");
	        return template;
	    }
	
	

}
