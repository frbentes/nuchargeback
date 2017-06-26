package nubank.com.br.nuchargeback.model;

import com.google.gson.annotations.SerializedName;

public class Notice {

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("primary_action")
    public NoticeAction primaryAction;

    @SerializedName("secondary_action")
    public NoticeAction secondaryAction;

    @SerializedName("links")
    public NoticeLink links;

    public Notice(String title, String description, NoticeAction primaryAction,
                  NoticeAction secondaryAction, NoticeLink links) {
        this.title = title;
        this.description = description;
        this.primaryAction = primaryAction;
        this.secondaryAction = secondaryAction;
        this.links = links;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public NoticeAction getPrimaryAction() {
        return primaryAction;
    }

    public NoticeAction getSecondaryAction() {
        return secondaryAction;
    }

    public NoticeLink getLinks() {
        return links;
    }

}
