package aasaanjobs.com.aasaan_http_core.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by nazmuddin.m on 2/26/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDO {
    protected String limit, next, offset, previous, total_count;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
}
