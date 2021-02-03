package com.example.demo;

/**
 * TODO
 *
 * @author Zangy
 * @version 1.0
 * @date 2021/1/21 0021 下午 3:26
 */
public class MessageInfoDTO {
    private String messageid;
    private String appurl;
    private String pcurl;
    private String content;

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }

    public String getPcurl() {
        return pcurl;
    }

    public void setPcurl(String pcurl) {
        this.pcurl = pcurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageInfoDTO{" +
                "messageid='" + messageid + '\'' +
                ", appurl='" + appurl + '\'' +
                ", pcurl='" + pcurl + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
