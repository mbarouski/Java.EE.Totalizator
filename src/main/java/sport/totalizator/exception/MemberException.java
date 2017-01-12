package sport.totalizator.exception;

import sport.totalizator.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberException extends Exception {
    private Member member;
    private List<String> errorMessageList;

    public MemberException() {
        super();
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberException(Throwable cause) {
        super(cause);
    }

    public MemberException(Member member) {
        this.member = member;
        this.errorMessageList = new ArrayList<>();
    }

    public void addMessage(String message){
        this.errorMessageList.add(message);
    }

    public Member getMember() {
        return member;
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }
}
