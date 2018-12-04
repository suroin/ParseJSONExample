
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;

@JsonIgnoreProperties({ "owner", "identifier","modDate","inode","folder","disabledWYSIWYG","sortOrder","modUser","host" ,"lastReview","stInode","languageId"})
public class DepositValues implements Serializable {

    @JsonProperty
    private Double minMonth;
    @JsonProperty
    private Double maxMonth;
    @JsonProperty
    private Double minPercent;
    @JsonProperty
    private Double maxPercent;
    @JsonProperty
    private Double monthlyServiceFee;
    @JsonProperty
    private String code;



    public DepositValues(Double minPercent,Double maxPercent, Double minMonth, Double maxMonth, Double monthlyServiceFee, String code) {

        this.minMonth = minMonth;
        this.maxMonth = maxMonth;
        this.minPercent = minPercent;
        this.maxPercent = maxPercent;
        this.monthlyServiceFee = monthlyServiceFee;
        this.code = code;


    }

    public DepositValues() {}

    public Double getMaxMonth() {
        return this.maxMonth;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }


    public void setMaxMonth(Double maxMonth) {
        this.maxMonth = maxMonth;
    }


    public Double getMaxPercent() {
        return this.maxPercent;
    }

    public void setMaxPercent(Double maxPercent) {
        this.maxPercent = maxPercent;
    }


    @Override
    public String toString(){

        return "DepositValues [minPercent = " + minPercent + ", maxPercent=" + maxPercent + ", minMonth=" + minMonth
                + ", maxMonth=" + maxMonth + ", monthlyServiceFee=" + monthlyServiceFee + ", code=" + code + "]";
    }

    @JsonValue
    public String toJson(){
        return "[minPercent = " + minPercent + ", maxPercent=" + maxPercent + ", minMonth=" + minMonth
                + ", maxMonth=" + maxMonth + ", monthlyServiceFee=" + monthlyServiceFee + ", code=" + code + "]";
    }
}
