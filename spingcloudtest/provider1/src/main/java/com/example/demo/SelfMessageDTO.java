package com.example.demo;

/**
 * TODO
 *
 * @author Zangy
 * @version 1.0
 * @date 2021/1/21 0021 下午 3:25
 */

public class SelfMessageDTO {
    private String servicecode;
    private String time;
    private String sign;
    private String userids;
    private MessageInfoDTO message;
    private String remindtype;
    private String datatype;

    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserids() {
        return userids;
    }

    public void setUserids(String userids) {
        this.userids = userids;
    }

    public MessageInfoDTO getMessage() {
        return message;
    }

    public void setMessage(MessageInfoDTO message) {
        this.message = message;
    }

    public String getRemindtype() {
        return remindtype;
    }

    public void setRemindtype(String remindtype) {
        this.remindtype = remindtype;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    @Override
    public String toString() {
        return "SelfMessageDTO{" +
                "servicecode='" + servicecode + '\'' +
                ", time='" + time + '\'' +
                ", sign='" + sign + '\'' +
                ", userids='" + userids + '\'' +
                ", message=" + message +
                ", remindtype='" + remindtype + '\'' +
                ", datatype='" + datatype + '\'' +
                '}';
    }
}
