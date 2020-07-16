package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* <p>
    * ${table.comment!} 接口类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin/${table.entityPath}")
public class ${table.controllerName} {
    @Resource
    private ${table.serviceName} service;

    /**
     * 查询全部
     *
     * @return List
     */
    @PostMapping(value = "/selectAll")
    public List<${entity}> selectAll() {
        return service.list();
    }

    /**
     * 分页查询全部
     *
     * @return List
    */
    @PostMapping(value = "/selectPage")
    public List<${entity}> selectPage(@RequestParam(required = false,defaultValue = "1") int page,
                                      @RequestParam(required = false,defaultValue = "10") int limit) {
        Page<${entity}> pages = new Page<>(page,limit);
        service.page(pages).getRecords();
        System.out.println("总页数"+pages.getPages());
        System.out.println("当前页记录数量"+pages.getSize());
        System.out.println("总记录数量"+pages.getTotal());
        return pages.getRecords();
    }

}
