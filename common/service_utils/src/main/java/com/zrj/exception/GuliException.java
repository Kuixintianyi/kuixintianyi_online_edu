package com.zrj.exception;

import com.zrj.result.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: GuliException
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String message;

    public GuliException(ResultCodeEnum fileUploadError) {

    }
}
