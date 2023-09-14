package com.demo.validation.dto;


import com.demo.validation.annotation.ValidString;
import com.demo.validation.common.ValidGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull(groups = {ValidGroup.class}, message = "ID不能为空")
    private Long id;

    @NotBlank(message = "名字为必填项")
    private String username;

    @Email(message = "邮箱格式不正确")
    private String email;

    @ValidString(value = {"男", "女"}, message = "性别只能是男或者女")
    private String sex;


}
