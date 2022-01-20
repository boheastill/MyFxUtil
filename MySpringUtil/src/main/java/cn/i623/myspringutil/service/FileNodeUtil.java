package cn.i623.myspringutil.service;

import com.google.gson.Gson;
import lombok.Data;

import java.util.List;

public class FileNodeUtil {


    public static String getKafkaVoStr(String jdbh, String fileName) {
        String s = "{\"taskId\":\"\",\"ignoreQualityFail\":true,\"lcjdbh\":\"\",\"sjbs\":\"图灵天津test\",\"fileList\":[{\"id\":0,\"fileName\":\"\",\"fileUrl\":\"/home/tlrobot/tj/test/\",\"sort\":0,\"fileType\":\"pdf\",\"dossierType\":1}],\"action\":[{\"actionType\":\"Quality\"},{\"actionType\":\"DoublePdf\"},{\"actionType\":\"Element\"},{\"actionType\":\"Catalogue\"}]}";
        Gson gson = new Gson();
        KafkaVo kafkaVo = gson.fromJson(s, KafkaVo.class);
//        System.out.println(kafkaVo.toString());
        kafkaVo.setTaskId(String.valueOf(((int) (Math.random() * 10000000))));
        kafkaVo.setLcjdbh(jdbh);
        kafkaVo.getFileList().get(0).setFileName(fileName);
        kafkaVo.getFileList().get(0).setFileUrl("/home/tlrobot/tj/test/" +
                fileName +
                ".pdf");
        String kafkaVoStr = gson.toJson(kafkaVo);
        return kafkaVoStr;
    }

    @Data
    class KafkaVo {
        String taskId;
        Boolean ignoreQualityFail;
        String lcjdbh;
        String sjbs;
        List<fileList> fileList;
        List<Action> action;

        @Data
        private class fileList {
            Integer id;
            String fileName;
            String fileUrl;
            Integer sort;
            String fileType;
            Integer dossierType;
        }

        private class Action {
            String actionType;
        }
    }
}
