package com.github.order.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("miaosha_user")
@ApiModel("秒杀用户实体类")
public class MiaoshaUser {

	@ApiModelProperty("主键id")
	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty("用户昵称")
	@TableField(value = "nickname")
	private String nickname;

	@ApiModelProperty("用户昵称")
	@TableField(value = "password")
	private String password;

	@ApiModelProperty("盐")
	@TableField(value = "salt")
	private String salt;

	@ApiModelProperty("头像")
	@TableField(value = "head")
	private String head;

	@ApiModelProperty("注册时间")
	@TableField(value = "register_date")
	private Date registerDate;

	@ApiModelProperty("上次登录时间")
	@TableField(value = "last_login_date")
	private Date lastLoginDate;

	@ApiModelProperty("登录次数")
	@TableField(value = "login_count")
	private Integer loginCount;
}
