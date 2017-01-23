package sport.totalizator.exception;

import sport.totalizator.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberException extends ExceptionWithErrorList {
    private Member member;

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
        super();
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
