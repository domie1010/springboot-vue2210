package com.project.demo.controller;

import com.project.demo.entity.ApplyForReceipt;
import com.project.demo.service.ApplyForReceiptService;
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
 *申请接单：(ApplyForReceipt)表控制层
 *
 */
@RestController
@RequestMapping("/apply_for_receipt")
public class ApplyForReceiptController extends BaseController<ApplyForReceipt,ApplyForReceiptService> {

    /**
     *申请接单对象
     */
    @Autowired
    public ApplyForReceiptController(ApplyForReceiptService service) {
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
