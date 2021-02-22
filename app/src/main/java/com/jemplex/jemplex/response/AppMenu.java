package com.jemplex.jemplex.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AppMenu implements Parcelable {
    @SerializedName("key")
    String key;
    @SerializedName("id")
    public Integer id;
    @SerializedName("applicationID")
    public String applicationID;
    @SerializedName("menuType")
    String menuType;
    @SerializedName("parentMenuName")
    public String parentMenuName;
    @SerializedName("menuName")
    public String menuName;
    @SerializedName("menuCondition")
    String menuCondition;
    @SerializedName("displayName")
    public String displayName;
    @SerializedName("sortOrder")
    Integer sortOrder;
    @SerializedName("isDeleted")
    Boolean isDeleted;
    @SerializedName("createdBy")
    String createdBy;
    @SerializedName("createdDate")
    String createdDate;
    @SerializedName("modifiedBy")
    String modifiedBy;
    @SerializedName("modifiedDate")
    String modifiedDate;

    protected AppMenu(Parcel in) {
        key = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        applicationID = in.readString();
        menuType = in.readString();
        parentMenuName = in.readString();
        menuName = in.readString();
        menuCondition = in.readString();
        displayName = in.readString();
        if (in.readByte() == 0) {
            sortOrder = null;
        } else {
            sortOrder = in.readInt();
        }
        byte tmpIsDeleted = in.readByte();
        isDeleted = tmpIsDeleted == 0 ? null : tmpIsDeleted == 1;
        createdBy = in.readString();
        createdDate = in.readString();
        modifiedBy = in.readString();
        modifiedDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(applicationID);
        dest.writeString(menuType);
        dest.writeString(parentMenuName);
        dest.writeString(menuName);
        dest.writeString(menuCondition);
        dest.writeString(displayName);
        if (sortOrder == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sortOrder);
        }
        dest.writeByte((byte) (isDeleted == null ? 0 : isDeleted ? 1 : 2));
        dest.writeString(createdBy);
        dest.writeString(createdDate);
        dest.writeString(modifiedBy);
        dest.writeString(modifiedDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppMenu> CREATOR = new Creator<AppMenu>() {
        @Override
        public AppMenu createFromParcel(Parcel in) {
            return new AppMenu(in);
        }

        @Override
        public AppMenu[] newArray(int size) {
            return new AppMenu[size];
        }
    };
}
