package com.zy.proxy;

public class ProxyProject implements Project {
    //����û��ʹ�ö�̬(�ӿ�)
    RealProject realProject;

    public ProxyProject(RealProject realProject) {
        this.realProject = realProject;
    }

    @Override
    public void desc() {
        System.out.println("do something pre");
        realProject.desc();
        System.out.println("do something post");
    }
}
