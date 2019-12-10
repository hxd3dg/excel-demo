package com.execel.demo.excelcontroller;

import com.alibaba.fastjson.JSON;
import com.execel.demo.excelbean.BusClick;
import com.execel.demo.excelutil.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * excel导入导入导出操作
 * @author YZ
 * @date 2019/12/9 15:01
 **/
@RestController
public class ExcelController {

    /**
     * demo
     * @author YZ
     * @date 2019/12/9 14:24
     * @param
     * @return java.lang.Object
     **/
    @RequestMapping("demo")
    public Object demo(){
        return new String("demo");
    }

    /**
     * 导出excel文件
     * @author YZ
     * @date 2019/12/9 14:24
     * @param request
     * @param response
     * @return void
     **/
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)  throws IOException {

        List<BusClick> resultList = new ArrayList<BusClick>();
        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, resultList, BusClick.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
        System.out.println("execel文件导出成功");
        resultList.forEach(
                a-> System.out.println(JSON.toJSONString(a))
        );
    }

    /**
     * 导入execel文件
     * @author YZ
     * @date 2019/12/9 14:25
     * @param file
     * @return void
     **/
    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
    public void readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file){
        long t1 = System.currentTimeMillis();
        List<BusClick> list = ExcelUtils.readExcel("", BusClick.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        System.out.println("execel导入成功");
        list.forEach(
                b -> System.out.println(JSON.toJSONString(b))
        );
    }

    /**
     * 导出excel模板文件
     * @author YZ
     * @date 2019/12/10 10:43
     * @param request
     * @param response
     * @return void
     **/
    @RequestMapping(value = "/downloadExcelTemplate", method = RequestMethod.GET)
    public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) {
        ExcelUtils.writeExcel(response,null , BusClick.class);
    }
}
