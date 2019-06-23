package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    @Id
    private String _id;
    private String articleid;
    private String content;
    private String userid;
    private String parentid;
    private Date publishdate;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
}
