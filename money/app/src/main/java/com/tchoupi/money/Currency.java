package com.tchoupi.money;

import android.os.Parcel;
import android.os.Parcelable;

public class Currency implements Parcelable {

    private int flag;
    private double rate;
    private double euroRate;
    private String symbol;

    public Currency(int flag, double rate, double euroRate, String symbol) {
        this.flag = flag;
        this.rate = rate;
        this.symbol = symbol;
        this.euroRate = euroRate;
    }

    protected Currency(Parcel in) {
        flag = in.readInt();
        rate = in.readDouble();
        euroRate = in.readDouble();
        symbol = in.readString();
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel in) {
            return new Currency(in);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(flag);
        dest.writeDouble(rate);
        dest.writeDouble(euroRate);
        dest.writeString(symbol);
    }

    public double getEuroRate() {
        return euroRate;
    }

    public void setEuroRate(double euroRate) {
        this.euroRate = euroRate;
    }
}
