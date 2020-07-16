package com.sukongcheng.suboot.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sukongcheng.suboot.entity.Exam;
import com.sukongcheng.suboot.service.IExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 接口类
 * </p>
 *
 * @author 苏空城
 * @since 2020-07-16
 */
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin/exam")
public class AdminExamController {
    @Resource
    private IExamService service;

    /**
     * 查询全部
     *
     * @return JsonResult
     */
    @PostMapping(value = "/selectAll")
    public List<Exam> selectAll() {

        List<Exam> resultExam = service.list();

        return resultExam;
    }

    /**
     * 查询全部
     *
     * @return JsonResult
     */
    @PostMapping(value = "/selectPage")
    public List<Exam> selectPage() {

        Page<Exam> page = new Page<>(1,2);
        return service.page(page).getRecords();
    }

}
