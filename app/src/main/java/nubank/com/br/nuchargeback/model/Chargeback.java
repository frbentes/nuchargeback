package nubank.com.br.nuchargeback.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class Chargeback {

    @SerializedName("id")
    private String id;

    @SerializedName("comment_hint")
    private String commentHint;

    @SerializedName("title")
    private String title;

    @SerializedName("autoblock")
    private boolean autoblock;

    @SerializedName("reason_details")
    private List<HashMap<String, String>> reasons;

    @SerializedName("links")
    private ChargebackLink links;


    public Chargeback(String id, String commentHint, String title, boolean autoblock,
                      List<HashMap<String, String>> reasons, ChargebackLink links) {
        this.id = id;
        this.commentHint = commentHint;
        this.title = title;
        this.autoblock = autoblock;
        this.reasons = reasons;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public String getCommentHint() {
        return commentHint;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAutoblock() {
        return autoblock;
    }

    public List<HashMap<String, String>> getReasons() {
        return reasons;
    }

    public ChargebackLink getLinks() {
        return links;
    }

}
