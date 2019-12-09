package com.execel.demo.excelinterface;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    /**
     * Excel列名
     * @author YZ
     * @date 2019/12/9 15:03
     * @param
     * @return java.lang.String
     **/
    String value() default "";

    /**
     * Excel从左往右排列序号,从1开始
     * @author YZ
     * @date 2019/12/9 15:02
     * @param
     * @return int
     **/
    int col() default 0;
}
