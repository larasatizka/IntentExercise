package aset;

import android.os.Parcel;
import android.os.Parcelable;

public class data implements Parcelable {
    private String fullname;
    private String email;
    private String password;
    private String confirm;
    private String homepage;
    private String about;

    public data(String fullname, String email, String password, String confirm, String homepage, String about) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.homepage = homepage;
        this.about = about;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullname);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.confirm);
        dest.writeString(this.homepage);
        dest.writeString(this.about);
    }

    protected data(Parcel in) {
        this.fullname = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.confirm = in.readString();
        this.homepage = in.readString();
        this.about = in.readString();
    }

    public static final Creator<data> CREATOR = new Creator<data>() {
        @Override
        public data createFromParcel(Parcel source) {
            return new data(source);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };
}
