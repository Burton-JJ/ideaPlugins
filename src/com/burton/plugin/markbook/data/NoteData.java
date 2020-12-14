package com.burton.plugin.markbook.data;

/*********************************
 * <p> 文件名称: NoteData
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.markbook.data
 * <p> 功能说明: 记录笔记实体对象
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/23
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class NoteData {
    private String title;
    private String mark;
    private String content;
    private String fileName;
    private String fileType;

    public NoteData(String title, String mark, String content, String fileName, String fileType) {
        this.title = title;
        this.mark = mark;
        this.content = content;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "NoteData{" +
                "title='" + title + '\'' +
                ", mark='" + mark + '\'' +
                ", content='" + content + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
