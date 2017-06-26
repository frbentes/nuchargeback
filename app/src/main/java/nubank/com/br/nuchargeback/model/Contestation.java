package nubank.com.br.nuchargeback.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class Contestation {

    @SerializedName("comment")
    private String comment;

    @SerializedName("reason_details")
    private List<HashMap<String, String>> reasons;

    public Contestation() {
    }

    public Contestation(String comment, List<HashMap<String, String>> reasons) {
        this.comment = comment;
        this.reasons = reasons;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<HashMap<String, String>> getReasons() {
        return reasons;
    }

    public void setReasons(List<HashMap<String, String>> reasons) {
        this.reasons = reasons;
    }

}
