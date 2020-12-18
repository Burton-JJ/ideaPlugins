package com.burton.plugin.servicepublish.action;

import com.burton.plugin.servicepublish.settings.ServicePublishConfig;
import com.burton.plugin.servicepublish.utils.ServicePublishUtil;
import com.burton.plugin.servicepublish.utils.TbspInterfaceMethodInfo;
import com.intellij.lang.Language;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.intellij.psi.search.EverythingGlobalScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.TypeConversionUtil;
import com.intellij.psi.xml.XmlFile;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneratePublishXmlAction extends AnAction {
    private NotificationGroup notificationGroup = new NotificationGroup("servicePublish_id", NotificationDisplayType.BALLOON, true);

    @Override
    public void actionPerformed(final AnActionEvent e) {
        //获取配置
        ServicePublishConfig servicePublishConfig = ServiceManager.getService(ServicePublishConfig.class);
        final Map<String, String> configMap = servicePublishConfig.getConfigResult();

        //动态加载common jar包
        try {
            ServicePublishUtil.addUrl(new File(configMap.get("commonJarPath")));
        } catch (Exception ex) {
            //提示加载jar包出错
            //NotificationGroup notificationGroup = new NotificationGroup("servicePublish_id", NotificationDisplayType.BALLOON, true);
            Notification notification = notificationGroup.createNotification("加载commonjar包失败", MessageType.ERROR);
            Notifications.Bus.notify(notification);
        }

        //获取选中的文件
        final PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        //转化为class文件
        PsiClass c = getPsiClass(psiFile);
        //获取接口所在报名 com.burton.plugin
        PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
        String servicePackageName = psiJavaFile.getPackageName();
        //获取接口名
        String serviceName = c.getName();
        String completeServiceName = servicePackageName + "." + serviceName;
        //获取接口的方法列表
        PsiMethod[] methods = c.getMethods();
        final List<TbspInterfaceMethodInfo> interfaceMethodInfoList = new ArrayList<>();
        for (PsiMethod method : methods) {
            TbspInterfaceMethodInfo methodInfo = new TbspInterfaceMethodInfo();
            //方法名
            String methodName = method.getName();
            //获取入参
            PsiParameter[] extParams = method.getParameterList().getParameters();
            PsiParameter extParam = extParams[0];
            //获取入参的Type 其实就是入参的类名
            PsiType reqType = TypeConversionUtil.erasure(extParam.getType());
            //入参类名
            String reqClassName = reqType.getPresentableText();
            //根据入参的类名转为class
            PsiClass reqClass = getPsiClassByName(reqClassName, e);
            //将入参class转为javaFile 为了获取如在所在包的包名
            PsiJavaFile reqJavaFile = (PsiJavaFile) reqClass.getContainingFile();
            //获取入参所在包包名
//            PsiPackage reqpkg = JavaPsiFacade.getInstance(e.getProject()).findPackage(reqJavaFile.getPackageName());
//            String reqPkgName = reqpkg.getQualifiedName();
            String reqPkgName = reqJavaFile.getPackageName();
            String reqPkgAndName = reqPkgName + "." + reqClassName;

//            PsiTypeParameter[] typeParameters = method.getTypeParameters();
//            PsiTypeParameter reqType = typeParameters[0];
            //出参Type
            PsiType respType = method.getReturnType();
            //出参类名
            String respClassName = respType.getPresentableText();
            //根据出参的类名转为class
            PsiClass respClass = getPsiClassByName(respClassName, e);
            //将入参class转为javaFile 为了获取如在所在包的包名
            PsiJavaFile respJavaFile = (PsiJavaFile) respClass.getContainingFile();
            //获取入参所在包包名
//            PsiPackage resppkg = JavaPsiFacade.getInstance(e.getProject()).findPackage(respJavaFile.getPackageName());
//            String respPkgName = resppkg.getQualifiedName();
            String respPkgName = respJavaFile.getPackageName();
            String respPkgAndName = respPkgName + "." + respClassName;

            methodInfo.setServiceName(completeServiceName);
            methodInfo.setServicePackageName(servicePackageName);
            methodInfo.setMethodName(methodName);
            methodInfo.setParamName(reqPkgAndName);
            methodInfo.setReturnName(respPkgAndName);
            interfaceMethodInfoList.add(methodInfo);

//            JvmParameter[] parameters = method.getParameters();
//            JvmParameter req = parameters[0];
        }
        if (!interfaceMethodInfoList.isEmpty()) {
                ApplicationManager.getApplication().runWriteAction(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ServicePublishUtil.generateServiceWrapper(interfaceMethodInfoList, configMap.get("serviceWrapperXmlPath"), e);
                                    ServicePublishUtil.generateService(interfaceMethodInfoList, configMap.get("serviceXmlPath"), e);
                                } catch (Exception ex) {
                                    //提示服务发布出错
                                    Notification notification = notificationGroup.createNotification("生成服务发布文件失败", MessageType.ERROR);
                                    Notifications.Bus.notify(notification);
                                }
                            }
                        }
                );
                Notification notification = notificationGroup.createNotification("生成服务发布文件成功", MessageType.INFO);
                Notifications.Bus.notify(notification);

        }

    }

    /**
     * 按钮什么情况下能显示 什么情况下不能显示
     *
     * @param e
     */
    @Override
    public void update(@NotNull AnActionEvent e) {
        //只有接口文件可以点击按钮
        final PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Language language = psiFile.getLanguage();
        String languageType = language.getID().toLowerCase();
        if ("java".equals(languageType) && getPsiClass(psiFile).isInterface()) {
            e.getPresentation().setEnabled(true);
        } else {
            e.getPresentation().setEnabled(false);
        }

//        if ("java".equals(getFileExtension(e.getDataContext()))) {
//            e.getPresentation().setEnabled(true);
//        } else {
//            e.getPresentation().setEnabled(false);
//        }
    }

    public static String getFileExtension(DataContext dataContext) {
        VirtualFile file = DataKeys.VIRTUAL_FILE.getData(dataContext);
        return file == null ? null : file.getExtension();
    }

    private PsiElement getPsiElement(AnActionEvent e) {
        PsiFile psiFile = (PsiFile) e.getData(LangDataKeys.PSI_FILE);
        Editor editor = (Editor) e.getData(PlatformDataKeys.EDITOR);
        if (psiFile != null && editor != null) {
            int offset = editor.getCaretModel().getOffset();
            return psiFile.findElementAt(offset);
        } else {
            e.getPresentation().setEnabled(false);
            return null;
        }
    }

    private PsiClass getPsiMethodFromContext(AnActionEvent e) {
        PsiElement elementAt = this.getPsiElement(e);
        return elementAt == null ? null : (PsiClass) PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
    }

    public static PsiClass getPsiClassByName(String className, AnActionEvent e) {
        PsiClass[] psiClasses = PsiShortNamesCache.getInstance(e.getProject()).getClassesByName(className, new EverythingGlobalScope(e.getProject()));
        return psiClasses[0];
    }

    //psiFile转psiClass
    public static PsiClass getPsiClass(PsiFile psiFile) {
        String fullName = psiFile.getName();
        String className = fullName.split("\\.")[0];
        PsiClass[] psiClasses = PsiShortNamesCache.getInstance(psiFile.getProject()).getClassesByName(className, new EverythingGlobalScope(psiFile.getProject()));
        return psiClasses[0];
    }

    //psiFile转psiClass2
    public static PsiClass getPsiClass2(PsiFile psiFile) {
        PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
        final PsiClass[] classes = psiJavaFile.getClasses();
        return classes[0];
    }


