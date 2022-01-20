package cn.i623.myspringutil.service;

import cn.i623.myspringutil.domain.KafkaMsg;
import cn.i623.myspringutil.mapper.KafkaMsgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KafkaMsgService {

    @Resource
    private KafkaMsgMapper kafkaMsgMapper;


    public int deleteByPrimaryKey(Integer id) {
        return kafkaMsgMapper.deleteByPrimaryKey(id);
    }


    public int insert(KafkaMsg record) {
        return kafkaMsgMapper.insert(record);
    }


    public int insertSelective(KafkaMsg record) {
        return kafkaMsgMapper.insertSelective(record);
    }


    public KafkaMsg selectByPrimaryKey(Integer id) {
        return kafkaMsgMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(KafkaMsg record) {
        return kafkaMsgMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(KafkaMsg record) {
        return kafkaMsgMapper.updateByPrimaryKey(record);
    }

}
