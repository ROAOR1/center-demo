package com.example.center.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.center.common.annotation.Create;
import com.example.center.common.annotation.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 宝宝信息表
 *
 * @author wwh
 * @since 1.0 2021-02-04
 */
@Getter
@Setter
public class BabyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(title = "")
    private Long id;

    @Schema(title = "用户uid")
    private String uid;

    @Schema(title = "昵称")
    @NotBlank(message = "昵称不能为空", groups = Update.class)
    private String nickName;

    @Schema(title = "出生日期")
    private LocalDate birthday;

    @Schema(title = "预产期")
    private LocalDate expectedDay;

    @Schema(title = "1：未出生 2：出生")
    @NotNull(message = "状态不能为空", groups = Create.class)
    private Integer status;

    @Schema(title = "1：选中（首页显示） 2：未选中")
    @NotNull(message = "是否主宝宝不能为空")
    private Integer isPrimary;

    @Schema(title = "0：未知 1：男 2：女")
    @NotNull(message = "性别不能为空", groups = Create.class)
    private Integer gender;

    @Schema(title = "宝宝头像")
    private String photoThumb;

    @Schema(title = "出生方式 1：顺产 2：剖宫")
    private Integer bornType;

    @Schema(title = "出生孕周 天数")
    private Integer gestationalWeek;

    @Schema(title = "是否删除：0，否；1，是")
    private Integer isDeleted;

    @Schema(title = "创建用户ID")
    private String createUserId;

    @Schema(title = "修改用户ID")
    private String modifyUserId;

    @Schema(title = "")
    private LocalDateTime createTime;

    @Schema(title = "")
    private LocalDateTime modifyTime;
}
