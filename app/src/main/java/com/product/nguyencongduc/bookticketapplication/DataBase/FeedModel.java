package com.product.nguyencongduc.bookticketapplication.DataBase;

/**
 * Created by user on 4/1/2018.
 */

public class FeedModel {

    private int id;
    private final String description;
    private final String title;
    private String urlImage;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    private FeedModel(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.title = builder.title;
        this.urlImage = builder.urlImage;
    }

    public static class Builder {
        public int id;
        public String description;
        public String title;
        public String urlImage;

        public Builder(int id, String title, String description) {
            this.id = id;
            this.description = description;
            this.title = title;
        }

        public Builder(AgencyModel agencyModel) {
            this.description = agencyModel.getCompanyDescription();
            this.title = agencyModel.getCompanyName();
            this.id = agencyModel.getId();
        }

        public Builder setimageUrl(String url) {
            this.urlImage = url;
            return this;
        }

        public FeedModel build() {
            return new FeedModel(this);
        }
    }
}
