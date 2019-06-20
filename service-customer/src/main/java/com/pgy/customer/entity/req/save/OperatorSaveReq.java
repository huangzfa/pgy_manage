package com.pgy.customer.entity.req.save;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/15
 */
@Data
public class OperatorSaveReq extends ReqParam {
    @NotBlank(message = "phone不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "phone格式不正确")
    private String loginName;

    @NotBlank(message = "姓名不能为空")
    private String realName;

    @NotNull(message = "有效性不能为空")
    private Integer operatorState;

    private Integer opId;
}
