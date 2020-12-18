package com.burton.plugin;

/*********************************
 * <p> 文件名称: VersionTest
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/12/18
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class VersionTest {
    private String aaa;
    private String bbb;
    private String ccc;

    static {
        System.out.println("你好");
        System.out.println("你好第二行");
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bbb) {
        this.bbb = bbb;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }
}
