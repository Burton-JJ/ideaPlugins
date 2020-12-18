package com.burton.plugin.markbook.windows;

import com.burton.plugin.markbook.data.DateCenter;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*********************************
 * <p> 文件名称: NoteListWindow

 * <p> 模块名称：com.burton.plugin.markbook.windows
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/23
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class NoteListWindow {
    private JTextField tfTopic;
    private JTable tbContent;
    private JButton btnCreate;
    private JButton btnClear;
    private JButton btnClose;
    private JPanel contentPanel;

    private void init() {
        tbContent.setModel(DateCenter.TABLE_MODEL);
        tbContent.setEnabled(true);
    }

    public NoteListWindow(Project project, ToolWindow toolWindow) {
        init();
        //生成文档操作
        btnCreate.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic = tfTopic.getText();
                String outFileName = topic + ".md";
                if (topic == null || "".equals(topic)) {
                    MessageDialogBuilder.yesNo("操作结果", "文档标题没有输入");
                    return;
                }
                //获取保存路径
                VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
                if (null != virtualFile) {
                    String path = virtualFile.getPath();
                    String filePathAndName = path + File.separator + outFileName;
                }

            }
        });
        //清空操作
        btnClear.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                DateCenter.reset();
            }
        });
        //隐藏操作
        btnClose.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
