package newair.com.marriagenetwork.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/1/27.
 */
public class LoginModel {

    /**
     * status : 1
     * msg : 登陆成功
     * data : {"user":"F4y9/WivfZS4uN4gdhP37M0RoCFi2D4RQHAshNJMUaDNICgQG+BTDAOOYOPryyzDRk3Wh0aTcxxnrnW/cG2qBCz74kG37s6XVb3eQUAGoJ8="}
     */

    private int status;
    private String msg;
    /**
     * user : F4y9/WivfZS4uN4gdhP37M0RoCFi2D4RQHAshNJMUaDNICgQG+BTDAOOYOPryyzDRk3Wh0aTcxxnrnW/cG2qBCz74kG37s6XVb3eQUAGoJ8=
     */

    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }



    public DataEntity getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {
        private String user;

        public void setUser(String user) {
            this.user = user;
        }

        public String getUser() {
            return user;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.user);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.user = in.readString();
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }
}
