package com.burton.plugin.servicepublish.settings.ui;

import com.burton.plugin.servicepublish.settings.ServicePublishConfig;

import javax.swing.*;
import java.util.Map;

/*********************************
 * <p> 文件名称: ServicePublishConfigForm
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.servicepublish.settings
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/26
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class ServicePublishConfigForm {
    /**
     * 主面板
     */
    private JPanel mainPane;
    private JTextField serviceXmlPath;
    private JTextField serviceWrapperXmlPath;
    private JTextField commonJarPath;


    public ServicePublishConfigForm() {
        ServicePublishConfig settings = ServicePublishConfig.getInstance();
        Map<String,String> configResult = settings.getConfigResult();
        initInput(configResult);
    }

    private void initInput(Map<String, String> configResult) {
        serviceXmlPath.setText(configResult.get("serviceXmlPath"));
        serviceWrapperXmlPath.setText(configResult.get("serviceWrapperXmlPath"));
        commonJarPath.setText(configResult.get("commonJarPath"));
    }

    public JPanel getMainPane() {
        return mainPane;
    }

    public void setMainPane(JPanel mainPane) {
        this.mainPane = mainPane;
    }

    public JTextField getServiceXmlPath() {
        return serviceXmlPath;
    }

    public void setServiceXmlPath(JTextField serviceXmlPath) {
        this.serviceXmlPath = serviceXmlPath;
    }

    public JTextField getServiceWrapperXmlPath() {
        return serviceWrapperXmlPath;
    }

    public void setServiceWrapperXmlPath(JTextField serviceWrapperXmlPath) {
        this.serviceWrapperXmlPath = serviceWrapperXmlPath;
    }

    public JTextField getCommonJarPath() {
        return commonJarPath;
    }

    public void setCommonJarPath(JTextField commonJarPath) {
        this.commonJarPath = commonJarPath;
    }
}
