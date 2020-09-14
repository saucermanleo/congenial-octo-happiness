package com.bjmashibing.system.test.rpc.request;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:05
 */
public class Parameter implements Serializable {
    private Class calzz;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Long requestId;
    private Object[] args;
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Long getRequestId() {
        return requestId;
    }

    public Parameter(String className, String methodName, Class<?>[] parameterTypes) {
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        requestId = UUID.randomUUID().getLeastSignificantBits();
    }

    public Class getCalzz() {
        return calzz;
    }

    public void setCalzz(Class calzz) {
        this.calzz = calzz;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", requestId=" + requestId +
                '}';
    }
}
