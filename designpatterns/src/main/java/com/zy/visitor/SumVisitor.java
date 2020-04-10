package com.zy.visitor;

/**
 * 计算总和
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:45
 */
public class SumVisitor implements Visitor {

    private float sum = 0f;

    public float getSum() {
        return sum;
    }

    public void clear(){
        sum = 0f;
    }

    @Override
    public void visit(String s) {
        try{
           sum =sum + Float.parseFloat(s);
        }catch (Exception e){

        }
    }

    @Override
    public void visit(Integer i) {
        sum =sum + i;
    }

    @Override
    public void visit(Float f) {
        sum =sum +  f;
    }
}
