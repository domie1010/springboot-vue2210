package com.project.demo.controller;

import com.project.demo.entity.CourierServicesCompany;
import com.project.demo.service.CourierServicesCompanyService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *快递公司：(CourierServicesCompany)表控制层
 *
 */
@RestController
@RequestMapping("/courier_services_company")
public class CourierServicesCompanyController extends BaseController<CourierServicesCompany,CourierServicesCompanyService> {

    /**
     *快递公司对象
     */
    @Autowired
    public CourierServicesCompanyController(CourierServicesCompanyService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
