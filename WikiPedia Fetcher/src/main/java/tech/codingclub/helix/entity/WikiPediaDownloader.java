package tech.codingclub.helix.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.helix.global.HttpURLConnectionExample;

public class WikiPediaDownloader {

    private String keyword;


    public WikiPediaDownloader(String keyword) {
        this.keyword = keyword;
    }

    public WikiResult getResult() {
        //Get clean keyword
        //get the url for wikipedia
        //make a get request to url
        //parse the wikipedia response using jsoup
        //showing result
        if(this.keyword==null || this.keyword.length()==0)return null;
        //step1
        this.keyword=this.keyword.trim().replaceAll("[ ]+","_");
        //step2
        String wikiUrl=getWikiUrlForQuery(this.keyword);
        String response="";
        String imageurl = null;
        try {
            //step3
            String wikiResponse= HttpURLConnectionExample.sendGet(wikiUrl);
            //System.out.println(wikiResponse);
            Document document= Jsoup.parse(wikiResponse,"https://en.wikipedia.org");
            Elements childelement=document.body().select(".mw-parser-output > *");
            int state=0;
            for(Element child:childelement){
                if(state==0){
                    if(child.tagName().equals("table")){
                        state=1;
                    }

                }
                else if(state==1){
                    if(child.tagName().equals("p")){
                        state=2;
                        response=child.text();
                        break;
                    }

                }

            }
            try{
                imageurl=document.body().select(".infobox img").get(0).attr("src");
            }
            catch(Exception e){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        WikiResult wikiResult=new WikiResult(this.keyword,response,imageurl);
//        Gson gson=new GsonBuilder().setPrettyPrinting().create();
//        String json=gson.toJson(wikiResult);
//        return json;
        return wikiResult;

    }

    private String getWikiUrlForQuery(String keyword) {
        return "https://en.wikipedia.org/wiki/"+keyword;
    }
}
