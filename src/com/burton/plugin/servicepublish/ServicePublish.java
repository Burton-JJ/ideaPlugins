package com.burton.plugin.servicepublish;

import com.burton.plugin.servicepublish.settings.ConfigListenersRegistry;
import com.burton.plugin.servicepublish.settings.ServicePublishConfig;
import org.apache.commons.lang3.StringUtils;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*********************************
 * <p> 文件名称: ServicePublish
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.servicepublish
 * <p> 功能说明: 
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/26
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class ServicePublish implements ConfigListenersRegistry<ServicePublishConfig> {
    private String serviceXmlPath;
    private String serviceWrapperXmlPath;
    private String aa;
    private String commonJarPath;

    public ServicePublish(ServicePublishConfig settings) {
        List<ConfigListenersRegistry> servicePublish = new ArrayList<>();
        servicePublish.add(this);
        //settings.registries(servicePublish);
        init(settings);
    }

    /**
     * 根据配置刷新资源
     *
     * @param settings
     * @return
     */
    @Override
    public String refresh(ServicePublishConfig settings) {
        return init(settings);
    }

    private String init(ServicePublishConfig settings) {
        Map<String, String> config = settings.getConfigResult();
        this.serviceXmlPath = config.get("serviceXmlPath");
        this.serviceWrapperXmlPath = config.get("serviceWrapperXmlPath");
        this.commonJarPath = config.get("commonJarPath");
        return StringUtils.EMPTY;
    }
}
