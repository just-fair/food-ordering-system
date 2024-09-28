
public class Food {
    protected String foodImage="./Images/";
    protected String foodName="";
    protected int foodPrice=0;
    protected int pcs=1;
    protected int total=0;
    
    //gamit sa admin transactions, view ng receipt
    public Food(String name, int qnty, int total){
        this.foodName=name;
        this.pcs=qnty;
        this.total=total;
    }
    
    //gamit sa Dashboard
    public Food(String foodImage, String foodName, int foodPrice){
        this.foodImage=foodImage;
        this.foodName=foodName;
        this.foodPrice=foodPrice;
        this.total=foodPrice*pcs;
    }
    
    //METHOD
    public void setPcs(int pcs){
        this.pcs=pcs;
    }
    
    public void setTotal(int total){
        this.total=total;
    }
    
    
    
}
