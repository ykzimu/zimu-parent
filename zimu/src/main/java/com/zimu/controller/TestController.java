package com.zimu.controller;

import com.zimu.common.utils.LoginUserUtils;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(value = "test")
public class TestController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new Validator() {
            @Override
            public boolean supports(Class<?> clazz) {
                return clazz.equals(FormInput.class);
            }

            @Override
            public void validate(Object target, Errors errors) {
                errors.reject("x", "xxxxxxxxxxxxx");
            }
        });
    }

    @GetMapping(value = "myTest")
    @ResponseBody
    public JsonView myTest(@Validated FormInput formInput, BindingResult result) {

        JsonView jsonView = new JsonView();
        UserInfo userInfo = LoginUserUtils.getUserInfo();
        jsonView.setData(result.getAllErrors());
        return jsonView;
    }

    static class FormHeader {

    }

    @Getter
    @Setter
    static class FormInput {

        @NotNull
        private Long id;

        @NotEmpty
        @Length(min = 6, max = 16, message = "个数必须在{min}和{max}之间")
        private String username;

        @NotEmpty
        @Email
        private String email;

        @NotEmpty
        @Length(min = 11, max = 11, message = "{I0005}")
        private String mobile;

    }

}
