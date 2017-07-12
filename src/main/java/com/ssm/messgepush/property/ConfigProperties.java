package com.ssm.messgepush.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 个推应用配置
 * @author harry.zhang
 * @CreateDate:	2017年7月12日
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {

	private String appId;
	
	private String appKey;
	
	private String masterSecret;
	
	private String url;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
