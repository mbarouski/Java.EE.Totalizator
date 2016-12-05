package sport.totalizator.service.factory;

import sport.totalizator.service.EventService;
import sport.totalizator.service.UserService;
import sport.totalizator.service.impl.EventServiceImpl;
import sport.totalizator.service.impl.UserServiceImpl;

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
}
