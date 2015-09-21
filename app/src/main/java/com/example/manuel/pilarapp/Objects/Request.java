package com.example.manuel.pilarapp.Objects;

import com.example.manuel.pilarapp.Objects.Actos;

import java.util.List;

/**
 * Created by Manuel on 19/09/2015.
 */
public class Request {

    private Integer totalCount;
    private Integer start;
    private Integer rows;
    private List<Actos> result;

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

    public List<Actos> getResult() {
        return result;
    }

    public void setResult(List<Actos> result) {
        this.result = result;
    }

    public Request(Integer totalCount, int start, int rows, List<Actos> result) {
        this.totalCount = totalCount;
        this.start = start;
        this.rows = rows;
        this.result = result;
    }



}
