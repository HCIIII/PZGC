package net.azurewebsites.pzgccxs.pzgc.utilities;

import net.azurewebsites.pzgccxs.pzgc.models.Article;

import java.io.*;
import java.util.Map;

public class ArticleListGenerator {
    private static String template = "<div class=\"article\">\n"+
            "                    <div class=\"article-left\">\n"+
            "                        <a href=\"&article_html&\"><img src=\"&cover&\"></a>\n"+
            "                    </div>\n"+
            "                    <div class=\"article-right\">\n"+
            "                        <div class=\"article-title\">\n"+
            "                            <a class=\"title\" href=\"&article_html&\">&title&</a>\n"+
            "                            <p>&posted_at&</p>\n"+
            "                        </div>\n"+
            "                        <div class=\"article-text\">\n"+
            "                            <p>&content_preview&</p>\n"+
            "                            <a href=\"&article_html&\"><img src=\"images/more.png\" alt=\"\"/></a>\n"+
            "                            <div class=\"clearfix\"></div>\n"+
            "                        </div>\n"+
            "                    </div>\n"+
            "                    <div class=\"clearfix\"></div>\n"+
            "                </div>";
    private static final String TITLE = "&title&";
    private static final String POSTED_AT = "&posted_at&";
    private static final String CONTENT_PREVIEW = "&content_preview&";
    private static final String COVER = "&cover&";
    private static final String ARTICLE_HTML = "&article_html&";

    public static String generate(Map<String, Article> articles, String templatePath, String path) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(getTemplate(templatePath).replaceAll("&article_list&", getArticleList(articles)));
        bw.flush();
        bw.close();

        return path;
    }

    private static String getTemplate(String templatePath) throws IOException {
        File file = new File(templatePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String template = br.lines().reduce((x, y) -> x + y).orElse("");
        br.close();
        return template;
    }

    private static String getArticleList(Map<String, Article> articles){
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Article> article : articles.entrySet()){
            String articleHtmlPath = "blogs/" + article.getKey();
            sb.append(getArticle(article.getValue(), articleHtmlPath));
        }

        return sb.toString();
    }

    private static String getArticle(Article article, String articleHtmlPath){
        return template
                .replaceAll(TITLE, article.getTitle())
                .replaceAll(POSTED_AT, article.getPostedAt().toString().substring(0, 10))
                .replaceAll(CONTENT_PREVIEW, article.getContent().substring(0, getArticlePreviewLength(article)) + "......")
                .replaceAll(COVER, article.getCover())
                .replaceAll(ARTICLE_HTML, articleHtmlPath);
    }

    private static int getArticlePreviewLength(Article article){
        String title = article.getTitle();
        int titleLength = title.length();

        int res = 0;
        if (titleLength <= 13){
            res = 50;
        }else if (titleLength <= 22){
            res = 30;
        }else {
            res = 16;
        }
        return res;
    }
}
