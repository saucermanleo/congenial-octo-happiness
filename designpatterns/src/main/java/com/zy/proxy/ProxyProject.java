package com.zy.proxy;

public class ProxyProject implements Project {
    //这里没有使用多态(接口)
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
