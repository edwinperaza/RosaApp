package cl.moriahdp.RosaApp.home.modelObject;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Entity
public class HomeModelObject implements Serializable {

    // Constants
    public static final int HEADER = 0;
    public static final int NORMAL = 1;

    // Declare the @IntDef for these constants
    @IntDef({HEADER, NORMAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface itemType {}

    @PrimaryKey
    @NonNull
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("video_url")
    @Expose
    private String videoUrl;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("date")
    @Expose
    private String date;

    private int type;

    public HomeModelObject() {

    }

    @Ignore
    public HomeModelObject(String title, String description, String videoUrl,
                           String imageUrl, String date, int type) {
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
        this.date = date;
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }

    public int getType() {
        return type;
    }
}
