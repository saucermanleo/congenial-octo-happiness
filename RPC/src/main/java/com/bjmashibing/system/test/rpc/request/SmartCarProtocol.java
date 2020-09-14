package com.bjmashibing.system.test.rpc.request;

import java.util.Arrays;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 10:21
 */
public class SmartCarProtocol {
    public static final int head_data = 0X76;
    private int contentLength;
    private byte[] content;

    public SmartCarProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

    public int getHead_data() {
        return head_data;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SmartCarProtocol{" +
                "contentLength=" + contentLength +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
