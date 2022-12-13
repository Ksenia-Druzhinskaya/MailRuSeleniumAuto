package lib.entities;

public class EmailMessage
{
    private final String to;
    private final String subject;
    private final String body;

    public EmailMessage(String to, String subject, String body){
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo(){
        return this.to;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getBody(){
        return this.body;
    }

    public String getHtmlBody(){
        return this.body.replace("\n", "<br>");
    }
}
