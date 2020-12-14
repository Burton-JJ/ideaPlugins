package com.burton.plugin.markbook.dialogs;

import com.burton.plugin.markbook.data.DataConvert;
import com.burton.plugin.markbook.data.DateCenter;
import com.burton.plugin.markbook.data.NoteData;
import com.burton.plugin.utils.ContentUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;

/*********************************
 * <p> 文件名称: AddNoteDialog
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.markbook.dialogs
 * <p> 功能说明: 添加MB笔记弹出框
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/22
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class AddNoteDialog extends DialogWrapper {
    private EditorTextField tfTitle;
    private EditorTextField tfMark;
    public AddNoteDialog() {
        super(true);
        setTitle("添加笔记注释");
        init();
    }

    /**
     * 弹出框中间部分
     * @return
     */
    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        tfTitle = new EditorTextField("笔记标题");
        tfMark = new EditorTextField("笔记内容");
        tfMark.setPreferredSize(new Dimension(200, 100));
        jPanel.add(tfTitle, BorderLayout.NORTH);
        jPanel.add(tfMark, BorderLayout.CENTER);
        return jPanel;
    }

    /**
     * 弹出框下边部分（按钮）
     * @return
     */
    @Override
    protected JComponent createSouthPanel() {
        JPanel jPanel = new JPanel();
        JButton jButton = new JButton("添加笔记");
        jButton.addActionListener(e -> {
            String title = tfTitle.getText();
            String mark = tfMark.getText();
            String fileType = DateCenter.FILE_NAME.substring(DateCenter.FILE_NAME.lastIndexOf(".") + 1);
            NoteData noteData = new NoteData(title, mark, DateCenter.SELECT_TEXT, DateCenter.FILE_NAME, fileType);
            DateCenter.NOTE_LIST.add(noteData);
            //System.out.println(DateCenter.NOTE_LIST);
            DateCenter.NOTE_LIST.add(noteData);
            DateCenter.TABLE_MODEL.addRow(DataConvert.convert(noteData));
        });
        jPanel.add(jButton);
        return jPanel;
    }
}
