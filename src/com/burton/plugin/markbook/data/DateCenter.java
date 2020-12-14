package com.burton.plugin.markbook.data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

/*********************************
 * <p> 文件名称: DateCenter
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.markbook.data
 * <p> 功能说明: 文件中心
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/23
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class DateCenter {
    public static String SELECT_TEXT;
    public static String FILE_NAME;
    public static List<NoteData> NOTE_LIST = new LinkedList<>();
    public static String[] HEAD = {"标题", "备注", "文件名", "代码段"};
    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, HEAD);

    public static void reset() {
        NOTE_LIST.clear();
        TABLE_MODEL.setDataVector(null, HEAD);
    }


}
