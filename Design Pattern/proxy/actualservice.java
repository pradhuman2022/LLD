package service.proxypattern;

import java.util.Set;

class actualservice implements serviceinterface { 
   
    @Override
    public void putString(String s) {
        // TODO Auto-generated method stub
        System.out.println("Hello "  + s + ", I am actual service");
    }
}