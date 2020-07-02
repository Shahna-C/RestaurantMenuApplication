package app;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class Main {

		private static Connection conn;

		public static void main(String[] args) { // within this method, connection to the database is made
			System.out.println("Hello World");

			final String connectionStr = "jdbc:mysql://127.0.0.1:3306/restaurantDB";

			try {
				conn = DriverManager.getConnection(connectionStr, "root", "Dance4Eva45$hu^a");
				System.out.println("Succesfully connected!");

			} catch (SQLException e) {
				System.out.println("Unable to connect to database");
				e.printStackTrace();
			}

			System.out.println("\n");
			System.out.println("Welcome!");

			Menu.showOptions();
		}

		public static void createProfile(String customer_name, String address, String phone_number) {

			final String createProfileQuery = "INSERT INTO customer (customer_name, address, phone_number) VALUES (?, ?, ?)";

			try {
				PreparedStatement ps = conn.prepareStatement(createProfileQuery);
				ps.setString(1, customer_name);
				ps.setString(2, address);
				ps.setString(3, phone_number);

				ps.executeUpdate();

				System.out.println("Profile created successfully! Your ID #: ");
				getCustomerId(customer_name);

			} catch (SQLException e) {
				System.out.println("Error in createProfileQuery");
				e.printStackTrace();
			}
		}

		public static void getCustomerId(String customer_name) { 
			final String getCustomerIdQuery = "Select id FROM customer WHERE customer_name = ?";

			try {
				PreparedStatement statement = conn.prepareStatement(getCustomerIdQuery);
				statement.setString(1, customer_name);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					System.out.println(rs.getInt("id"));
				}

			} catch (SQLException e) {
				System.out.println("Error in getCustomerIdQuery");
				e.printStackTrace();
			}
		}

		
		public static void getRestaurantId(Integer id) { 
			final String getRestaurantIdQuery = "Select id FROM restaurant WHERE id = ?";

			try {
				PreparedStatement statement = conn.prepareStatement(getRestaurantIdQuery);
				statement.setInt(1, id);
		        ResultSet rs = statement.executeQuery();
            while (rs.next()) {
				System.out.println(rs.getInt("id"));
                }
			} catch (SQLException e) {
				System.out.println("Error in getRestaurantIdQuery");
				e.printStackTrace();
			}
		}
		
		
		public static void showMenu() { 
			final String showMenuQuery = "Select item, description, price FROM items";

			try {
				PreparedStatement statement = conn.prepareStatement(showMenuQuery);
                ResultSet rs = statement.executeQuery();
             while (rs.next())  {
				System.out.println(rs.getString("item") + " " + rs.getString("description") + " " + rs.getDouble("price"));
                }
			} catch (SQLException e) {
				System.out.println("Error in showMenuQuery");
				e.printStackTrace();
			}
		}
		
		
		public static void addItem(String item, int quantity) {
			final String addItemQuery = "INSERT INTO customer_order(item, quantity) VALUES (?, ?)";

			try {
				PreparedStatement ps = conn.prepareStatement(addItemQuery);
				ps.setString(1, item);
				ps.setInt(2, quantity);         
				System.out.println(quantity + " " + item + "('s) added successfully!");
                
			} catch (SQLException e) {
				System.out.println("Error in AddItemQuery");
				e.printStackTrace();
			}
		}	


		public static void removeItem(int id) {
			final String removeItemQuery = "DELETE FROM items WHERE id = ?)";

			try {
				PreparedStatement ps = conn.prepareStatement(removeItemQuery);
				ps.setInt(1, id);
				ps.executeUpdate();
				System.out.println("item_id:"  + id + " item removed successfully");

			} catch (SQLException e) {
				System.out.println("Error in removeItemQuery");
				e.printStackTrace();
			}
		}
		
		
		public static void updateQuantity(String item, int quantity) {
			final String updateQuantityQuery = "UPDATE items set quantity = ? WHERE id = ?";

			try {
				PreparedStatement ps = conn.prepareStatement(updateQuantityQuery);
				ps.setString(1, item);
				ps.setInt(2, quantity);
				ps.executeUpdate();

				System.out.println(item + " " + quantity+ "('s) updated successfully!");

			} catch (SQLException e) {
				System.out.println("Error in updateQuery");
				e.printStackTrace();
			}
		}
	
	}


