package tech.codingclub.helix.entity;

public class WikiResult {




    private  String quary;
    private String text_result;
    private  String img_url;
    public WikiResult(String quary, String text_result, String img_url) {
        this.quary = quary;
        this.text_result = text_result;
        this.img_url = img_url;
    }

    public WikiResult() {
    }

    public String getText_result(){
        return text_result;
    }
    public String getQuary() {
        return quary;
    }

    public String getImg_url() {
        return img_url;
    }

}