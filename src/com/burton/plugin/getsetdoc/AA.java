package com.burton.plugin.getsetdoc;

import freemarker.core.StringArraySequence;

public class AA {
    /**
     * 你好
     */
    private String a;


    /**
     * 获取 你好
     *
     * @return a 你好
     */
    public String getA() {
        return this.a;
    }

    /**
     * 设置 你好
     *
     * @param a 你好
     */
    public void setA(String a) {
        this.a = a;
    }
}
