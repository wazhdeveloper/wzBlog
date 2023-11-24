package com.wz.blogcommon.bean;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.wz.blogcommon.utils.DateUtil;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@SuppressWarnings("serial")
@TableName("wz_category")
@EqualsAndHashCode
public class Category implements Serializable {
    private static final long serialVersionUID = -6842141270754667710L;
    @TableId
    @ExcelIgnore
    private Long id;
    //分类名
    @ExcelProperty("分类名")
    private String name;
    //父分类id，如果没有父分类为-1
    @ExcelIgnore
    private Long pid;
    //描述
    @ExcelProperty("描述")
    private String description;
    //状态0:正常,1禁用
    @ExcelProperty("状态: 0正常,1禁用")
    private String status;

    @TableField(fill = FieldFill.INSERT)
    @ExcelIgnore
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    @ExcelIgnore
    private Date createTime = DateUtil.getNow();

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelIgnore
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelIgnore
    private Date updateTime = DateUtil.getNow();
    //删除标志（0代表未删除，1代表已删除）
    @TableLogic(value = "0", delval = "1")
    @ExcelIgnore
    private Integer delFlag = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}

