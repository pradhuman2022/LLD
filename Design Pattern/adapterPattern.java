public class MyClass {
    
    public static void main(String args[]) {
        God g = new God();
        AdapterInterface i = new GodAdapter(g);
        Me m = new Me(i);
    
        System.out.println(m.areYouBlessed());
        System.out.println(m.getBlessingFromGod());
        System.out.println(m.areYouBlessed());
    }
}

/***
 * Create an class for implementing a library function God
 * request god libarary to get blessings.
 * */
 
 class God { // libarary which can be mutated in future, hence we will adapt it for any changes in it.
     public String getBlessing() {
         return "Your are blessed now.";
     }
 }
 
 
 class Me { //client
    
    AdapterInterface IAdapter;
    boolean amIBlessed; 
    
    public Me(AdapterInterface IAdapter) {
        this.IAdapter = IAdapter;
    }
    
    public boolean areYouBlessed() {
        return this.amIBlessed;
    } 
    
    public String getBlessingFromGod() {
        this.amIBlessed = true;
        return (String)IAdapter.request();
    }
 }
 
 
 interface AdapterInterface { //adapted interface for defining method , will give adavantage of abstraction.
     public Object request();
 }
 
 
 class GodAdapter implements AdapterInterface { // adapter class where god class is adaptee.
     God adaptee;
     
     public GodAdapter(God adaptee) {
         this.adaptee = adaptee;
     }
     
    
     public Object request() {
         return this.adaptee.getBlessing();
     }
 }
