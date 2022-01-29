package cn.i623.myspringutil.mapper;

import cn.i623.myspringutil.domain.XsTemplate;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XsTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XsTemplate record);

    int insertSelective(XsTemplate record);

    XsTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XsTemplate record);

    int updateByPrimaryKey(XsTemplate record);

    List<String> findTemplatePackageByTemplateSn(@Param("templateSn") String templateSn);
}