import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends Service{
	private static final String INSERTPRODUCT = "INSERT INTO PRODUCT (PRODUCTNAME, PRODUCTPRICE, PRODUCTQUANTITY) VALUES(?,?,?)";
    private static final String GETPRODUCTS = "SELECT * FROM PRODUCT ORDER BY RAND() LIMIT 8";
	private static final String GETLASTID = "SELECT MAX(idproduct) FROM PRODUCT";
    private static final String UPDATEPRODUCT = "UPDATE PRODUCT SET PRODUCTQUANTITY =(?) WHERE IDPRODUCT=(?)";
    private static final String INSERTPURCHASE = "INSERT INTO PURCHASE (PURCHASEPRODUCTID, PURCHASEPRODUCTQUANTITY) VALUES(?,?)";

	
    public void insert(Product product) throws Exception {

        this.connect();
        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(INSERTPRODUCT);
            
            int id = findId();
            pstmt.setString(1, product.getName()+ id);
            pstmt.setInt(2, product.getPrice()); 
            pstmt.setInt(3, product.getQuantity());
            
            pstmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("No se pudo insertar el registro.");
        } finally {
            this.disconect();
            }
    }
    public int findId() throws Exception {
    	int id =0;
        this.connect();
        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(GETLASTID);
          
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                 id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("No se pudo buscar el registro.");
        } finally {
            this.disconect();
        }

        return id+1;
    }

	
	 public List<Product> getProducts() throws Exception {
	        this.connect();
	        ArrayList<Product> productList = new ArrayList<>();
	            
	        try {
	            PreparedStatement pstmt = this.getConnection().prepareStatement(GETPRODUCTS);
	            ResultSet rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	                int id = rs.getInt("IDPRODUCT");
	                String name = rs.getString("PRODUCTNAME");
	                int price = rs.getInt("PRODUCTPRICE");
	                int quantity = rs.getInt("PRODUCTQUANTITY");
	                
	                productList.add(new Product(id, name, price, quantity));
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new Exception("No se pudo buscar el registro.");
	        } finally {
	            this.disconect();
	        }

	        return productList;
	    }
	    
	 
	 public void Update(Product product, int quantity) throws Exception {

	        this.connect();
	        try {
	            PreparedStatement pstmt = this.getConnection().prepareStatement(UPDATEPRODUCT);
	            pstmt.setInt(1, (product.getQuantity()-quantity));
	            pstmt.setInt(2, product.getId());
	            pstmt.execute();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new Exception("No se pudo actualizar el producto.");
	        } finally {
	            this.disconect();
	        }
	    }
	 
	 public void insertPurchase(Product product, int quantity) throws Exception {

	        this.connect();
	        try {
	            PreparedStatement pstmt = this.getConnection().prepareStatement(INSERTPURCHASE);
	            
	            pstmt.setInt(1, product.getId());
	            pstmt.setInt(2, quantity);
	            
	            pstmt.execute();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new Exception("No se pudo insertar el registro.");
	        } finally {
	            this.disconect();
	            }
	    }
}
