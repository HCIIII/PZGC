package net.azurewebsites.pzgccxs.pzgc.utilities;

import net.azurewebsites.pzgccxs.pzgc.models.Article;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class BlogGenerator {

    private static String BLOG_TEMPLATE_PATH = "C:\\Users\\LZH\\OneDrive - smail.nju.edu.cn\\Projects\\IdeaProjects\\PZGC-master\\src\\main\\resources\\blogs\\blog_template.html";
    private static final String TITLE = "&title&";
    private static final String AUTHOR = "&author&";
    private static final String POSTED_AT = "&posted_at&";
    private static final String CONTENT = "&content&";
    private static final String COVER = "&cover&";
    private static final String ARTICLE_HTML = "&article_html&";
    private static String blogTemplate;

    public static String generate(Article article, String folderPath) throws IOException {
        String filename = getArticleStoredFilename(article);
        String filePath = folderPath + filename;

        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(getBlog(getBlogTemplate(), article));
        bw.flush();
        bw.close();

        return filePath;
    }

    public static String generate(Article article, String templatePath, String folderPath) throws IOException {
        BLOG_TEMPLATE_PATH = templatePath;
        return generate(article, folderPath);
    }

    private static String getBlogTemplate() throws IOException {
        if (null == blogTemplate){
            File file = new File(BLOG_TEMPLATE_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            blogTemplate = br.lines().reduce((x, y) -> x + y).orElse("");
            br.close();
        }
        return blogTemplate;
    }

    private static String getBlog(String blogTemplate, Article article){
        return blogTemplate
                .replaceAll(TITLE, article.getTitle())
                .replaceAll(AUTHOR, article.getAuthor())
                .replaceAll(POSTED_AT, article.getPostedAt().toString().substring(0, 10))
                .replaceAll(CONTENT, getDecoratedArticleContent(article))
                .replaceAll(COVER, article.getCover())
                .replaceAll(ARTICLE_HTML, getArticleStoredFilename(article));
    }

    private static String getDecoratedArticleContent(Article article){
        StringBuilder sb = new StringBuilder();

        String content = article.getContent();
        String[] paragraphs = content.split("\n");
        for (String paragraph : paragraphs) {
            if (!StringUtils.isBlank(paragraph)){
                sb.append("<p class=\"entry-text\">");
                sb.append(paragraph);
                sb.append("</p>");
            }
        }

        return sb.toString();
    }

    public static String getArticleStoredFilename(Article article){
        String title = article.getTitle();
        return "blog_" + title.replace("|", "") + ".html";
    }

}
