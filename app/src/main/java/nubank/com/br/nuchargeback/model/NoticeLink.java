package nubank.com.br.nuchargeback.model;

public class NoticeLink {

    public Link chargeback;

    public NoticeLink() {
    }

    public NoticeLink(String href) {
        this.chargeback = new Link(href);
    }

}
