package com.mrray.ray.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * yml文件帮助类
 *
 * @author lyc
 **/
public class YmlUtils {
    private static String bootstrap_file = "bootstrap.yml";

    private static Map<String, String> result = new HashMap<>();

    /**
     * 根据文件名获取yml的文件内容
     *
     * @return
     */
    public static Map<String, String> getYmlByFileName(String file) {
        result = new HashMap<>(10);
        if (file == null) {
            file = bootstrap_file;
        }
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        InputStream in = patternResolver.getClassLoader().getResourceAsStream(file);
        Yaml props = new Yaml();
        Object obj = props.loadAs(in, Map.class);
        Map<String, Object> param = (Map<String, Object>) obj;

        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            if (val instanceof Map) {
                forEachYaml(key, (Map<String, Object>) val);
            } else {
                result.put(key, val.toString());
            }
        }
        return result;
    }


    /**
     * 遍历yml文件，获取map集合
     *
     * @param key_str
     * @param obj
     * @return
     */
    public static Map<String, String> forEachYaml(String key_str, Map<String, Object> obj) {
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            String str_new = "";
            if (StringUtils.isNotBlank(key_str)) {
                str_new = key_str + "." + key;
            } else {
                str_new = key;
            }
            if (val instanceof Map) {
                forEachYaml(str_new, (Map<String, Object>) val);
            } else {
                result.put(str_new, val.toString());
            }
        }
        return result;
    }
} 
