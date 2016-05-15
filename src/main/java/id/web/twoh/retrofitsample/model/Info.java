package id.web.twoh.retrofitsample.model;

/**
 * Created by Hafizh Herdi on 5/1/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("seed")
    @Expose
    private String seed;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     *
     * @return
     * The seed
     */
    public String getSeed() {
        return seed;
    }

    /**
     *
     * @param seed
     * The seed
     */
    public void setSeed(String seed) {
        this.seed = seed;
    }

    /**
     *
     * @return
     * The results
     */
    public Integer getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(Integer results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     * The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     *
     * @return
     * The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     * The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

}