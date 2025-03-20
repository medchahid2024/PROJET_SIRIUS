package edu.ezip.ing1.pds.business.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Articles {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("articles")
    private Set<Article> Articles = new LinkedHashSet<Article>();

    int count;

    public  Articles(){

    }
    public Set<Article> getArticles() {
        return Articles;
    }

    public void setArticles(Set<Article> Articles) {
        this.Articles = Articles;
    }
    public int getCount (){
        return count;
    }



    public final Articles add (final Article Article) {


        Articles.add(Article);
        return this;
    }
}
