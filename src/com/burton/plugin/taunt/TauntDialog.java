//package com.burton.plugin.taunt;
//
//import com.burton.plugin.utils.ContentUtil;
//import com.intellij.openapi.ui.DialogWrapper;
//import org.jetbrains.annotations.Nullable;
//
//import javax.swing.*;
///*********************************
// * <p> 文件名称: MyApplicationComponent
// * <p> 模块名称：com.burton.plugin.taunt
// * <p> 功能说明: idea启动每日鸡汤
// * <p> 开发人员：jiangjun25372
// * <p> 开发时间：2020/8/22
// * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
// **********************************/
//public class TauntDialog extends DialogWrapper {
//    private JLabel jLabel;
//    protected TauntDialog() {
//        super(true);
//        setTitle("毒鸡汤标题");
//        init();
//    }
//
//    @Override
//    protected @Nullable
//    JComponent createCenterPanel() {
//        JPanel jPanel = new JPanel();
//        jLabel = new JLabel("毒鸡汤内容");
//        jPanel.add(jLabel);
//        return jPanel;
//    }
//
//    @Override
//    protected JComponent createSouthPanel() {
//        JPanel jPanel = new JPanel();
//        JButton jButton = new JButton("再来一碗");
//        jButton.addActionListener(e -> {
//            jLabel.setText(ContentUtil.getContent());
//        });
//        jPanel.add(jButton);
//        return jPanel;
//    }
//}
