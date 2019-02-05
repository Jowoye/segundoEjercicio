import java.util.ArrayList;
import java.util.List;

public class Product {
	private int id;
	private String name;
	private int quantity;
	private int price;
	
	public Product(){
		
	};
	
	public Product(String name, int quantity, int price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Product(int id, String name, int quantity, int price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void createProduct() {
		
		int price = (int)(Math.random() * 1000 + 100); 
		int quantity = (int)(Math.random() * 50 + 1);
		String name = "Producto";
		
		ProductService productService = new ProductService();
	    Product product = new Product();

	        try {
	            product.setName(name);
	            product.setPrice(price);
	            product.setQuantity(quantity);
	            
	            productService.insert(product);
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
	
	 public List<Product> getAllProducts() throws Exception {
	        ProductService productService = new ProductService();
	        List<Product> productList = new ArrayList<Product>();
	        
	         try {

	            for (Product prod : productService.getProducts()) {
	                  productList.add(new Product(prod.getId(), prod.getName(),prod.getPrice(),prod.getQuantity()));            
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return productList;
	    }
}
