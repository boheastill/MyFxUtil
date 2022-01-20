package cn.i623.myspringutil.control;

import cn.i623.myspringutil.domain.KafkaMsg;
import cn.i623.myspringutil.service.FileNodeUtil;
import cn.i623.myspringutil.service.KafkaMsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (kafuka_msg)表控制层
 *
 * @author xxxxx
 */
@RestController
//@RequestMapping("kafuka_msg")
public class KafkaMsgController {
    /**
     * 服务对象
     */
    @Resource
    private KafkaMsgService kafkaMsgService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public KafkaMsg selectOne(Integer id) {
        return kafkaMsgService.selectByPrimaryKey(id);
    }

    /**
     * 插入kafka消息
     *
     * @param jd   节点编码
     * @param file 文件名称，不含后缀
     * @return 单条数据
     */
    @GetMapping("send")
    public String insertOne(String jd, String file) {
        System.out.println(jd);
        System.out.println(file);
        String kafkaVoStr = FileNodeUtil.getKafkaVoStr(jd, file);
        KafkaMsg record = new KafkaMsg();
        record.setMsg(kafkaVoStr);
        record.setSendTime(new Date());
        record.setMsgId(Long.valueOf(((int) (Math.random() * 10000000))));
        record.setStatus(1);
        record.setIsDel(0);
        record.setCreatedTime(new Date());
        record.setTopicName("ZJ_OCR_OPEN_PLATFORM");
        int insert = kafkaMsgService.insert(record);
        return String.valueOf(insert);
    }

}
