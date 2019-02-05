import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int option = 0; 
		try {
			do {
				 option = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dígito de la opción \n 1-Crear un producto \n 2-Generar tiquete \n 0-Salir" ));
				switch (option) {
				case 1:
					generateProduct();
					break;
					
				case 2:
					totalPrice();
					break;
				default:
					break;
				}
				
			} while (option!=0);
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	public static void generateProduct() {
		Product product = new Product();
        try {
           product.createProduct();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public static List<Product> getProductList(){
		Product product = new Product();
		List<Product> productList = new ArrayList<>();
		try {
			productList = product.getAllProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}
	
	
	public static void totalPrice() {
		List<Product> productList = getProductList();
		int purchase = (int)(Math.random() * 8 + 1);
		int total = 0;
		int finalPrice = 0;
		int quantity = 0; 
		ProductService productService = new ProductService();
		StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append("Producto" + "\t" + "Cantidad" + "\t" + "Precio" + "\t" + "Total" + "\n\n");
		
		for (int i=0; i<purchase; i++ ){
			if(productList.get(i).getQuantity() > 0) {
				do {
					 quantity = (int)(Math.random() * 10 + 1);
				} while (quantity > productList.get(i).getQuantity());
				
				total = productList.get(i).getPrice() * quantity;
				stringBuffer.append(productList.get(i).getName()+ "\t" + quantity+ "\t\t" + productList.get(i).getPrice() + "\t" + total + "\n");
				finalPrice += total;
				
				try {
					productService.Update(productList.get(i), quantity);
					productService.insertPurchase(productList.get(i), quantity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		stringBuffer.append("\n" + "Precio Final" + "\t\t\t\t" + finalPrice);
		System.out.println(stringBuffer);
		
	}

}
