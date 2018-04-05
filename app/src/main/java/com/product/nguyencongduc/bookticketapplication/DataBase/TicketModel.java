package com.product.nguyencongduc.bookticketapplication.DataBase;

import com.product.nguyencongduc.bookticketapplication.utils.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

import static com.product.nguyencongduc.bookticketapplication.utils.DateFormatUtils.PARAMS_INPUT_3;
import static com.product.nguyencongduc.bookticketapplication.utils.DateFormatUtils.PARAMS_INPUT_4;

/**
 * Created by nguyencongduc on 4/2/18.
 */

public class TicketModel {

    private final int id;
    private final int maxRegister;
    private final int curRegister;
    private final String airCraftName;
    private final Date date;

    public TicketModel(Builder builder) {
        this.id = builder.id;
        this.maxRegister = builder.maxRegister;
        this.curRegister = builder.curRegister;
        this.date = builder.date;
        this.airCraftName = builder.airCraftName;
    }

    public int getId() {
        return id;
    }

    public int getMaxRegister() {
        return maxRegister;
    }

    public int getCurRegister() {
        return curRegister;
    }

    public Date getDate() {
        return date;
    }

    public String getDay() {
        return DateFormatUtils.getInstance().getStringFromDate(date, PARAMS_INPUT_4);
    }

    public String getTime() {
        return DateFormatUtils.getInstance().getStringFromDate(date, PARAMS_INPUT_3);
    }

    public String getAirCraftName() {
        return airCraftName;
    }

    public boolean canRegister() {
        return curRegister < maxRegister;
    }

    public static class Builder {

        private int id;
        private int maxRegister;
        private int curRegister;
        private Date date;
        private String airCraftName;

        public Builder(int id, int maxRegister, int curRegister, String airName) {
            this.id = id;
            this.maxRegister = maxRegister;
            this.curRegister = curRegister;
            this.airCraftName = airName;
            date = Calendar.getInstance().getTime();
        }

        public Builder setDate(Date dt) {
            date = dt;
            return this;
        }

        public TicketModel build() {
            return new TicketModel(this);
        }
    }
}
