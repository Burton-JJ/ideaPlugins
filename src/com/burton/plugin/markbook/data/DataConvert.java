package com.burton.plugin.markbook.data;

/*********************************
 * <p> 文件名称: DataConvert

 * <p> 模块名称：com.burton.plugin.markbook.data
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/23
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class DataConvert {
    public static String[] convert(NoteData noteData) {
        String[] raw = new String[4];
        raw[0] = noteData.getTitle();
        raw[1] = noteData.getMark();
        raw[2] = noteData.getFileName();
        raw[3] = noteData.getContent();
        return raw;
    }
}
