package ds.multiwayTree;

public class DataItem {
	
	public double data;
	
	public DataItem(double data){
		this.data = data;
	}
	
	public void displayItem(){
		System.out.print("/" + this.data);
	}
}
