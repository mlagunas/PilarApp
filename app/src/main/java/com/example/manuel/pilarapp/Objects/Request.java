package com.example.manuel.pilarapp.Objects;

import com.example.manuel.pilarapp.Objects.Acto;

import java.util.List;

/**
 * Created by Manuel on 19/09/2015.
 */
public class Request implements java.io.Serializable{

    private Integer totalCount;
    private Integer start;
    private Integer rows;
    private List<Acto> result;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Acto> getResult() {
        return result;
    }

    public void setResult(List<Acto> result) {
        this.result = result;
    }

    public Request(Integer totalCount, int start, int rows, List<Acto> result) {
        this.totalCount = totalCount;
        this.start = start;
        this.rows = rows;
        this.result = result;
    }



}
