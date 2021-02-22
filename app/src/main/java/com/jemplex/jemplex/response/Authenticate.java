package com.jemplex.jemplex.response;

import com.google.gson.annotations.SerializedName;

public class Authenticate {
    @SerializedName("companyID")
    public String companyID;
    @SerializedName("applicationID")
    public String applicationID;
    @SerializedName("userName")
    public String userName;
    @SerializedName("password")
    public String password;
    @SerializedName("token")
    public String token;
    @SerializedName("roleName")
    public String roleName;
    @SerializedName("message")
    public String message;

    public Authenticate(String companyID, String applicationID, String userName, String password) {
        this.companyID = companyID;
        this.applicationID = applicationID;
        this.userName = userName;
        this.password = password;
    }

    public Authenticate() {
    }
}
