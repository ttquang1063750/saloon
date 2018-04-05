package com.product.nguyencongduc.bookticketapplication.DataBase;

/**
 * Created by nguyencongduc on 4/2/18.
 */

public class AgencyModel {

    private final int id;
    private final String companyName;
    private final String companyEmail;
    private final String companyPhone;
    private final String companyDescription;
    private final String companyAddress;
    private String companyBackground;
    private String companyAvatar;

    private AgencyModel(Builder builder) {
        this.id = builder.id;
        this.companyName = builder.companyName;
        this.companyEmail = builder.companyEmail;
        this.companyPhone = builder.companyPhone;
        this.companyDescription = builder.companyDescription;
        this.companyBackground = builder.companyBackground;
        this.companyAvatar = builder.companyAvatar;
        this.companyAddress = builder.companyAddress;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public String getCompanyBackground() {
        return companyBackground;
    }

    public String getCompanyAvatar() {
        return companyAvatar;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public static class Builder {

        private int id;
        private String companyName;
        private String companyEmail;
        private String companyPhone;
        private String companyAddress;
        private String companyDescription;
        private String companyBackground = "";
        private String companyAvatar = "";

        public Builder(int id, String name, String email, String desc, String phone, String address) {
            this.id = id;
            this.companyName = name;
            this.companyEmail = email;
            this.companyDescription = desc;
            this.companyPhone = phone;
            this.companyAddress = address;
        }

        public Builder setCompanyBackground(String url) {
            this.companyBackground = url;
            return this;
        }

        public Builder setCompanyAvatar(String url) {
            this.companyAvatar = url;
            return this;
        }

        public AgencyModel build() {
            return new AgencyModel(this);
        }
    }
}
