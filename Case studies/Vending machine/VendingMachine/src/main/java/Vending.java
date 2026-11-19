import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Vending {
    //managing the whole products
    //from storing them to give updates on availabilty
    Scanner sc;
    //A hashmap to store products with them as objects and each product's quantity
    HashMap<Product,Integer> productMap;
    //is it enough?
    //NO to store the currency in the vending machine and give clear denominations everytime
    //we will use another hashmap to store denomination and available coins or notes in the machine
    TreeMap<Integer,Integer> availableCurrency;
    List<Integer> totalCashInDenos;
    //so when intializing vending machine need to intialize these both

    //Vending machine setup ! is there any updations in vending machines?? yes:NO
    //Not required for now
    //We can update the productMap later if required with UpdateProdcts..

    //lets generate a constructor for this now
    public Vending(){
        sc=new Scanner(System.in);
        productMap=new HashMap<>();
        totalCashInDenos=new ArrayList<>();
        availableCurrency=new TreeMap<>(Collections.reverseOrder());
        this.intializeMachine();
        this.intializeDispensary();

    }

    void showMenu(){

//        productMap.entrySet().stream().map(entry->{
//            Product temp=entry.getKey();
//            temp.getName();
//            temp.getPrice();
//            entry.getValue();
//        }).
        boolean isRunning=true;
        while(isRunning) {
            System.out.println("Take a look at the products:");
            productMap.forEach((entry, value) -> {
                System.out.println(entry.getName() + " rupees: " + entry.getPrice() + " Quantity:" + value);
            });
            //int prodSize = productMap.size();
            //break;
            System.out.println("Enter product name to purchase or none to exit the transaction!");
            String option=sc.next().trim().toLowerCase(Locale.ROOT);

            Product prodToPurchase=productMap.keySet().stream()
                    .filter(key->option.toUpperCase(Locale.ROOT).equals(key.getName()))
                    .findFirst()
                    .orElse(null);
            //Bug here cause getName returning Uppercase product name!!

            if(option.equals("none")){
                isRunning=false;
            }
//            else if (productMap.containsKey(prodToPurchase)) {
//                System.out.println("Success here!!");
//            }//just checking here
            else if(prodToPurchase!=null && productMap.containsKey(prodToPurchase)){//need to check product here
                if(startTransaction(prodToPurchase)){
                    System.out.println("You have purchased the product successfully!");
                }
                else{
                    System.out.println("Sorry! The product is not dispensed!");
                }
            }
            else{
                System.out.println("Enter a valid product or NONE to exit!!");
            }
        }
    }
    boolean startTransaction(Product product){
        int quant=-1;
        System.out.println("Enter quantity of products to purchase!");
        try{
            quant=sc.nextInt();//two throws are here
            if(quant<0)throw new IllegalArgumentException();
        }
        catch (IllegalArgumentException E){
            System.out.println("Please enter valid quantity at least next time! Learn NUMBERS!");
            return false;
        }

        if(!quantAvail(product,quant)){
            return false;
        }
        //Total bill here
        BigDecimal total=getBill(product,quant);
        //overrided toString soo
        System.out.println();
        System.out.println("The total amount for the product "+product+" of quantity "+ quant +"="+ total);
        System.out.println("You want to checkout or cancel the purchase??");
        System.out.printf("\n\nEnter 1 to continue and any other key to cancel!");
        try{
            int ch=sc.nextInt();
            if(ch!=1)return false;
        }
        catch (InputMismatchException E){
            System.out.println("Enter valid choice!!");
        }
        //Got here without any hurdles now implement payment
        boolean paid=startPayment(product,quant,total);
        if(paid)
            return true;
        return false;
    }

    boolean startPayment(Product product,int quant,BigDecimal total){
        System.out.println();
        System.out.println("Enter payment interface you want to continue with!");
        System.out.printf("\n1.UPI\n2.Cash and coins\n#Enter your choice!#");
        int ch=-1;
        try {
            ch = sc.nextInt();
            if(ch<1||ch>2)throw new InputMismatchException();
        }
        catch (InputMismatchException F){
            System.out.println("Don't cross boundaries!!");
            return false;
        }
        catch (IllegalArgumentException E){
            System.out.println("Learn numbers first!!");
            return false;
        }
        if(ch==1){
           return simulateUPI(product,quant,total);
        }
        else if(ch==2){
            return simulateCash(product,quant,total);
        }

        return false;
    }
    boolean simulateCash(Product product,int quant,BigDecimal total){
        System.out.println();
        System.out.println("Currency being simulated!!!");
        try {
            if(!modifyProductMap(product, quant, total,false))
                throw new FailedPurchaseException("Unable to dispense from the machine sorry!!");
            else
                return true;
        }
        catch (FailedPurchaseException own){
            own.getMessage();
            return false;
        }
        //return true;
    }
    boolean calculateAndDispense(int customerCash,BigDecimal total) throws FailedPurchaseException{
        ///the customer gives total not denominations so after give-total=change
        if(customerCash<total.intValue()){
            throw new FailedPurchaseException("Not enough money!!");
        }
        //let's say given total 200,cutomer given 300
        int totalTemp=customerCash-total.intValue();//200-300 *wrong
        int totalAct=customerCash-total.intValue();//300-200 *correct
        if(totalAct<0){
            throw new FailedPurchaseException("Not enough cash is available in the machine!!");
            //return false;
        }

        //we will get the vending machine cash and coins
        //1,2,5,10,100
        //from the biggest we will minus the customerCash
        TreeMap<Integer,Integer> cashRef=availableCurrency;
        System.out.println(cashRef);

        for(Integer key:cashRef.keySet()){
            if(totalTemp==0)
                break;
            //we will get key say 100:10 notes as Treemap is being simulated in reverse!
            //so we will eliminate all 100's of customer cash from available if less
            //if greater return false NOOO wait until every currency is checked
            ///we are not atm we need to dispense only change after purchase not whole money in denominations but the change
            int avail=cashRef.get(key);
            //say avail==10 and bill==1125
            //so minus=total%100 -> 11 so out of total minus only 10 and add remaining to the total
            //can't change the availableCurrency while for each loop??

            //if 50 is there then mod=0 so no negatives here
            int needed=totalTemp/key;
            //mod=11
            //so from available<actual so minus whole available
            if(avail>=needed){
                totalTemp=totalTemp-(needed*key);
            }
            else{
                totalTemp=totalTemp-(avail*key);
            }
        }
        if(totalTemp==0){
            dipenseCash(totalAct);
            return true;
        }
        return false;
    }
    void dipenseCash(int total){
        System.out.println("Collect your change!!");
        for(Map.Entry<Integer,Integer> entry :availableCurrency.entrySet()){
            if(total==0)
                break;

            int key=entry.getKey();
            int avail=entry.getValue();
            int needed=total/key;

            if(avail>=needed){
                total=total-(needed*key);
                entry.setValue(avail-needed);
                System.out.println(key+":"+needed);
            }
            else{
                total=total-(avail*key);
                entry.setValue(0);
                System.out.println(key+":"+avail);
            }
        }
        System.out.println("The modified currency in the machiine!!");
        System.out.println(availableCurrency);
       // modifyProductMap()
    }

    boolean modifyProductMap(Product product,int quant,BigDecimal total,boolean UPI){
//        if(productMap.get(product)>=quant){//already checked in availQuant
            if(UPI){
                productMap.put(product,productMap.get(product)-quant);
                return true;
            }
            else{
                //modified after cash dispensed in dispensecash or convert to upi=true;
                if(cashDispensed(total)){
                    productMap.put(product,productMap.get(product)-quant);
                }
            }
        return false;
    }
    boolean cashDispensed(BigDecimal total){
        System.out.println("Enter the total you are going to put:");
        int customerCash=Integer.MIN_VALUE;
        try{
            customerCash=sc.nextInt();
            if(customerCash<0) throw new InputMismatchException("Can't enter negative MOney!!");
        }
        catch (InputMismatchException ip){
                System.out.println("Enter valid input!");
        }
//        System.out.println("Enter number of 1,2,5,10,100 coins in order:");
//        for(int i=0;i<5;i++)
//        {
//            try {
//                customerCash[i] = sc.nextInt();
//            }
//            catch (InputMismatchException ip){
//                System.out.println("Enter valid input!");
//                i--;
//            }
//        }
        if(calculateAndDispense(customerCash,total))
        return true;
        return false;
    }



//    boolean isProductDispensed(){
//        return true;
//    }

    boolean simulateUPI(Product product,int quant,BigDecimal total){
        System.out.println("UPI being simulated!!!");
        try {
            if(!modifyProductMap(product, quant, total,true))
                throw new FailedPurchaseException("Unable to dispense from the machine sorrr!!");
        }
        catch (FailedPurchaseException own){
            own.getMessage();
            return false;
        }
        return true;
    }
    BigDecimal getBill(Product prod,int quant){
        BigDecimal quant1=new BigDecimal(quant);
        BigDecimal total =prod.getPrice().multiply(quant1);
        //or BigDecimal.val(quant)
        return total;
    }

    boolean quantAvail(Product prod,int quant){
        return productMap.get(prod)>=quant;//valid and available quantity
    }


    private void intializeMachine() {
        System.out.println("***Welcome to vending machine setup***");
        System.out.println("Please enter the different types of products you want to store in the machine");
        System.out.println("Can't exit without adding all required products!!**");
        int prods=sc.nextInt();
        for(int i=0;i<prods;i++){
            System.out.printf("Please enter product %d name,price and quantity(name without any spaces):",i);
            String name=sc.next().trim();
            BigDecimal price=BigDecimal.valueOf(-1);
            try {
                price= sc.nextBigDecimal();
            }
            catch(InputMismatchException E){
                System.out.println("Invalid type!");
                i--;
                continue;
            }
            int quantity=sc.nextInt();
            Product prodToAdd=new Product(price,name);
            if(addProduct(prodToAdd,quantity)){
                System.out.println("Product added successfully");
            }
            else{
                System.out.println("Duplicate product or unknown error while adding the product");
                i--;
            }
        }
        System.out.println("CONGRATS!!!The vending mahcine has been set up, Now add the dispence cash to make it work properly");

    }

    void intializeDispensary(){
        System.out.println("Available denominations are:");
        System.out.println("'1','2','5','10','100'");
        availableCurrency.put(1,0);
        availableCurrency.put(2,0);
        availableCurrency.put(5,0);
        availableCurrency.put(10,0);
        availableCurrency.put(100,0);
        System.out.println("Enter respective available cash/coins for each denomination!");
        for(Integer deno:availableCurrency.keySet()){
            System.out.println("Enter "+deno+" available notes/coins:");
            try{
                int temp=sc.nextInt();
                if(temp<0) throw new InputMismatchException("No negatives!");
                availableCurrency.put(deno,temp);
            }
            catch (InputMismatchException E){
                System.out.println("Enter valid number,"+E.getMessage());
            }
        }
        System.out.println("Denominations given are:");
        System.out.println(availableCurrency);
        totalCashInDenos=availableCurrency.entrySet().stream().map(entry->
                entry.getKey()* entry.getValue()
        ).toList();
        int i=0;
        for(Integer temp:availableCurrency.keySet())
            System.out.println(temp+":"+totalCashInDenos.get(i++));
        }

    boolean addProduct(Product prod,int quant)throws IllegalArgumentException{
        if(productMap.containsKey(prod.getName()))
            return false;
        try {
            if (quant < 0 ||prod.getPrice().intValueExact() <= 0 ) {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException E){
            System.out.println("Invalid stock quantity or Invalid price!!");
            return false;
        }
        productMap.put(prod,quant);
        return true;
    }

}
