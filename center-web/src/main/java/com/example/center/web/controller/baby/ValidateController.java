package com.example.center.web.controller.baby;

import com.example.center.api.baby.BabyService;
import com.example.center.common.annotation.Create;
import com.example.center.common.entity.BabyInfo;
import com.example.center.common.req.BabyInfoIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: wwh
 * @Date: 2021/2/18
 * @Description: 参数校验
 */
@Validated
@RestController
@RequestMapping("/validate")
public class ValidateController {

    @GetMapping("/run")
    public void run(@NotBlank(message = "body不能为空") String body){
        System.out.println(body);
    }


    @Autowired
    private BabyService babyService;

    /**
     * get请求，触发ConstraintViolationException异常
     * @param status
     * @param gender
     * @return
     */
    @GetMapping("/listBabyByStatus")
    public List<BabyInfo> listBabyByStatus(@NotNull(message = "状态不能为空") Integer status,
                                           @NotNull(message = "性别不能为空") Integer gender){
        return babyService.listBabyByStatus(status);
    }

    /**
     * form-data格式请求，触发BindException异常
     * @param babyInfo
     */
    @PostMapping("/save2")
    public void saveBaby2(@Validated(Create.class) BabyInfo babyInfo){
        babyService.saveBaby(babyInfo);
    }

    /**
     * json格式请求，触发MethodArgumentNotValidException异常
     * @param babyInfo
     */
    @PostMapping("/save")
    public void saveBaby(@RequestBody @Validated(Create.class) BabyInfo babyInfo){
        babyService.saveBaby(babyInfo);
    }

    /**
     * 嵌套验证
     * @param babyInfoIn
     * @return
     */
    @GetMapping("/getBaby")
    public BabyInfo getBaby(@Validated BabyInfoIn babyInfoIn){
        return babyService.getBabyById(babyInfoIn.getBabyInfo().getId());
    }

    @GetMapping("/getBaby2")
    public BabyInfo getBaby2(BabyInfoIn babyInfoIn){
        return babyService.getBabyById(4L);
    }

    /**
     * 方法验证模式，@Validated需要打在类上
     * @param id
     * @return
     */
    @GetMapping("/getBaby3")
    public BabyInfo getBaby3(@NotNull(message = "id不能为空") Long id){
        return babyService.getBabyById(id);
    }
}
