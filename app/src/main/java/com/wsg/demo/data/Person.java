package com.wsg.demo.data;

import com.wsg.demo.util.HanYuPinYin;

/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class Person {
    public String face;
    public String name;
    public String firstLetter = isNotBlank(name) ? HanYuPinYin.cn2py(name)
            : "";

    public Person(String face, String name, String sign) {
        this.face = face;
        this.name = name;
        this.sign = sign;
    }

    public String getFirstLetter() {
        if (isBlank(firstLetter)) {
            firstLetter = isNotBlank(name) ? HanYuPinYin.cn2py(name)
                    : "";
        }
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    private String sign;

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0 && !"null".equals(str)
                && str != "";
    }

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        if (str == "") {
            return true;
        }
        if (str == "null") {
            return true;
        }
        return str.trim().length() == 0;
    }
}
