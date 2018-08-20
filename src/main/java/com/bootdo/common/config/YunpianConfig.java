package com.bootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "yunpian", ignoreUnknownFields = false)
@PropertySource("classpath:config/yunpian.properties")
@Component
public class YunpianConfig {
	
	private String apiKey;
	
	private String url;
	
	private String multiUrl;
	
	private String batchUrl;
	
	private String flag;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMultiUrl() {
		return multiUrl;
	}

	public void setMultiUrl(String multiUrl) {
		this.multiUrl = multiUrl;
	}

	public String getBatchUrl() {
		return batchUrl;
	}

	public void setBatchUrl(String batchUrl) {
		this.batchUrl = batchUrl;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
