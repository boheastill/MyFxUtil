package cn.i623.myfxutil.myinterface;

import java.util.List;

public interface IDCardInterface {

    /*校验是否符合身份证规则
     * 输入：长度为18的字符串，非数字的默认按罗马X 处理
     * 输出：符合规则（是/否）
     * */
    Boolean cheakIdCard(String code);

    /*获取随机的示例号码*/
    String getRadomIdCard();

    String getIdCardWithoutVerfy();

    List<String> getInfoByIdCard();

    //静态内部类，构建者
}
