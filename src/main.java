import Errors.DuplicateIDException;

public class main {
	public static void main (String [] args) {
		Customer c1 = null;
		try {
			c1 = new Customer("123", "Yossi", "Cohen", "yoss@gmail.com", "rishon 1", CustomerType.REGULAR);
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
