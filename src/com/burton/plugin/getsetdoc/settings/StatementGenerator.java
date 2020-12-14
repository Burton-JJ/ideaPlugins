package com.burton.plugin.getsetdoc.settings;

/*********************************
 * <p> 文件名称: StatementGenerator
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.getsetdoc.settings
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/24
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class StatementGenerator {
    public static String defaultGetFormat = "/**\n" +
            " * 获取 #{bare_field_comment}\n" +
            " * \n" +
            " * @return ${field.name} #{bare_field_comment}  \n" +
            " */ ";
    public static String defaultSetFormat = "/**\n" +
            " * 设置 #{bare_field_comment}\n" +
            " * \n" +
            " * @param ${field.name} #{bare_field_comment}  \n" +
            " */ ";
}
