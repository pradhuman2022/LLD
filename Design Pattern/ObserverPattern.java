import java.util.*;


/**
 * Problem: Payment application
 * We will have Payment application which shall do following transactions,
 * TransactionsUseCases:
 * 1) Send Money
 * 2) Recieve Money
 * */
public class Main  {
    public static void main(String[] args) {
       BiddingSubject sub = new BiddingSubject();
       BidderObserver ob1 = new BidderObserver(sub);
       BidderObserver ob2 = new BidderObserver(sub);
       
       sub.createNewBid();
       sub.bidIsClosed();
    }
}

/**
*  Publisher
**/
interface Subject {
  public void registerObserver(Observer ob);
  public void removeObserver(Observer ob);
  public void notifyObserver();
}

/**
* Subscriber
*/
interface Observer {
  public void update(int bidId);
}

class BiddingSubject implements Subject {
  int bidId;
  Set<Observer> listeners;
  String status;
  
  BiddingSubject() {
      listeners = new HashSet<Observer>();
  }
  
  public void registerObserver(Observer ob) {
      listeners.add(ob);
  }
  
  public void removeObserver(Observer ob) {
      if (listeners.contains(ob))
        listeners.remove(ob);
  }
  
  public void notifyObserver() {
      for (Observer observer: listeners) {
          observer.update(bidId);
      }
  }
  
  public void createNewBid() {
      Random r = new Random();
      bidId = Math.abs(r.nextInt());
      this.status = "Open";
      notifyObserver();
  }
  
  public void bidIsClosed() {
      this.status = "Closed"; 
      notifyObserver();
  }
  
  public int getBidId() {
      return this.bidId;
  }
}

class BidderObserver implements Observer {
    Subject bidData;
    int price;
    BidderObserver(Subject bidData) {
        this.bidData = bidData;
        this.price = 0;
        bidData.registerObserver(this);
    }
    
    public void update(int bidId) {
        this.price += price == 0 ? price + 1 : price;
        displayPriceForBid(price, bidId);
    }
    
    public void displayPriceForBid(int price, int bidId) {
        System.out.println(price +" "+ bidId);
    }
}
