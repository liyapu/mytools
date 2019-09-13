package com.lyp.learn.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-08 14:24
 */

public class DogTest {

    @Test
    public void testTask(){
        Map map = new HashMap<>();
        map.put("code",0);
        map.put("message","");
        Map result =  new HashMap();
        List<TaskVo> taskVos = new ArrayList<>();
        taskVos.add(new TaskVo(1,"幸运福袋","每日12-15点，20-22点\n开启福袋可得5-50g狗粮","http://$$$$.png","12:00 开启","huoguo://browser_home",1,1));
        taskVos.add(new TaskVo(2,"阅读2篇文章","奖励20g狗粮，每天3次","http://read.png","去阅读","huoguo://browser_home",1,1));
        taskVos.add(new TaskVo(3,"观看2个视频","奖励20g狗粮，每天3次","http://video.png","去观看","huoguo://browser_home",1,1));
        taskVos.add(new TaskVo(4,"浏览2次网址","奖励20g狗粮，每天1次","http://eeieie.png","去浏览","huoguo://browser_home",1,1));

        result.put("taskList",taskVos);
        map.put("result",result);

        System.out.println(JSONObject.toJSONString(map));
    }

    @Test
    public void testDynamic(){

        Map map = new HashMap<>();
        map.put("code",0);
        map.put("message","");
        Map result =  new HashMap();
        List<Dynamic> dynamics = new ArrayList<>();
//        dynamics.add(new Dynamic("小狗吃饱了，出去玩了",new Date()));
//        dynamics.add(new Dynamic("小狗寻宝倒计时结束，已经生成奖励了",new Date()));
//        dynamics.add(new Dynamic("小狗寻出宝藏了,来领取吧",new Date()));

        result.put("dynamicList",dynamics);
        map.put("result",result);
        System.out.println(JSONObject.toJSONString(map));
    }

}

class Dynamic{
    private String content;
    private Date createTime;

    public Dynamic(String content, Date createTime) {
        this.content = content;
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

class TaskVo{
    private Integer taskId;
    private String title;
    private String taskDesc;
    private String taskIcon;
    private String buttonDesc;
    private String buttonUrl;
    private Integer buttonType;
    private Integer status;

    public TaskVo(Integer taskId, String title, String taskDesc, String taskIcon, String buttonDesc, String buttonUrl, Integer buttonType,Integer status) {
        this.taskId = taskId;
        this.title = title;
        this.taskDesc = taskDesc;
        this.taskIcon = taskIcon;
        this.buttonDesc = buttonDesc;
        this.buttonUrl = buttonUrl;
        this.buttonType = buttonType;
        this.status = status;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(String taskIcon) {
        this.taskIcon = taskIcon;
    }

    public String getButtonDesc() {
        return buttonDesc;
    }

    public void setButtonDesc(String buttonDesc) {
        this.buttonDesc = buttonDesc;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    public Integer getButtonType() {
        return buttonType;
    }

    public void setButtonType(Integer buttonType) {
        this.buttonType = buttonType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

