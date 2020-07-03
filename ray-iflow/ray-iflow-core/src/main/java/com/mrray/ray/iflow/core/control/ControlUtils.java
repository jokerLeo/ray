package com.mrray.ray.iflow.core.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mrray.ray.common.validation.ValidateUtils;
import com.mrray.ray.iflow.core.model.AssessProject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * 控件参数帮助类
 *
 * @author lyc
 * @create 2019-12-19 10:14
 **/
public final class ControlUtils {

    /**
     * 获取前端传递的评估参数
     *
     * @param assessProject
     * @return
     */
    public static <T> T getArgs(AssessProject assessProject, Class<T> clazz) {
        T confirmDto = getConfirmArgs(assessProject, clazz);
        T stopDto = getStopArgs(assessProject, clazz);

        return confirmDto != null ? confirmDto : stopDto;
    }

    /**
     * 获取确定按钮提交的参数
     *
     * @param assessProject
     * @return
     */
    public static <T> T getConfirmArgs(AssessProject assessProject, Class<T> clazz) {
        T args = getDtoByKey(assessProject, ControlConstant.CONFIRM, clazz);
        ValidateUtils.validate(args);
        return args;
    }

    /**
     * 获取拒绝按钮提交的参数
     *
     * @param assessProject
     * @return
     */
    public static <T> T getStopArgs(AssessProject assessProject, Class<T> clazz) {
        T args = getDtoByKey(assessProject, ControlConstant.STOP, clazz);
        ValidateUtils.validate(args);
        return args;
    }

    /**
     * 通过key值获取传递的dto对象
     *
     * @param assessProject
     * @param key
     * @return
     */
    private static <T> T getDtoByKey(AssessProject assessProject, String key, Class<T> clazz) {
        if (StringUtils.isEmpty(key) || null == assessProject || CollectionUtils.isEmpty(assessProject.getButtonControls())) {
            return null;
        }

        Optional<ButtonControl> optional = assessProject.getButtonControls().stream().filter(s ->
                key.equals(s.getKey())
        ).findFirst();

        if (optional.isPresent() && null != optional.get().getArgs()) {
            return JSON.parseObject(JSONObject.toJSONString(optional.get().getArgs()), clazz);
        }

        return null;
    }


    /**
     * 点击确定按钮
     *
     * @param assessProject
     * @return
     */
    public static boolean confirm(AssessProject assessProject) {
        return btnSubmit(assessProject, ControlConstant.CONFIRM);
    }

    /**
     * 点击拒绝按钮
     *
     * @param assessProject
     * @return
     */
    public static boolean stop(AssessProject assessProject) {
        return btnSubmit(assessProject, ControlConstant.STOP);
    }

    /**
     * 点击取消按钮
     *
     * @param assessProject
     * @return
     */
    public static boolean cancel(AssessProject assessProject) {
        return btnSubmit(assessProject, ControlConstant.CANCEL);
    }

    /**
     * 按钮提交
     *
     * @param assessProject
     * @param btnKey
     * @return
     */
    private static boolean btnSubmit(AssessProject assessProject, String btnKey) {
        if (StringUtils.isEmpty(btnKey) || CollectionUtils.isEmpty(assessProject.getButtonControls())) {
            return false;
        }

        Optional<ButtonControl> optional = assessProject.getButtonControls().stream().filter(s ->
                btnKey.equals(s.getKey())
        ).findFirst();

        return optional.isPresent() && optional.get().getValue().booleanValue();
    }

    /**
     * 获取自定义按钮
     *
     * @param name
     * @param key
     * @return
     */
    private static ButtonControl baseBtn(String name, String key) {
        return new ButtonControl(name, key);
    }

    /**
     * 获取确定按钮
     *
     * @param name
     * @return
     */
    public static ButtonControl rootBtn(String name) {
        return new ButtonControl(name, ControlConstant.ROOT);
    }

    /**
     * 获取确定按钮
     *
     * @param name
     * @return
     */
    public static ButtonControl confirmBtn(String name) {
        return new ButtonControl(name, ControlConstant.CONFIRM);
    }

    /**
     * 默认的取消按钮
     *
     * @return
     */
    private static ButtonControl cancelBtn() {
        return new ButtonControl("取消", ControlConstant.CANCEL);
    }

    /**
     * 获取拒绝按钮
     *
     * @param name
     * @return
     */
    public static ButtonControl stopBtn(String name) {
        return new ButtonControl(name, ControlConstant.STOP);
    }

    /**
     * 获取拒绝根按钮
     *
     * @param name
     * @return
     */
    private static ButtonControl stopRootBtn(String name) {
        return new ButtonControl(name, ControlConstant.STOP_ROOT);
    }

    /**
     * 默认的提交按钮组
     *
     * @return
     */
    public static ButtonControl commitGroupBtn(String confirmKey) {
        ButtonControl rootSubmitBtn = ControlUtils.baseBtn("提交", confirmKey);
        ButtonControl submitBtn = ControlUtils.confirmBtn("确认提交");
        rootSubmitBtn.setChildren(Arrays.asList(submitBtn, ControlUtils.cancelBtn()));

        return rootSubmitBtn;
    }

    /**
     * 默认的拒绝按钮组，包含两层拒绝按钮和拒绝原因参数
     *
     * @return
     */
    public static ButtonControl stopGroupBtn() {
        ButtonControl rootRefuseBtn = ControlUtils.stopRootBtn("拒绝");
        ButtonControl refuseBtn = ControlUtils.stopBtn("确认拒绝");
        rootRefuseBtn.setChildren(Arrays.asList(refuseBtn, ControlUtils.cancelBtn()));

        return rootRefuseBtn;
    }
} 
