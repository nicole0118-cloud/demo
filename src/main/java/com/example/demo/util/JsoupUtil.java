package com.example.demo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.IOException;

/**
 * Xss过滤工具
 *
 * @author Nicole
 */
public class JsoupUtil {

    /**
     * 使用自带的basicWithImages 白名单
     * 允许的便签有a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,
     * strike,strong,sub,sup,u,ul,img
     * 以及a标签的href,img标签的src,align,alt,height,width,title属性
     */
    private static final Whitelist WHITELIST = Whitelist.basicWithImages();

    /**
     * 配置过滤化参数,不对代码进行格式化
     */
    private static final Document.OutputSettings OUTPUTTING =
            new Document.OutputSettings().prettyPrint(false);

    /*
     * 富文本编辑时一些样式是使用style来进行实现的
     * 比如红色字体 style="color:red;" 所以需要给所有标签添加style属性
     */
    static {
        WHITELIST.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", WHITELIST, OUTPUTTING);
    }


    public static void main(String[] args) throws IOException {
        String text = "<a href=\"http://www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss";
        System.out.println(clean(text));

        String test = "<a href=\"http://www.baidu.com\" onclick=\"alert(1);\"></a> <body>hello,world</body>\"";
        System.out.println(clean(test));

        System.out.println("++++++++++result1：");
        //只允许p标签存在
        String testHtml = "<div class='div' style='height: 100px;'>div 标签的内容 </div>" +
                "<p class='div' style='width: 50px;'>p 标签的内容 </p>";
        //创建一个白名单
        Whitelist whitelist = new Whitelist();
        //白名单中只允许p标签存在
        whitelist.addTags("p");
        String result1 = Jsoup.clean(testHtml, whitelist);
        System.out.println(clean(result1));

        System.out.println("+++++++++++result2：");
        // p标签中的class属性加入白名单中
        whitelist.addAttributes("p", "class");
        String result2 = Jsoup.clean(testHtml, whitelist);
        System.out.println(result2);

        System.out.println("+++++++++++result3：");
        whitelist.addAttributes(":all", "style", "title", "class");
        whitelist.addTags("div", "h1");
        testHtml = "<h1 onclick='alert(1);'class='' style=''title=''>h1内容</h1>" +
                "<div class=''>div 内容 </div><p class='' style=''>p内容</p>";
        String result3 = Jsoup.clean(testHtml, whitelist);
        System.out.println(result3);
    }
}
