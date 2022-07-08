package cn.i623.myspringutil.pdfdeal;


/**
 * PDF 切图 工具类
 * 首先是在home目录下创建一个before目录，把pdf的批量目录文件放入。
 * 其次生成图片会在home目录下生成一个after的目录，根据before下的目录创建对应的目录，
 * 把pdf切出的图片存入对应的目录
 */
public class MulThreadTest {
//    public static final String BASE_PATH = "C:\\Users\\28442\\Documents\\WXWork\\1688851074203100\\Cache\\File\\2022-03\\";
    public static final String BASE_PATH = "C:\\Users\\28442\\Desktop\\";
    public static final String FILE_FULL_NAME = "1.pdf";
//    public static final String FILE_FULL_NAME = "任光新卷宗.pdf";

    public static void main(String[] args) {
        TimeStatic.recordTime(TimeStatic.ProcessNodeEnum.TASK_START);
        String filePath = BASE_PATH + FILE_FULL_NAME;
        String imagePath = filePath.replace(".pdf", "");
        String fileName = FILE_FULL_NAME.replace(".pdf", "");
        ImageUtils.pdf2image(filePath, fileName,  imagePath);
        TimeStatic.recordTime(TimeStatic.ProcessNodeEnum.TASK_END);
        System.out.println(TimeStatic.TIME_RECORD);//xxxxxx
    }
}