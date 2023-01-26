package service.proxypattern;

class proxy implements serviceinterface {
    serviceinterface serviceObj;
    String credentials;
    tokenservice tokenService;

    proxy (String token) {
        credentials = token;
        serviceObj = new actualservice();
        //token verification should be part of token services like STS
        tokenService = new tokenservice();
    }

    @Override
    public void putString(String s) {
        // TODO Auto-generated method stub
        if (tokenService.isAuthenticated(credentials)) {
            serviceObj.putString(s);
        } else {
            System.out.print("Unauthorized app access, please provide correct credentials");
        } 
    }
}