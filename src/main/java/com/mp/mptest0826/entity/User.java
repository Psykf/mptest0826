package com.mp.mptest0826.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data //lombok插件生成set和get方法
public class User {


    //@TableId(type = IdType.AUTO) //主键策略，自动增长,可以不写，默认有值
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableLogic //逻辑删除
    private  Integer isDeletes;

    @Version //乐观锁
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    //添加操作时候设置值
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //添加和修改操作设置值
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;




}
