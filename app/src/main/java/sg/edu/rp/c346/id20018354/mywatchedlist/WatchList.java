package sg.edu.rp.c346.id20018354.mywatchedlist;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class WatchList implements Serializable {

    private int id;
    private String title;
    private String description;
    private String category;

    public WatchList(int id, String title, String description, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @NonNull
    @Override
    public String toString() {
        return "WatchList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

