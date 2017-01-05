package sport.totalizator.entity;

import java.math.BigDecimal;

public class Rate {
    public static String WIN = "WIN";
    public static String DRAW = "DRAW";
    public static String FIRST_GOAL = "FIRST_GOAL";
    public static String EXACT_SCORE = "EXACT_SCORE";

    private String eventName;
    private int eventId;
    private BigDecimal sum;
    private BigDecimal win;
    private String username;
    private int userId;
    private String type;
    private int member1Id;
    private int member2Id;
    private int member1Score;
    private int member2Score;


    public Rate(){

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getWin() {
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMember1Id() {
        return member1Id;
    }

    public void setMember1Id(int member1Id) {
        this.member1Id = member1Id;
    }

    public int getMember2Id() {
        return member2Id;
    }

    public void setMember2Id(int member2Id) {
        this.member2Id = member2Id;
    }

    public int getMember1Score() {
        return member1Score;
    }

    public void setMember1Score(int member1Score) {
        this.member1Score = member1Score;
    }

    public int getMember2Score() {
        return member2Score;
    }

    public void setMember2Score(int member2Score) {
        this.member2Score = member2Score;
    }
}
