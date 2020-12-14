package com.burton.plugin.markbook.processor;

import com.burton.plugin.markbook.data.NoteData;

import java.util.List;

/*********************************
 * <p> 文件名称: SourceNoteData
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.markbook.processor
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/23
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface SourceNoteData {
    public String getFileName();

    public String getTopic();

    public List<NoteData> getNoteList();
}
