package com.burton.plugin.markbook.processor;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/*********************************
 * <p> 文件名称: MDFreeMarkProcessor

 * <p> 模块名称：com.burton.plugin.markbook.processor
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/23
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class MDFreeMarkProcessor extends AbstractFreeMarkProcessor {
    @Override
    protected Template getTemplate() {
        Configuration configuration = new Configuration();
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(stringTemplateLoader);
        return null;
    }

    @Override
    protected Object getModel(SourceNoteData sourceNoteData) {
        Map model = new HashMap();
        model.put("topic", sourceNoteData.getTopic());
        model.put("noteList", sourceNoteData.getNoteList());
        return model;
    }

    @Override
    protected Writer getWriter(SourceNoteData sourceNoteData) {
        return null;
    }
}
