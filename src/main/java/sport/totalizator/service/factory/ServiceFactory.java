package sport.totalizator.service.factory;

import sport.totalizator.service.*;
import sport.totalizator.service.impl.*;

/**
 * ServiceFactory is implementation of factory pattern that allows us to get
 * some Service object. Singleton.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance(){
        return instance;
    }

    ServiceFactory(){}

    public UserService getUserService(){
        return UserServiceImpl.getInstance();
    }

    public EventService getEventService(){
        return EventServiceImpl.getInstance();
    }

    public LeagueService getLeagueService(){
        return LeagueServiceImpl.getInstance();
    }

    public MemberService getMemberService(){
        return MemberServiceImpl.getInstance();
    }

    public RateService getRateService(){
        return RateServiceImpl.getInstance();
    }

    public PaySystemService getPaySystemService() {
        return PaySystemServiceImpl.getInstance();
    }

    public EventResultService getEventResultService() {
        return EventResultServiceImpl.getInstance();
    }

    public CategoryService getCategoryService() {
        return CategoryServiceImpl.getInstance();
    }
}
