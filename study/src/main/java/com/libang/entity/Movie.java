package com.libang.entity;

import java.sql.Timestamp;

/**
 * @author libang
 * @date 2018/7/10 17:14
 */
public class Movie {

    private Integer id;
    private String movieName;
    private String derictorName;
    private String area;
    private String year;
    private String imagePath;
    private String content;
    private Timestamp createTime;
    private Integer scanNum;
    private Integer replyNum;
    private String remark;
    private String lastTime;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", derictorName='" + derictorName + '\'' +
                ", area='" + area + '\'' +
                ", year='" + year + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", scanNum=" + scanNum +
                ", replyNum=" + replyNum +
                ", remark='" + remark + '\'' +
                ", lastTime='" + lastTime + '\'' +
                '}';
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getDerictorName() {
        return derictorName;
    }

    public void setDerictorName(String derictorName) {
        this.derictorName = derictorName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getScanNum() {
        return scanNum;
    }

    public void setScanNum(Integer scanNum) {
        this.scanNum = scanNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
