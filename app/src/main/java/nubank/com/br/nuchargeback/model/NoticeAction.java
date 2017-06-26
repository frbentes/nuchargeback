package nubank.com.br.nuchargeback.model;

public class NoticeAction {

    public String title;
    public String action;

    public NoticeAction(String title, String action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public String getAction() {
        return action;
    }

}
