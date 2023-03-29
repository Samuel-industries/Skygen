package api.webhook.exceptions;

public class WebhookException extends RuntimeException
{
    public WebhookException(final String reason) {
        super(reason);
    }
}
