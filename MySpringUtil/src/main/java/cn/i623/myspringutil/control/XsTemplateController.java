package cn.i623.myspringutil.control;

import cn.i623.myspringutil.domain.XsTemplate;
import cn.i623.myspringutil.service.XsTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (xs_template)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("xs_template")
public class XsTemplateController {
/**
* 服务对象
*/
@Resource
private XsTemplateService xsTemplateService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public XsTemplate selectOne(Integer id) {
return xsTemplateService.selectByPrimaryKey(id);
}

}
