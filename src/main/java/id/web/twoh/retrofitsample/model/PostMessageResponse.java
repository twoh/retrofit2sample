package id.web.twoh.retrofitsample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hafizh Herdi on 10/15/2016.
 */

public class PostMessageResponse {

    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("username")
    @Expose
    private String username;

    /**
     *
     * @return
     * The age
     */
    public String getAge() {
        return age;
    }

    /**
     *
     * @param age
     * The age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The sex
     */
    public String getSex() {
        return sex;
    }

    /**
     *
     * @param sex
     * The sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
