package com.project.demo.controller;

import com.project.demo.entity.ExpressInformation;
import com.project.demo.service.ExpressInformationService;
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
 *快递信息：(ExpressInformation)表控制层
 *
 */
@RestController
@RequestMapping("/express_information")
public class ExpressInformationController extends BaseController<ExpressInformation,ExpressInformationService> {

    /**
     *快递信息对象
     */
    @Autowired
    public ExpressInformationController(ExpressInformationService service) {
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
