package com.pgy.common.utils;

import com.pgy.common.enums.RandomTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 图片工具类
 *
 * @author: 德才
 * @date:2019/3/02 19:50
 */
public class ImageUtil {

    protected final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
    static {
        // images
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");
        //
        mFileTypes.put("41433130", "dwg"); // CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); // 日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B0304", "docx");
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
    }

    /**
     * 把原图片压缩成w*h的图片
     *
     * @param is 原始图片流
     * @param new_w 需要压缩到的宽度
     * @param new_h 需要压缩到的高度
     * @param per  百分比
     * @return
     */

    /**
     * 生成随机验证码图片
     */
    public static Map<String, BufferedImage> createRandomImage(RandomTypeEnum randomTypeEnum) {
        Map<String, BufferedImage> result = new HashMap<>();
        int width = 258;        // 图片的width
        int height = 100;       // 图片的height
        int codeCount = 4;      // 图片上显示验证码的个数
        int fontHeight = 50;
        int xx = 45;
        int codeY = 60;
        int bound;

        char[] codeSequence;
        switch (randomTypeEnum) {
            case CHAR:
                codeSequence = new char[]
                        {
                                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n',
                                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
                        };
                bound = 50;
                break;
            case NUMBER:
                codeSequence = new char[]
                        {
                                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                        };
                bound = 10;
                break;
            default:
                codeSequence = new char[]
                        {
                                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                                'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                                'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't',
                                'u', 'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6',
                                '7', '8', '9'
                        };
                bound = 58;
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = bufferedImage.getGraphics();
        //随机数生成器类
        Random random = new Random();
        //将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        //设置字体
        Font font = new Font("Default", Font.BOLD, fontHeight);
        gd.setFont(font);

        //随机产生30条干扰线
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        //randomCode用于保存随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        int red, green, blue;
        for (int i = 0; i < codeCount; i++) {
            //得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(bound)]);
            //产生随机的颜色分量来构造颜色值
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            //用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);
            //将产生的随机数组合在一起。
            randomCode.append(code);
        }
        result.put(randomCode.toString(), bufferedImage);
        return result;
    }

    public static String cacheImg(String randomType) throws IOException {
        RandomTypeEnum rType = RandomTypeEnum.getOf(randomType);
        // 生成验证码
        Map<String, BufferedImage> map = ImageUtil.createRandomImage(rType);
        if (1 == map.size()) {
            Map.Entry<String, BufferedImage> entry = map.entrySet().iterator().next();
            if (StringUtils.isNotBlank(entry.getKey())) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(entry.getValue(), "jpeg", byteArrayOutputStream);
                String image = Base64.encodeBase64String(byteArrayOutputStream.toByteArray());
                return image;
            }
        }
        return "";
    }

    /**
     * 绘制颜色
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > 255) {
            f = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f),
                f + random.nextInt(b - f));
    }

    /**
     * 绘制背景图
     * @param g
     */
    public static void createBackground(Graphics g,int w,int h) {
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, w, h);

        for (int i = 0; i < 8; i++) {
            g.setColor(getRandColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int x1 = random.nextInt(w);
            int y1 = random.nextInt(h);
            g.drawLine(x, y, x1, y1);
        }
    }

    /**
     * 绘制字符串
     * @param g
     * @return
     */
    public static  String createCharacter(Graphics g) {
        char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
                'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
        String[] fontTypes = { "Arial", "Arial Black", "AvantGarde Bk BT",
                "Calibri" };
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);
            g.setColor(new Color(50 + random.nextInt(100), 50 + random
                    .nextInt(100), 50 + random.nextInt(100)));
            g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], 1,
                    26));
            System.out.print("r:"+r+" i:"+i);
            g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));

            s.append(r);
        }
        return s.toString();
    }
}
