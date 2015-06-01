package aasaanjobs.com.aasaan_http_core.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * Created by nazmuddin.m on 2/26/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDO {
    
    /** The total_count. */
    protected String limit, next, offset, previous, total_count;

    /**
     * Gets the limit.
     *
     * @return the limit
     */
    public String getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     *
     * @param limit the new limit
     */
    public void setLimit(String limit) {
        this.limit = limit;
    }

    /**
     * Gets the next.
     *
     * @return the next
     */
    public String getNext() {
        return next;
    }

    /**
     * Sets the next.
     *
     * @param next the new next
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * Gets the offset.
     *
     * @return the offset
     */
    public String getOffset() {
        return offset;
    }

    /**
     * Sets the offset.
     *
     * @param offset the new offset
     */
    public void setOffset(String offset) {
        this.offset = offset;
    }

    /**
     * Gets the previous.
     *
     * @return the previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * Sets the previous.
     *
     * @param previous the new previous
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * Gets the total_count.
     *
     * @return the total_count
     */
    public String getTotal_count() {
        return total_count;
    }

    /**
     * Sets the total_count.
     *
     * @param total_count the new total_count
     */
    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
}
