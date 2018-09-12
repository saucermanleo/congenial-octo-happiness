package com.zy.pmk.util;

import javax.validation.constraints.NotBlank;

public class TestBean {
    @NotBlank(message = "姓名不能为空")
    private String name;
}
