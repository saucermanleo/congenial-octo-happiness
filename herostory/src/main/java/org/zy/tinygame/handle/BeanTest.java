package org.zy.tinygame.handle;

import java.util.Objects;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/1 0001 12:41
 */

public class BeanTest {
    private int veryGood;
    private int good;
    private int bad;
    private int veryBad;
    private int noResult;

    public int getVeryGood() {
        return veryGood;
    }

    public void setVeryGood(int veryGood) {
        this.veryGood = veryGood;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public int getVeryBad() {
        return veryBad;
    }

    public void setVeryBad(int veryBad) {
        this.veryBad = veryBad;
    }

    public int getNoResult() {
        return noResult;
    }

    public void setNoResult(int noResult) {
        this.noResult = noResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanTest beanTest = (BeanTest) o;
        return veryGood == beanTest.veryGood &&
                good == beanTest.good &&
                bad == beanTest.bad &&
                veryBad == beanTest.veryBad &&
                noResult == beanTest.noResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(veryGood, good, bad, veryBad, noResult);
    }

    @Override
    public String toString() {
        return "BeanTest{" +
                "veryGood=" + veryGood +
                ", good=" + good +
                ", bad=" + bad +
                ", veryBad=" + veryBad +
                ", noResult=" + noResult +
                '}';
    }
}
