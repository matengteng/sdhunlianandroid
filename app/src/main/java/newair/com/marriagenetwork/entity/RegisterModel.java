package newair.com.marriagenetwork.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/1/27.
 */
public class RegisterModel  extends BaseModel{


    /**
     * user : F4y9/WivfZQm7qaBgLS5VhTmiSyFCte2V+1PsZYJ5TP6Jo1BNBHEJACj8qCiQ0IuxpNKyY3EEdsV9p8U1lLwBA==
     */

    private DataEntity data;

    public void setData(DataEntity data) {
        this.data = data;
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
