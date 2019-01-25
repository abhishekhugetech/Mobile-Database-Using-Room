package com.epiclancers.intrototheroomlibrary.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static com.epiclancers.intrototheroomlibrary.database.Constants.*;

@Entity(tableName = TABLE_MOBILE_NAME)
public class Mobile implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_MOBILE_ID)
    private int mobileId;

    @ColumnInfo(name = COL_MOBILE_NAME)
    private String mobileName;

    @ColumnInfo(name = COL_MOBILE_PRICE)
    private String mobilePrice;

    @ColumnInfo(name = COL_MOBILE_PROCESSOR)
    private String mobileProcessor;

    @ColumnInfo(name = COL_MOBILE_RAM)
    private String mobileRam;

    @ColumnInfo(name = COL_MOBILE_IMAGE)
    private String mobileImageUrl;

    public Mobile(int mobileId, String mobileName, String mobilePrice, String mobileProcessor, String mobileRam , String mobileImageUrl) {
        this.mobileId = mobileId;
        this.mobileName = mobileName;
        this.mobilePrice = mobilePrice;
        this.mobileProcessor = mobileProcessor;
        this.mobileRam = mobileRam;
        this.mobileImageUrl = mobileImageUrl;
    }

    @Ignore
    public Mobile(String mobileName, String mobilePrice, String mobileProcessor, String mobileRam, String mobileImageUrl) {
        this.mobileName = mobileName;
        this.mobilePrice = mobilePrice;
        this.mobileProcessor = mobileProcessor;
        this.mobileRam = mobileRam;
        this.mobileImageUrl = mobileImageUrl;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getMobilePrice() {
        return mobilePrice;
    }

    public void setMobilePrice(String mobilePrice) {
        this.mobilePrice = mobilePrice;
    }

    public String getMobileProcessor() {
        return mobileProcessor;
    }

    public void setMobileProcessor(String mobileProcessor) {
        this.mobileProcessor = mobileProcessor;
    }

    public String getMobileRam() {
        return mobileRam;
    }

    public void setMobileRam(String mobileRam) {
        this.mobileRam = mobileRam;
    }

    public String getMobileImageUrl() {
        return mobileImageUrl;
    }

    public void setMobileImageUrl(String mobileImageUrl) {
        this.mobileImageUrl = mobileImageUrl;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Mobile createFromParcel(Parcel in) {
            return new Mobile(in);
        }

        public Mobile[] newArray(int size) {
            return new Mobile[size];
        }
    };


    public Mobile(Parcel in){
        this.mobileId = in.readInt();
        this.mobileName = in.readString();
        this.mobilePrice = in.readString();
        this.mobileProcessor = in.readString();
        this.mobileRam = in.readString();
        this.mobileImageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mobileId);
        dest.writeString(mobileName);
        dest.writeString(mobilePrice);
        dest.writeString(mobileProcessor);
        dest.writeString(mobileRam);
        dest.writeString(mobileImageUrl);
    }
}
