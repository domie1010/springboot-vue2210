package com.project.demo.controller;

import com.project.demo.entity.ExpressPickUp;
import com.project.demo.service.ExpressPickUpService;
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
 *快递取件：(ExpressPickUp)表控制层
 *
 */
@RestController
@RequestMapping("/express_pick_up")
public class ExpressPickUpController extends BaseController<ExpressPickUp,ExpressPickUpService> {

    /**
     *快递取件对象
     */
    @Autowired
    public ExpressPickUpController(ExpressPickUpService service) {
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
