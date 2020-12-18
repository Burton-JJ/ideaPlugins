package com.burton.plugin.getsetdoc.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/*********************************
 * <p> 文件名称: FormatConfigurable

 * <p> 模块名称：com.burton.plugin.getsetdoc.settings
 * <p> 功能说明: 集成SearchableConfigurable实现createComponent方法创建的Jcomponent会展示在settings面板上
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/24
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class FormatConfigurable implements SearchableConfigurable {
    private FormatForm formatForm;
    private FormatSetting formatSetting = FormatSetting.getInstance();

    public FormatConfigurable() {
    }

    @NotNull
    @Override
    public String getId() {
        return "DocFormat";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return this.getId();
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return this.getId();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if(null == this.formatForm) {
            this.formatForm = new FormatForm();
        }

        return this.formatForm.mainPanel;
    }

    @Override
    public boolean isModified() {
        return !this.formatSetting.getGetFormat().equals(this.formatForm.getFormatTextArea.getText()) || !this.formatSetting.getSetFormat().equals(this.formatForm.setFormatTextArea.getText());
    }

    @Override
    public void apply() throws ConfigurationException {
        this.formatSetting.setGetFormat(this.formatForm.getFormatTextArea.getText());
        this.formatSetting.setSetFormat(this.formatForm.setFormatTextArea.getText());
    }

    @Override
    public void reset() {
        this.formatForm.getFormatTextArea.setText(this.formatSetting.getGetFormat());
        this.formatForm.setFormatTextArea.setText(this.formatSetting.getSetFormat());
    }

}
