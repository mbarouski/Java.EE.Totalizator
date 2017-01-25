package sport.totalizator.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

/**
 * {@link EventInfoTag} represents JSP tag that shows short {@link sport.totalizator.entity.Event}'s info.
 */
public class EventInfoTag extends SimpleTagSupport {
    private String eventName;
    private String eventLeague;
    private Time eventTime;
    private Date eventDate;

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventLeague(String eventLeague) {
        this.eventLeague = eventLeague;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public void doTag() throws JspException, IOException{
        StringBuilder sb = new StringBuilder("<div class=\"event-main\">");
        sb.append("<h5 class=\"event-name\">" + eventName + "</h5>");
        sb.append("<p class=\"event-league\">" + eventLeague + "</p>");
        sb.append("<time class=\"event-date\">" + eventDate + " " + eventTime + "</time>");
        sb.append("</div>");
        JspWriter out = getJspContext().getOut();
        out.write(sb.toString());
    }
}
