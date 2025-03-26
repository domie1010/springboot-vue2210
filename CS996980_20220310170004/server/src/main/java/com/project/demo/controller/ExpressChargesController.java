package com.project.demo.controller;

import com.project.demo.entity.ExpressCharges;
import com.project.demo.service.ExpressChargesService;
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
 *快递收取费用：(ExpressCharges)表控制层
 *
 */
@RestController
@RequestMapping("/express_charges")
public class ExpressChargesController extends BaseController<ExpressCharges,ExpressChargesService> {

    /**
     *快递收取费用对象
     */
    @Autowired
    public ExpressChargesController(ExpressChargesService service) {
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
