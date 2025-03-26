package com.project.demo.controller;

import com.project.demo.entity.UseExpressDelivery;
import com.project.demo.service.UseExpressDeliveryService;
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
 *寄快递：(UseExpressDelivery)表控制层
 *
 */
@RestController
@RequestMapping("/use_express_delivery")
public class UseExpressDeliveryController extends BaseController<UseExpressDelivery,UseExpressDeliveryService> {

    /**
     *寄快递对象
     */
    @Autowired
    public UseExpressDeliveryController(UseExpressDeliveryService service) {
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
