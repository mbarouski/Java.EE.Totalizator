package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.LeagueDAO;
import sport.totalizator.dao.MemberDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.Member;
import sport.totalizator.service.MemberService;
import sport.totalizator.service.exception.ServiceException;

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
}
