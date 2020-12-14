package com.burton.plugin.markbook.windows;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/*********************************
 * <p> 文件名称: NoteListWindowFactory
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.markbook.windows
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/23
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class NoteListWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        NoteListWindow noteListWindow = new NoteListWindow(project, toolWindow);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(noteListWindow.getContentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
