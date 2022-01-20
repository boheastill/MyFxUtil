package cn.i623.myspringutil.mapper;

import cn.i623.myspringutil.domain.KafkaMsg;

public interface KafkaMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KafkaMsg record);

    int insertSelective(KafkaMsg record);

    KafkaMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KafkaMsg record);

    int updateByPrimaryKey(KafkaMsg record);
}