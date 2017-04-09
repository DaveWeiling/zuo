package com.itheima.tourhelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/4/8.
 * <p>
 * tourSite 景点名称
 * tourNum 去过的总人数
 * tourLineNum 当前正在排队的人数
 * image 景点的图片
 */
public class DataBean implements Parcelable {
    String tourSite;
    int tourNum;
    int tourLineNum;
    int star;
    int image;

    public void setTourSite(String tourSite) {
        this.tourSite = tourSite;
    }

    public void setTourNum(int tourNum) {
        this.tourNum = tourNum;
    }

    public void setTourLineNum(int tourLineNum) {
        this.tourLineNum = tourLineNum;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "tourSite='" + tourSite + '\'' +
                ", tourNum=" + tourNum +
                ", tourLineNum=" + tourLineNum +
                ", star=" + star +
                ", image=" + image +
                '}';
    }

    public String getTourSite() {
        return tourSite;
    }

    public int getTourNum() {
        return tourNum;
    }

    public int getTourLineNum() {
        return tourLineNum;
    }

    public int getStar() {
        return star;
    }

    public int getImage() {
        return image;
    }

    public DataBean(String tourSite, int tourNum, int tourLineNum, int star, int image) {
        this.tourSite = tourSite;
        this.tourNum = tourNum;
        this.tourLineNum = tourLineNum;
        this.star = star;
        this.image = image;
    }

    protected DataBean(Parcel in) {
        tourSite = in.readString();
        tourNum = in.readInt();
        tourLineNum = in.readInt();
        star = in.readInt();
        image = in.readInt();
    }

    public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel in) {
            return new DataBean(in);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tourSite);
        dest.writeInt(tourNum);
        dest.writeInt(tourLineNum);
        dest.writeInt(star);
        dest.writeInt(image);
    }
}
