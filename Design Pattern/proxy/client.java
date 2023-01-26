package service.proxypattern;

public class client {
    String token = "pradhuman";
    serviceinterface proxy;

    client() {
        proxy = new proxy(token);
    }

    public void updateString (String s) {
        proxy.putString(s);
    }
}
