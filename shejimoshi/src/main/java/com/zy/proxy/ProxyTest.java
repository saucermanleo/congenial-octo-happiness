package com.zy.proxy;
//����ģʽ��װ��ģʽ�Ĳ�ͬ���� װ�ο���������װ  �����еľۺ�û��ʹ�ö�̬(�ӿ�)���Բ������;
public class ProxyTest {
    public static void main(String[] args) {
        RealProject realProject = new RealProject();
        ProxyProject proxyProject = new ProxyProject(realProject);
        proxyProject.desc();
    }
}
