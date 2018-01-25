package test.levkovskiy.com.digifico.net.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class NewsModel implements Parcelable {

    private int id;
    private String title;
    private String type;
    private String content;

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    NewsModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        type = in.readString();
        content = in.readString();


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(content);
    }


}

