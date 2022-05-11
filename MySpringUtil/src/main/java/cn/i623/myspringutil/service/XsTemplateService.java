package cn.i623.myspringutil.service;

import cn.i623.myspringutil.domain.XsTemplate;
import cn.i623.myspringutil.mapper.XsTemplateMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XsTemplateService {

    @Resource
    private XsTemplateMapper xsTemplateMapper;


    public int deleteByPrimaryKey(Integer id) {
        return xsTemplateMapper.deleteByPrimaryKey(id);
    }


    public int insert(XsTemplate record) {
        return xsTemplateMapper.insert(record);
    }


    public int insertSelective(XsTemplate record) {
        return xsTemplateMapper.insertSelective(record);
    }


    public XsTemplate selectByPrimaryKey(Integer id) {
        return xsTemplateMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(XsTemplate record) {
        return xsTemplateMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(XsTemplate record) {
        return xsTemplateMapper.updateByPrimaryKey(record);
    }

    public List<String> findTemplatePackageByTemplateSn(String templateSn) {
        return xsTemplateMapper.findTemplatePackageByTemplateSn(templateSn);
    }


    public List<String> selectTemplateSnByNode(String node) {
        return xsTemplateMapper.selectTemplateSnByNode(node);
    }
}


