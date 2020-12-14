package com.burton.plugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;
/*********************************
 * <p> 文件名称: FirstPluginAction
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin
 * <p> 功能说明: idea右下角提示
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/22
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class FirstPluginAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        NotificationGroup notificationGroup = new NotificationGroup("firstPlugin_id", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification("testBurton", MessageType.INFO);
        Notifications.Bus.notify(notification);
    }
}
