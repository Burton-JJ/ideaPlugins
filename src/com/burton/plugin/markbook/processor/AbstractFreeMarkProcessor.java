//package com.burton.plugin.markbook.processor;
//
//import freemarker.template.Template;
//
//import java.io.Writer;
//
///*********************************
// * <p> 文件名称: AbstractFreeMarkProcessor
//
// * <p> 模块名称：com.burton.plugin.markbook.processor
// * <p> 功能说明:
// * <p> 开发人员：jiangjun25372
// * <p> 开发时间：2020/8/23
// * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
// **********************************/
//public abstract class AbstractFreeMarkProcessor implements Processor {
//    protected abstract Template getTemplate();
//
//    protected abstract Object getModel(SourceNoteData sourceNoteData);
//
//    protected abstract Writer getWriter(SourceNoteData sourceNoteData);
//
//    @Override
//    public final void process(SourceNoteData sourceNoteData) throws Exception {
//        Template template = getTemplate();
//        Object model = getModel(sourceNoteData);
//        Writer writer = getWriter(sourceNoteData);
//        template.process(model, writer);
//    }
//}