//    /**
//     * 添加需要扫描的jar包
//     *
//     * @param jarPath
//     * @throws NoSuchMethodException
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     * @throws MalformedURLException
//     */
//    public static void addUrl(File jarPath) throws NoSuchMethodException, InvocationTargetException,
//            IllegalAccessException, MalformedURLException {
//
//        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//        // 反射获取类加载器中的addURL方法，并将需要加载类的jar路径
//        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//        if (!method.isAccessible()) {
//            method.setAccessible(true);
//        }
//        URL url = jarPath.toURI().toURL();
//        // 把当前jar的路径加入到类加载器需要扫描的路径
//        method.invoke(classLoader, url);
//    }

    private static void generateServiceWrapper(List<TbspInterfaceMethodInfo> interfaceMethodInfoList, String filePath, AnActionEvent e) throws Exception {
        if (interfaceMethodInfoList != null && interfaceMethodInfoList.size() > 0) {
            String serviceName = interfaceMethodInfoList.get(0).getServiceName().substring(interfaceMethodInfoList.get(0).getServiceName().lastIndexOf(".") + 1);
            String fileName = serviceName + ".servicewrapper.new.xml";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<method-configs>");
            //stringBuilder.append(System.lineSeparator());
            for (TbspInterfaceMethodInfo tbspInterfaceMethodInfo : interfaceMethodInfoList) {
                stringBuilder.append("<method-config service-id=\"").append(tbspInterfaceMethodInfo.getServiceName()).append(".").append(tbspInterfaceMethodInfo.getMethodName())
                        .append("\" type=\"").append(tbspInterfaceMethodInfo.getServiceName()).append("\" method-name=\"").append(tbspInterfaceMethodInfo.getMethodName()).append("\">");
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append("<parameter-type type=\"").append(tbspInterfaceMethodInfo.getParamName()).append("\"/>");
                //stringBuilder.append(System.lineSeparator());
                stringBuilder.append("</method-config>");
                //stringBuilder.append(System.lineSeparator());
            }
            stringBuilder.append("</method-configs>");
            String formatedXml = formatXml(stringBuilder.toString());
            //System.out.println(formatedXml);
            //创建Xml文件
            XmlFile psiFile = (XmlFile) PsiFileFactory.getInstance(e.getProject()).createFileFromText(fileName, StdFileTypes.XML, formatedXml);
            File ioFile = new File(filePath);
            VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(ioFile);
            //virtualFile.refresh(false, true)

            PsiDirectory psiDirectory = PsiDirectoryFactory.getInstance(e.getProject()).createDirectory(virtualFile);
            psiDirectory.add(psiFile);
            //todo 如何生成文件
            //generateXmlFile(filePathAndName, formatedXml);
        }
    }

    /**
     * 生成service的xml文件内容
     *
     * @param interfaceMethodInfoList
     * @throws Exception
     */
    private static void generateService(List<TbspInterfaceMethodInfo> interfaceMethodInfoList, String filePath, AnActionEvent e) throws Exception {
        if (interfaceMethodInfoList != null && interfaceMethodInfoList.size() > 0) {
            String serviceName = interfaceMethodInfoList.get(0).getServiceName().substring(interfaceMethodInfoList.get(0).getServiceName().lastIndexOf(".") + 1);
            String fileName = serviceName + ".service.new.xml";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<service-components>");
            stringBuilder.append("<service-component type=\"").append(interfaceMethodInfoList.get(0).getServiceName()).append("\" bean=\"").append(getServiceImplName(serviceName)).append("\">");
            //stringBuilder.append(System.lineSeparator());
            for (TbspInterfaceMethodInfo tbspInterfaceMethodInfo : interfaceMethodInfoList) {
                stringBuilder.append("<service-method local-name=\"").append(tbspInterfaceMethodInfo.getMethodName()).append("\" service-id=\"")
                        .append(tbspInterfaceMethodInfo.getServiceName()).append(".").append(tbspInterfaceMethodInfo.getMethodName()).append("\" method-name=\"")
                        .append(tbspInterfaceMethodInfo.getMethodName()).append("\" cacheable=\"false\" category=\"").append(tbspInterfaceMethodInfo.getServicePackageName())
                        .append("\">").append("<service-parameters>").append("<service-parameter name=\"req\" type=\"").append(tbspInterfaceMethodInfo.getParamName())
                        .append("\" required=\"false\" is-array=\"false\"/>").append("</service-parameters>").append("<service-result name=\"")
                        .append(tbspInterfaceMethodInfo.getMethodName()).append("Result").append("\" required=\"false\" is-array=\"false\" type=\"")
                        .append(tbspInterfaceMethodInfo.getReturnName()).append("\"/>").append("</service-method>");
            }
            stringBuilder.append("</service-component>").append("</service-components>");
            String formatedXml = formatXml(stringBuilder.toString());
            //System.out.println(formatedXml);
            //创建Xml文件
            XmlFile psiFile = (XmlFile) PsiFileFactory.getInstance(e.getProject()).createFileFromText(fileName, StdFileTypes.XML, formatedXml);
            File ioFile = new File(filePath);
            VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(ioFile);
            //virtualFile.refresh(false, true)

            PsiDirectory psiDirectory = PsiDirectoryFactory.getInstance(e.getProject()).createDirectory(virtualFile);
            psiDirectory.add(psiFile);
            //todo 如何生成文件
            //generateXmlFile(filePathAndName, formatedXml);
        }
    }

    /**
     * xml格式化
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String formatXml(String str) throws Exception {
        Document document = null;
        document = DocumentHelper.parseText(str);
        // 格式化输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //format.setEncoding("gb2312");
        StringWriter writer = new StringWriter();
        // 格式化输出流
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        // 将document写入到输出流
        xmlWriter.write(document);
        xmlWriter.close();
        return writer.toString();
    }

    /**
     * 获取接口实现类名称
     * 接口名 + impl
     *
     * @param serviceName
     * @return
     */
    private static String getServiceImplName(String serviceName) {
        char[] chars = serviceName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars) + "Impl";
    }


    /**
     * 异步写入写出
     */
//    private void createFileInWriteCommandAction() {
//        new WriteCommandAction.Simple(project, mPsiFile) {
//            @Override
//            protected void run() throws Throwable {
//                createFile();
//            }
//        }.execute();
//    }


}
