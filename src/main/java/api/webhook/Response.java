package api.webhook;

import com.google.gson.annotations.*;

public class Response
{
    boolean global;
    String message;
    @SerializedName("retry_after")
    int retryAfter;
    
    public boolean isGlobal() {
        return this.global;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public int getRetryAfter() {
        return this.retryAfter;
    }
}
