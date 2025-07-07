package com.hzokbe.eigakan.model.paginated.response;

import java.util.List;

public class PaginatedResponse<T> {
    private List<T> content;

    private Integer number;

    private Integer totalPages;
    
    private Integer size;

    private Long totalElements;

    private Boolean first;

    private Boolean last;

    public PaginatedResponse() {
    }

    public PaginatedResponse(
        List<T> content,
        Integer number,
        Integer totalPages,
        Integer size,
        Long totalElements,
        Boolean first,
        Boolean last
    ) {
        this.content = content;

        this.number = number;

        this.totalPages = totalPages;

        this.size = size;

        this.totalElements = totalElements;

        this.first = first;

        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
}
