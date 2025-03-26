package com.project.demo.controller;

import com.project.demo.entity.ExpressEvaluation;
import com.project.demo.service.ExpressEvaluationService;
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
 *快递评价：(ExpressEvaluation)表控制层
 *
 */
@RestController
@RequestMapping("/express_evaluation")
public class ExpressEvaluationController extends BaseController<ExpressEvaluation,ExpressEvaluationService> {

    /**
     *快递评价对象
     */
    @Autowired
    public ExpressEvaluationController(ExpressEvaluationService service) {
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
