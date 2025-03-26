package com.project.demo.controller;

import com.project.demo.entity.ExpressLogistics;
import com.project.demo.service.ExpressLogisticsService;
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
 *快递物流：(ExpressLogistics)表控制层
 *
 */
@RestController
@RequestMapping("/express_logistics")
public class ExpressLogisticsController extends BaseController<ExpressLogistics,ExpressLogisticsService> {

    /**
     *快递物流对象
     */
    @Autowired
    public ExpressLogisticsController(ExpressLogisticsService service) {
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
