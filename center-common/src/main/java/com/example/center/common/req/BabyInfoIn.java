package com.example.center.common.req;

import com.example.center.common.entity.BabyInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: wwh
 * @Date: 2021/2/19
 * @Description: Valid 嵌套校验
 */
@Getter
@Setter
public class BabyInfoIn {

    @NotBlank(message = "用户名不能为空")
    @Schema(title = "用户名")
    private String userName;

    /**
     * 传该参数才会校验，包含0和50
     */
    @Length(min = 0, max = 50, message = "描叙需在0-50字符之间")
    @Schema(title = "描述")
    private String desc;

    /**
     * 传该参数才会校验，包含0岁，和120岁
     */
    @Min(message = "年龄不能小于0岁", value = 0)
    @Max(message = "年龄不能大于120岁", value = 120)
    @Schema(title = "年龄")
    private Integer age;

    /**
     * 传该参数才会校验，用来校验Array,Collection,Map的大小
     */
    @Size(min = 1, message = "至少有一个爱好")
    @Schema(title = "爱好")
    private List<String> hobbies;

    @Past(message = "创建时间不能晚于现在")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 使用该注解进行嵌套验证
     */
    @Valid
    @Schema(title = "宝宝信息")
    private BabyInfo babyInfo;
}
