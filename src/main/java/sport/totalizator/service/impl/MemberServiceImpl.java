package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.MemberDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.Member;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.MemberService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.NumberValidator;

import java.util.List;

public class MemberServiceImpl  implements MemberService{

    private static final Logger log = Logger.getLogger(MemberServiceImpl.class);
    private static final MemberServiceImpl instance = new MemberServiceImpl();
    private MemberDAO memberDAO;

    public static MemberServiceImpl getInstance(){
        return instance;
    }

    MemberServiceImpl(){
        memberDAO = DAOFactory.getFactory().getMemberDAO();
    }

    @Override
    public List<Member> getMembersByLeague(int leagueId) throws ServiceException {
        try{
            return memberDAO.getMembersByLeague(leagueId);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Member> getMembersByEvent(int eventId) throws ServiceException {
        try{
            return memberDAO.getMembersByEvent(eventId);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public Member addMember(String name, String categoryId, String leagueId) throws ServiceException, ExceptionWithErrorList {
        Member member = new Member();
        ExceptionWithErrorList memberException = new ExceptionWithErrorList(member);
        if(name.isEmpty() || (name == null)){
            memberException.addMessage("err.name-is-invalid");
        }
        member.setName(name);
        int intCategoryId = NumberValidator.parseInt(categoryId, memberException, "err.incorrect-category");
        member.setCategoryId(intCategoryId);
        int intLeagueId = NumberValidator.parseInt(categoryId, memberException, "err.incorrect-league");
        member.setLeagueId(intLeagueId);
        if(memberException.getErrorMessageList().size() > 0){
            throw memberException;
        }
        try {
            return memberDAO.addMember(member);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }
}
