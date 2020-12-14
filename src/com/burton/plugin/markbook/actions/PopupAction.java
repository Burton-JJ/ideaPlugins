package com.burton.plugin.markbook.actions;

import com.burton.plugin.markbook.data.DateCenter;
import com.burton.plugin.markbook.dialogs.AddNoteDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

public class PopupAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        //region1、获取鼠标选择内容
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        SelectionModel selectionModel = editor.getSelectionModel();
        String selectedText = selectionModel.getSelectedText();
        //endregion

//        System.out.println(selectedText);
//        System.out.println("添加笔记");
        //将选中文本添加到数据中心
        DateCenter.SELECT_TEXT = selectedText;

        //获取当前操作文件的名称
        String name = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile().getName();
        //将选中文本所在文件的名称添加到数据中心
        DateCenter.FILE_NAME = name;

        AddNoteDialog addNoteDialog = new AddNoteDialog();
        addNoteDialog.show();
    }
}
