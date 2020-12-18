package com.burton.plugin.servicepublish.testexample;

import com.burton.plugin.servicepublish.testexample.reqresp.TestReq;
import com.burton.plugin.servicepublish.testexample.reqresp.TestResp;

/*********************************
 * <p> 文件名称: TestInterface
 * <p> 模块名称：com.burton.plugin
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/26
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface TestInterface {

    TestResp getTestcc(TestReq req);
}
