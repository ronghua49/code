package com.winway.scm;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:config.properties")
@EnableConfigurationProperties
@Component
@ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false)
public class ConfigProperties {

    private String uploadFilesUrl;
    private String uploadPicUrl;

    public   String getUploadPicUrl() {
        return uploadPicUrl;
    }

    public void setUploadPicUrl(String uploadPicUrl) {
        this.uploadPicUrl = uploadPicUrl;
    }

    public String getUploadFilesUrl() {
        return uploadFilesUrl;
    }

    public void setUploadFilesUrl(String uploadFilesUrl) {
        this.uploadFilesUrl = uploadFilesUrl;
    }
}
