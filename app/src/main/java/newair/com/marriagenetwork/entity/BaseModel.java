package newair.com.marriagenetwork.entity;

/**
 * Created by mateng on 16/1/18.
 */
public class BaseModel{


    /**
     * status : 1
     * msg : 成功
     */

    private int status;
    private String msg;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


}
