public class MyClass {
    
    public static void main(String args[]) throws InterruptedException {
    
        while(true) {
             
            Thread t1 = new Thread (new Runnable() {
                @Override
                public void run() {
                    Singleton instance1 = Singleton.getInstance();
                    System.out.println("Instance 1 hash:" + instance1.hashCode());
                }
            });
        
            Thread t2 = new Thread (new Runnable() {
                @Override
                public void run() {
                    Singleton instance2 = Singleton.getInstance();
                    System.out.println("Instance 2 hash:" + instance2.hashCode());
                }
            });
        
            t1.start();
            t2.start();
            System.out.println(t1.getName() + " " + t2.getName());
            Singleton.destroy();
            
            Thread.sleep(1000);
        }
             
    }
}

/***
 * Singleton Design Pattern
 * 
 * */
 
 class Singleton {
     private static volatile Singleton instance;
     
     private Singleton() {
         if (instance != null)
            throw new RuntimeException("Use getInstance method to use singleton instance");
     }
     
     public static Singleton getInstance() {
         
         if (instance == null) {
             synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
         }
         return instance;
     }
     
     public static void destroy() {
         instance = null;
     }
 }
