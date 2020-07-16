package com.jtexplorer.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtexplorer.entity.Exam;
import com.jtexplorer.service.IExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
* <p>
    *  接口类
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
     * @return List
     */
    @PostMapping(value = "/selectAll")
    public List<Exam> selectAll() {
        return service.list();
    }

    /**
     * 使用条件查询
     *
     * @param session session
     * @return JsonResult
     */
    @PostMapping(value = "/selectAllWithQueryWrapper")
    public List<Exam> selectAllWithQueryWrapper(@RequestParam(required = false) Long id,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String nameLike,
                                                HttpSession session) {
        QueryWrapper<Exam> queryWrapper = new QueryWrapper<>();
//        Map<String, Object> allEq = new HashMap<>();

//        allEq.put("exam_id", id);
//        if (StringUtil.isNotEmpty(name))
//            allEq.put("exam_name", name);
//        // 注意，该语句必须在allEq的赋值语句之后。
//        queryWrapper.allEq(allEq, true);
        // 这里的第一个参数的意思是将allEq（第二个参数）遍历，将不符合StringUtil.isNotEmpty(v)条件的条件剔除
//        queryWrapper.allEq((k, v) ->
//                        StringUtil.isNotEmpty(v)
//                , allEq, true);

        // 相等
        queryWrapper.eq("exam_id", id);
        // 不等于
        queryWrapper.ne("exam_id", id);
        // 大于
        queryWrapper.gt("exam_id", id);
        // 大于等于
        queryWrapper.ge("exam_id", id);
        // 小于
        queryWrapper.lt("exam_id", id);
        // 小于等于
        queryWrapper.le("exam_id", id);
        // between
        queryWrapper.between("exam_id", id, id + 1);
        // not between
        queryWrapper.notBetween("exam_id", id, id + 1);
        // like(会自动在前后增加%)
        queryWrapper.like("exam_name", name);
        // not like(会自动在前后增加%)
        queryWrapper.notLike("exam_name", name);

        // likeLeft(会自动在前增加%)
        queryWrapper.likeLeft("exam_name", name);
        // likeRight(会自动在后增加%)
        queryWrapper.likeRight("exam_name", name);

        // is null
        queryWrapper.isNull("exam_name");
        // is not null(会自动在后增加%)
        queryWrapper.isNotNull("exam_name");
        // in
        queryWrapper.in("exam_name", id, id, id);
        // not in
        queryWrapper.notIn("exam_name", id, id, id);

        // in sql
        queryWrapper.inSql("exam_name", "select 1 from dual");

        // not in sql
        queryWrapper.notInSql("exam_name", "select 1 from dual");

        // 分组
        queryWrapper.groupBy("exam_name", "exam_id");

        // 排序
        queryWrapper.orderBy(true, true, "exam_name", "exam_id");
        // 排序
        queryWrapper.orderByAsc("exam_name", "exam_id");
        // 排序
        queryWrapper.orderByDesc("exam_name", "exam_id");

        // having
        queryWrapper.having("sum(exam_id) > {0} and sum(exam_id) < {1}", id, id);
        queryWrapper.having("sum(exam_id) > 10");

        // or
        queryWrapper.eq("exam_id", 100).or().eq("exam_id", 200);
        // or嵌套
        queryWrapper.or(i -> i.eq("exam_id", 100).eq("exam_id", 100));

        // and嵌套
        queryWrapper.and(i -> i.eq("exam_id", 100).eq("exam_id", 100));

        // 正常嵌套 不带 AND 或者 OR-----nested
        queryWrapper.nested(i -> i.eq("exam_id", 100).eq("exam_id", 100));

        // 拼接 sql
        queryWrapper.apply("date_format(now(),'%Y-%m-%d') = '2008-08-08'");
        queryWrapper.apply("date_format(now(),'%Y-%m-%d') = {0} and date_format(now(),'%Y-%m-%d') = {1} ", "2008-08-10", "2008-08-09");

        // last 无视优化规则直接拼接到 sql 的最后 只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
        queryWrapper.last("limit 10");

        // exists
        queryWrapper.exists("select 1 from dual");

        // not exists
        queryWrapper.notExists("select 1 from dual");

        List<Exam> resultExam = service.list(queryWrapper);

        queryWrapper = new QueryWrapper<>();
        // 设置查出列
        // 第二类方法为:过滤查询字段(主键除外),入参不包含 class 的调用前需要wrapper内的entity属性有值! 这两类方法重复调用以最后一次为准
        queryWrapper.select("exam_id", "exam_name");
        // 莫名出现空指针异常
//        queryWrapper.select(i->i.getProperty().startsWith("exam"));
        resultExam = service.list(queryWrapper);

        // 用于更新的，更新条件的拼接与查询的类相同
        UpdateWrapper<Exam> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("exam_remark", "aaa");
        updateWrapper.setSql("exam_remark = 'bbb'");

        boolean a = service.update(updateWrapper);

        IPage<Exam> iPage = new Page<>(10, 1);
        iPage.setTotal(service.count());
        service.page(iPage, queryWrapper);
        resultExam = iPage.getRecords();
        System.out.println(a);
        return resultExam;
    }

    /**
     * 分页查询全部
     *
     * @return List
    */
    @PostMapping(value = "/selectPage")
    public List<Exam> selectPage(@RequestParam(required = false,defaultValue = "1") int page,
                                      @RequestParam(required = false,defaultValue = "10") int limit) {
        Page<Exam> pages = new Page<>(page,limit);
        service.page(pages).getRecords();
        System.out.println("总页数"+pages.getPages());
        System.out.println("当前页记录数量"+pages.getSize());
        System.out.println("总记录数量"+pages.getTotal());
        return pages.getRecords();
    }

}
