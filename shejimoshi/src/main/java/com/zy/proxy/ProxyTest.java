package com.zy.proxy;
//代理模式与装饰模式的不同在于 装饰可以自由组装  代理中的聚合没有使用多态(接口)所以不能组合;
public class ProxyTest {
    public static void main(String[] args) {
        RealProject realProject = new RealProject();
        ProxyProject proxyProject = new ProxyProject(realProject);
        proxyProject.desc();
    }
}
