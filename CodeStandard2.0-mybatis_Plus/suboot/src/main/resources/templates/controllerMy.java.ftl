package ${package.Controller};

import ${package.Entity}.${entity};
import com.jtexplorer.entity.enums.RequestEnum;
import ${package.Service}.${table.serviceName};
import com.jtexplorer.util.ListUtil;
import com.jtexplorer.util.JsonResult;
import com.jtexplorer.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
     * @param session session
     * @return JsonResult
     */
    @PostMapping(value = "/selectAll")
    public JsonResult selectAll(HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        if (!SessionUtil.verifyAdminLogin(session, jsonResult)) {
            return jsonResult;
        }
        List<${entity}> result${entity} = service.list();
        if(ListUtil.isNotEmpty(result${entity})){
            jsonResult.buildTrueNew(service.count(),result${entity});
        }else{
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }

}
