package com.zimu.controller;

import com.zimu.common.exception.ValidationException;
import com.zimu.component.AddressComponent;
import com.zimu.domain.info.AddressInfo;
import com.zimu.domain.info.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@Controller
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private AddressComponent addressComponent;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new Validator() {
            @Override
            public boolean supports(Class<?> clazz) {
                return clazz.equals(FormInput.class);
            }

            @Override
            public void validate(Object target, Errors errors) {

                FormInput formInput = (FormInput) target;
                Integer type = formInput.getType();
                if ((type != null && type != 1) && StringUtils.isBlank(formInput.getCode())) {
                    errors.reject("", "code不能为空！");
                }

            }
        });
    }

    @GetMapping(value = "myTest")
    @ResponseBody
    public JsonView myTest(@Validated FormInput formInput, BindingResult result) {

        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().stream().findFirst().get().getDefaultMessage());
        }

        Integer type = formInput.getType();
        List<AddressInfo> addressInfos = null;
        if (type == null || type == 1) {
            addressInfos = addressComponent.getProvinceList();
        } else if (type == 2) {
            addressInfos = addressComponent.getCityList(formInput.getCode());
        } else if (type == 3) {
            addressInfos = addressComponent.getAreaList(formInput.getCode());
        }


        JsonView jsonView = new JsonView();
        jsonView.setData(addressInfos);
        return jsonView;
    }

    @Getter
    @Setter
    static class FormInput {

        private Integer type;

        private String code;

    }

}
