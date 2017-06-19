package id.recyclerview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UGCHomeResponseModel {


    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("ugcid")
    @Expose
    private int ugcid;

    @SerializedName("tm_user_userid")
    @Expose
    private int tmUserUserid;

    @SerializedName("moderasi_by")
    @Expose
    private int moderasiBy;

    @SerializedName("isDeleted")
    @Expose
    private int isDeleted;

    @SerializedName("filepath")
    @Expose
    private String filepath;

    @SerializedName("encfilename")
    @Expose
    private String encfilename;

    @SerializedName("datetime_add")
    @Expose
    private String datetimeAdd;

    @SerializedName("views")
    @Expose
    private int views;

    @SerializedName("tm_platform_platformid")
    @Expose
    private int tmPlatformPlatformid;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("likes")
    @Expose
    private int likes;

    @SerializedName("datetime_publish")
    @Expose
    private String datetimePublish;

    @SerializedName("videoid")
    @Expose
    private String videoid;

    @SerializedName("tm_category_categoryid")
    @Expose
    private int tmCategoryCategoryid;

    @SerializedName("datetime_delete")
    @Expose
    private Object datetimeDelete;

    @SerializedName("user_img")
    @Expose
    private Object userImg;

    @SerializedName("moderasi")
    @Expose
    private int moderasi;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("tx_img_imgid")
    @Expose
    private int txImgImgid;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("comment")
    @Expose
    private int comment;

    @SerializedName("tm_campaignid")
    @Expose
    private int tmCampaignid;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("status")
    @Expose
    private String status;

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }



    public void setUgcid(int ugcid) {
        this.ugcid = ugcid;
    }

    public int getUgcid() {
        return ugcid;
    }

    public void setTmUserUserid(int tmUserUserid) {
        this.tmUserUserid = tmUserUserid;
    }

    public int getTmUserUserid() {
        return tmUserUserid;
    }

    public void setModerasiBy(int moderasiBy) {
        this.moderasiBy = moderasiBy;
    }

    public int getModerasiBy() {
        return moderasiBy;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setEncfilename(String encfilename) {
        this.encfilename = encfilename;
    }

    public String getEncfilename() {
        return encfilename;
    }

    public void setDatetimeAdd(String datetimeAdd) {
        this.datetimeAdd = datetimeAdd;
    }

    public String getDatetimeAdd() {
        return datetimeAdd;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getViews() {
        return views;
    }

    public void setTmPlatformPlatformid(int tmPlatformPlatformid) {
        this.tmPlatformPlatformid = tmPlatformPlatformid;
    }

    public int getTmPlatformPlatformid() {
        return tmPlatformPlatformid;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setDatetimePublish(String datetimePublish) {
        this.datetimePublish = datetimePublish;
    }

    public String getDatetimePublish() {
        return datetimePublish;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setTmCategoryCategoryid(int tmCategoryCategoryid) {
        this.tmCategoryCategoryid = tmCategoryCategoryid;
    }

    public int getTmCategoryCategoryid() {
        return tmCategoryCategoryid;
    }

    public void setDatetimeDelete(Object datetimeDelete) {
        this.datetimeDelete = datetimeDelete;
    }

    public Object getDatetimeDelete() {
        return datetimeDelete;
    }

    public void setUserImg(Object userImg) {
        this.userImg = userImg;
    }

    public Object getUserImg() {
        return userImg;
    }

    public void setModerasi(int moderasi) {
        this.moderasi = moderasi;
    }

    public int getModerasi() {
        return moderasi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTxImgImgid(int txImgImgid) {
        this.txImgImgid = txImgImgid;
    }

    public int getTxImgImgid() {
        return txImgImgid;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getComment() {
        return comment;
    }

    public void setTmCampaignid(int tmCampaignid) {
        this.tmCampaignid = tmCampaignid;
    }

    public int getTmCampaignid() {
        return tmCampaignid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}