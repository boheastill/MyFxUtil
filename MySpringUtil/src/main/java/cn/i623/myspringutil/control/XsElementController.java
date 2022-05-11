package cn.i623.myspringutil.control;

import cn.i623.myspringutil.domain.XsElement;
import cn.i623.myspringutil.service.XsElementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * (xs_element)表控制层
 *
 * @author xxxxx
 * //    //根据数据库生成脚本
 */
@RestController
@RequestMapping("xs_element")
public class XsElementController {
    /**
     * 服务对象
     */
    @Resource
    private XsElementService xsElementService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public XsElement selectOne(Integer id) {
        return xsElementService.selectByPrimaryKey(id, 0);
    }

    @GetMapping("selectPath")
    public String selectPath() throws IOException {
        return xsElementService.selectPath();
    }

    @GetMapping("updatedb")
    public String updatedb() throws IOException {
        xsElementService.updateElementcodeAndGroupAndParentgroupByTemplateSnAndEleName();
        return "结束";
    }

}
