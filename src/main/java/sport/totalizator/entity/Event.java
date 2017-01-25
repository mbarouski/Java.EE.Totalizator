package sport.totalizator.entity;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * {@link Event} represents entity of event in database.
 */
public class Event {
    private int leagueId;
    private String eventName;
    private Time eventTime;
    private Date eventDate;
    private String eventLeague;
    private int rateCount;
    private String rateTypes;
    private int eventId;
    private String liveTranslationLink;
    private List<Member> members;
    private List<Integer> memberIds;
    private String status;
    private EventResult result;
    private boolean canMakeRate;
    private boolean canAddResult;

    public Event() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLeague() {
        return eventLeague;
    }

    public void setEventLeague(String eventLeague) {
        this.eventLeague = eventLeague;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getLiveTranslationLink() {
        return liveTranslationLink;
    }

    public void setLiveTranslationLink(String liveTranslationLink) {
        this.liveTranslationLink = liveTranslationLink;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(String rateTypes) {
        this.rateTypes = rateTypes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<Integer> memberIds) {
        this.memberIds = memberIds;
    }

    public EventResult getResult() {
        return result;
    }

    public void setResult(EventResult result) {
        this.result = result;
    }

    public void setCanMakeRate(boolean canMakeRate) {
        this.canMakeRate = canMakeRate;
    }

    public void setCanAddResult(boolean canAddResult) {
        this.canAddResult = canAddResult;
    }

    public boolean isCanMakeRate() {
        return canMakeRate;
    }

    public boolean isCanAddResult() {
        return canAddResult;
    }
}
