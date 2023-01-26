package service.proxypattern;

import java.util.HashSet;
import java.util.Set;

public class tokenservice {
    Set<String> tokenList = new HashSet<>();
    tokenservice () {
        tokenList = new HashSet<>();
        tokenList.add("pradhuman");

    }
    
    public boolean isAuthenticated (String credentials) {
        return tokenList.contains(credentials);
    }
}
