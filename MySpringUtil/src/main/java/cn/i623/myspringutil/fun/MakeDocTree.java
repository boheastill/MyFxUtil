package cn.i623.myspringutil.fun;

import lombok.Data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
一、开始
1.张三xxx
2.李四xxx
二、介绍
(一)产品
(二)研发
(三)项目小组
三、详细
1.科技研发
（一）配件组成
（二）采购规则
2.销售业务
四、结束
（一）供应商
1.a公司
2.b公司
（二）其他伙伴

作用域：当前全文（不应包含父节点）
目标：找全部直接子节点
特征：子节点用同一个编码，即用全文第一个编码类型往下找。
全文放入对象
迭代：
    0.对象状态：空、写入、找出直接子节点、找出全部子节点。
     2.对象

* */
public class MakeDocTree {


    /*
    假设：
     * 1.全文都有等级
     * 2.某等级是标题代表可以继续向下找，是正文代表不能继续向下找，
     * 正文示例：
     * 一、
     *   （一）
     *       1.
     *       2.
     *       3.
     *           （1）
     *           （2）
     *           （3）
     *   （二）
     * 二、
     * 三、
     *
     * 需求：
     * 1.构造一棵树，根节点是文档。
     * */
    public static void main(String[] args) {
        /*传入全文*/
        List<String> partList = new LinkedList<>();
        Collections.addAll(partList, "	一、开始	", "	1.张三xxx	", "	2.李四xxx	", "	二、介绍	", "	(一)产品	", "	(二)研发	", "	(三)项目小组	", "	三、详细	", "	1.科技研发	", "	（一）配件组成	", "	（二）采购规则	", "	2.销售业务	", "	四、结束	", "	（一）供应商	", "	1.a公司	", "	2.b公司	", "	（二）其他伙伴	");

        PartNode partNode = getDocTree(partList);
        System.out.println(partNode);
    }

    public static PartNode getDocTree(List<String> partList) {
        PartNode partNode = eachPart(new PartNode(partList));
        return partNode;
    }


    public static PartNode eachPart(PartNode partNode) {
        Integer level = partNode.getLevel();

        //一律不识别第一行，避免都卡在“一、”
//        System.out.printf(partNode.getPartText());
        List<PartNode> subNode = floatPartNode(partNode).getSubNode();//得到子节点
        if (subNode == null || subNode.size() == 0) {//代表是叶节点
            return partNode;
        } //代表还有子节点，对子节点平铺
        for (PartNode p : subNode) {
            p.setLevel(++level);
            eachPart(p);
        }
        return partNode;
    }

    /*      当前节点：如果有子节点，补充
     *     1.对象全文找第一个特征，此特征找其他子节点，写入当前对象。
     *
     * */
    static PartNode floatPartNode(PartNode part) {
        System.out.println(part);
        List<String> partTextList = part.getPartText();
        int size = partTextList.size();
        LevelSign curUseSign = null;
        List<PartNode> subPartNodeList = new ArrayList<>();
        Integer lastEnd = 0;
        Set<LevelSign> useSet = part.getUseSet();
//        System.out.println("当前对象" + useSet);
        if (useSet == null) {
            useSet = new HashSet<>();
        }
        for (int i = 0; i < size; i++) {
            String partText = partTextList.get(i);
            label:
            if (curUseSign == null) {
                for (LevelSign trySing : LevelSign.values()) {
                    //如果当前类型被用过，不尝试
                    if (useSet.contains(trySing)) {
                        continue;
                    }
                    //尝试
                    List<Map<String, String>> maps = regDeal(partText, trySing.getEnumRegValue());
                    //匹配到了等级
                    if (maps.size() > 0) {
                        curUseSign = trySing;
                        lastEnd = i;
                        //用过的编号雷系写入对象，避免重复用
                        useSet.add(curUseSign);
                        System.out.println("写入" + useSet);
//                        part.setUseSet(useSet);
                        break label;
                    }
                }//todo 如果文本经历所有匹配，都没有值，不应该放入子节点
                lastEnd++;
            } else {
                List<Map<String, String>> maps = regDeal(partText, curUseSign.getEnumRegValue());
                if (maps.size() > 0) {
//                    System.out.println(useSet);
                    subPartNodeList.add(new PartNode(partTextList, lastEnd, i, useSet));
                    lastEnd = i;
                }
            }
        }
        if (lastEnd != size) {
//            System.out.println(useSet);
            subPartNodeList.add(new PartNode(partTextList, lastEnd, size, useSet));
        }
        part.setSubNode(subPartNodeList);
        return part;
    }

    public static List<Map<String, String>> regDeal(String pendingText, Pattern pattern) {
        List<Map<String, String>> regResultMapList = new ArrayList<>();
        int m_idx = 0;

        Matcher matcher = pattern.matcher(pendingText);
        while (matcher.find(m_idx)) {
            Map<String, String> regTextMap = new HashMap<>();

            regTextMap.put("regText", matcher.group());
            String regStart = String.valueOf(matcher.start());
            regTextMap.put("regStart", regStart);
            String regEnd = String.valueOf(matcher.end());
            regTextMap.put("regEnd", regEnd);
//            PageRowUtil.p("regStart",regStart);
//            PageRowUtil.p("regEnd",regEnd);
            regResultMapList.add(regTextMap);
            m_idx = matcher.end() + ((matcher.group().length() == 0) ? 1 : 0);
        }
        return regResultMapList;
    }

    public enum LevelSign {
        ONE(Pattern.compile("[一二三四五六七八九十]+、")),//一、
        TWO(Pattern.compile("[(（][一二三四五六七八九十]{1,3}[)）]")),// （一）
        THREE(Pattern.compile("[0-9]+\\.")),//1.
        FOUR(Pattern.compile("[(（][0-9]{1,3}[)）]"));// （1）

        private final Pattern enumRegValue;

        LevelSign(Pattern enumRegValue) {
            this.enumRegValue = enumRegValue;

        }

        public Pattern getEnumRegValue() {
            return enumRegValue;
        }

    }

    @Data
    static public class PartNode {
        private List<String> partText;//段落列表

        private List<PartNode> subNode;//子节点

        private Integer level;//等级
        private Integer startNo;//段落序号开始
        private Integer endNo;//段落序号结束

        private Set useSet;

        /*注意，会按start end 截取list保存*/
        public PartNode(List<String> partText, Integer startNo, Integer endNo, Set useSet) {
            List<String> objects = new ArrayList<>();
            objects.addAll(partText.subList(startNo, endNo));
//            System.out.println("截取：" + objects.toString());
            this.partText = objects;
            this.startNo = startNo;
            this.endNo = endNo;
//            this.useSet = useSet;
            this.useSet = new HashSet();
            Iterator iterator = useSet.iterator();
            while (iterator.hasNext()) {
                this.useSet.add(iterator.next());
            }
        }

        public PartNode(List<String> partText) {
            this.partText = partText;
            this.level = 1;
            this.startNo = 0;
            this.endNo = partText.size();
        }
    }

}
