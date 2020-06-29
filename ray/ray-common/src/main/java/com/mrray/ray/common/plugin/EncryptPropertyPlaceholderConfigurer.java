package com.mrray.ray.common.plugin;

import com.mrray.ray.common.AESUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * 配置解密
 *
 * @author lyc
 **/
public class EncryptPropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer implements InitializingBean {

    /**
     * 需要解密的配置项前缀
     */
    private static final String PREFIX_ENC = "enc:";

    @Override
    protected Properties mergeProperties() throws IOException {
        Properties mergedProperties = new Properties();
        for (Properties localProp : localProperties) {
            mergedProperties.putAll(localProp);
        }

        for (Map.Entry entry : mergedProperties.entrySet()) {
            if (entry.getValue().toString().startsWith(PREFIX_ENC)) {
                String key = System.getProperty("enc.key");
                String value = entry.getValue().toString().replace(PREFIX_ENC, StringUtils.EMPTY);
                mergedProperties.setProperty(entry.getKey().toString(), AESUtil.decode(value, key));
            }
        }

        return mergedProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        localOverride = true;
    }
}
