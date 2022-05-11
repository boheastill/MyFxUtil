package cn.i623.myspringutil.mapper;

import cn.i623.myspringutil.domain.XsElement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XsElementMapper {
    int deleteByPrimaryKey(@Param("eleSn") Integer eleSn, @Param("isDel") Integer isDel);

    int insert(XsElement record);

    int insertSelective(XsElement record);

    XsElement selectByPrimaryKey(@Param("eleSn") Integer eleSn, @Param("isDel") Integer isDel);

    int updateByPrimaryKeySelective(XsElement record);

    int updateByPrimaryKey(XsElement record);

    List<Integer> selectEleSn();

    int updateElementcodeAndGroupAndParentgroupByTemplateSnAndEleName(@Param("updatedElementcode") String updatedElementcode
            , @Param("updatedGroup") String updatedGroup, @Param("updatedParentgroup") String updatedParentgroup
            , @Param("templateSn") String templateSn, @Param("eleName") String eleName);
}