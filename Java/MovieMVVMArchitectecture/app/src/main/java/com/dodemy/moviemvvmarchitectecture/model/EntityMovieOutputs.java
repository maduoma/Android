package com.dodemy.moviemvvmarchitectecture.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings({"ALL", "unused"})
public class EntityMovieOutputs {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<EntityMovieItem> results = null;
    @SuppressWarnings("unused")
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {this.page = page;}
    public Integer getTotalResults() {return totalResults;}
    public void setTotalResults(Integer totalResults) {this.totalResults = totalResults;}
    public Integer getTotalPages() {return totalPages;}
    public void setTotalPages(Integer totalPages){this.totalPages = totalPages;}
    public List<EntityMovieItem> getResults() { return results;}
    public void setResults(List<EntityMovieItem> results { this.results = results;}
}
