package com.zy.subjectobserver;

public class BossSubject extends Subject{

    private boolean sate = true;

    public boolean isSate() {
        return sate;
    }

    public void setSate(boolean sate) {
        if(sate){
            System.out.println("boss is coming");
        }else{
            System.out.println("boss is leaving");
        }
        this.sate = sate;
        super.notifyUpdate();
    }

}
