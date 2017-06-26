package nubank.com.br.nuchargeback.model;

public class ChargebackResponse {

    private String status;

    public ChargebackResponse() {
    }

    public ChargebackResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
