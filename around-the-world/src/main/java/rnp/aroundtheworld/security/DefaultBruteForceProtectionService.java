package rnp.aroundtheworld.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.services.UserService;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Service("bruteForceProtectionService")
public class DefaultBruteForceProtectionService  implements BruteForceProtectionService{
    private int maxFailedLogins;
    private int cacheMaxLimit;
    private final ConcurrentHashMap<String, FailedLogin> cache;

    @Autowired
    UserService userService;

    public DefaultBruteForceProtectionService() {
        cache = new ConcurrentHashMap<String, FailedLogin>(cacheMaxLimit);
    }

    @Override
    public void registerLoginFailure(String username) {
        User user = userService.findByUsername(username);
        if((user != null ) && (!userService.isAccountLocked(username))){
            if (!cache.containsKey(username)){
                System.out.printf("%nUSER: %s NOT IN cache -> adding user....%n", username);
                cache.put(username,new FailedLogin( LocalDateTime.now()));
            }
            FailedLogin failedLogin = cache.get(username);
            FailedLogin newFailedLogin = new FailedLogin( LocalDateTime.now());


            cache.put(username, newFailedLogin);
            cache.forEach( (key, val) -> System.out.println("COUNT IN CACHE "+val.getCount()));
        }

    }

    @Override
    public void resetBruteForceCounter(String username) {

    }

    @Override
    public boolean isBruteForceAttack(String username) {
        return false;
    }

    private class FailedLogin{
        private int count ;
        private LocalDateTime firstFailedLoginAttemptDate;
        private LocalDateTime secondFailedLoginAttemptDate;


        public FailedLogin(LocalDateTime firstFailedLoginAttemptDate) {
           this.firstFailedLoginAttemptDate = firstFailedLoginAttemptDate;
            this.count = 1;
        }

        public void incrementCount(){
            if (count + 1 >= 4) throw new IllegalArgumentException("Maximum value of count has been reached");
            count++;
        }

        public int getCount() {
            return count;
        }


    }
}

