package com.burton.plugin.getsetdoc.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*********************************
 * <p> 文件名称: FormatSetting

 * <p> 模块名称：com.burton.plugin.getsetdoc.settings
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/24
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
@State(
        name = "FormatSetting",
        storages = {@Storage(
                //id = "other",
                file = "$APP_CONFIG$/format.xml"
        )}
)
public class FormatSetting implements PersistentStateComponent<Element> {
    private String setFormat;

    private String getFormat;

    public FormatSetting() {
    }

    public static FormatSetting getInstance() {
        return (FormatSetting) ServiceManager.getService(FormatSetting.class);
    }

    /**
     * 关闭时会调用
     * @return
     */
    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("FormatSetting");
        element.setAttribute("setFormat", this.getSetFormat());
        element.setAttribute("getFormat", this.getGetFormat());
        return element;
    }

    @Override
    public void loadState(@NotNull Element element) {
        this.setSetFormat(element.getAttributeValue("setFormat"));
        this.setGetFormat(element.getAttributeValue("getFormat"));

    }

    public String getSetFormat() {
        return this.setFormat == null ? StatementGenerator.defaultSetFormat : this.setFormat;
    }

    public void setSetFormat(String setFormat) {
        this.setFormat = setFormat;
    }

    public String getGetFormat() {
        return this.getFormat == null ? StatementGenerator.defaultGetFormat : this.getFormat;
    }

    public void setGetFormat(String getFormat) {
        this.getFormat = getFormat;
    }
}
