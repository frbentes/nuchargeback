package nubank.com.br.nuchargeback.model;

import com.google.gson.annotations.SerializedName;

public class ChargebackLink {

    @SerializedName("block_card")
    private Link blockCard;

    @SerializedName("unblock_card")
    private Link unblockCard;

    @SerializedName("self")
    private Link self;

    public ChargebackLink() {
    }

    public ChargebackLink(String blockCard, String unblockCard, String self) {
        this.blockCard = new Link(blockCard);
        this.unblockCard = new Link(unblockCard);
        this.self = new Link(self);
    }

}
