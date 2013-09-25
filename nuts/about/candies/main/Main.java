package nuts.about.candies.main;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.nutsaboutcandy.entities.InventoryPackages;
import com.nutsaboutcandy.entities.Product;
import com.nutsaboutcandy.entities.categories.CandyCategory;
import com.nutsaboutcandy.entities.categories.Category;
import com.nutsaboutcandy.entities.categories.NutsCandyCategory;
import com.nutsaboutcandy.entities.categories.NutsCategory;
import com.nutsaboutcandy.entities.items.Candy;
import com.nutsaboutcandy.entities.items.Item;
import com.nutsaboutcandy.entities.items.Nut;



public class Main {

	public static void main(String[] args) {
		
		doLogin();
		
		ArrayList<InventoryPackages> packagesDB = null;
		try {
			packagesDB = getDB();
		} catch (ClassNotFoundException | IOException e) {
			packagesDB = new ArrayList<InventoryPackages>();
		}
		
		boolean logout=false;
		
		while(!logout){
			String mainMenuInput = mainMenu();
			
			if(mainMenuInput.equals("1")){
				doInventory(packagesDB);
			}
			else if(mainMenuInput.equals("2")){
				System.out.println("\n-------------------------Under Construction--------------------------------");
			}
			else if(mainMenuInput.equals("3")){
				System.out.println("\n-------------------------Under Construction--------------------------------");
			}
			else if(mainMenuInput.equals("4")){
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("You have just been logged out");
				logout=true;
			}
			else{
				System.out.println("Invalid Input!!!");
			}
		}
	
	}
	
	private static ArrayList<InventoryPackages> getDB() throws IOException, ClassNotFoundException{
		
		FileInputStream fis = new FileInputStream("packages.db");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ArrayList<InventoryPackages> tempDB = (ArrayList<InventoryPackages>) ois.readObject();
		
		return tempDB;
	}
	
	private static void saveDB(ArrayList<InventoryPackages> packagesDB) throws IOException{
		FileOutputStream fos = new FileOutputStream("packages.db");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(packagesDB);
		
		oos.close();
	}
	
	
	//////////////////////////////////////--> Log In <--////////////////////////////////////////
	
	private static void doLogin(){
		Scanner loginScanner = new Scanner(System.in);
		System.out.println("LOGIN");
		System.out.println(".................");
		boolean success=false;
		
		while(!success){
			System.out.print("Username: ");
			String username = loginScanner.next();
			
			System.out.print("Password: ");
			String password = loginScanner.next();
			
			if(username.equalsIgnoreCase("ysanth") && password.equalsIgnoreCase("ysanth")){
				success=true;
				System.out.println("Welcome "+username.toUpperCase()+"! :)");
			}
		}
		
	}
	
	
	//////////////////////////////////////--> Get Input from Main Menu <--////////////////////////////////////////
	
	private static String mainMenu(){
		
		Scanner mainMenuScanner = new Scanner(System.in);
		System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("This is the Main Menu\n"+
						   "[1] Inventory\t\t[2] Shopping Cart\t[3] Cashier\t[4] Logout");
		String input = mainMenuScanner.next();
		return input;
	}

	
	//////////////////////////////////////--> Methods for Inventory <--//////////////////////////////////////// 
	
	private static void doInventory(ArrayList<InventoryPackages> packagesDB){
		Scanner inventoryScanner = new Scanner(System.in);
		boolean doneInventory = false;
		
		while(!doneInventory){
			System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("INVENTORY"+
					   "\n"+"[1] Add Item\t\t[2] Update Item\t\t[3] Remove Item\t[4] Display Item\t[5] Back to Main Menu");
			String input = inventoryScanner.next();
			
			if(input.equals("1")){
				doAddItem(packagesDB);
			}
			else if(input.equals("2")){
				updateItems(packagesDB);
			}
			else if(input.equals("3")){
				doDeleteItem(packagesDB);
			}
			else if(input.equals("4")){
				displayItems(packagesDB);
			}
			else if(input.equals("5")){
				doneInventory=true;
			}
			else{
				System.out.println("Invalid Input");
			}
		}
	}
	
	private static void doDeleteItem(ArrayList<InventoryPackages> packagesDB){
		Scanner delItemScanner = new Scanner(System.in);
		displayItems(packagesDB);
		int chosen=0;
		
		boolean validChoice = false;
		while(!validChoice){
			System.out.println("Item to delete:");
			try{
				chosen = Integer.parseInt(delItemScanner.nextLine().trim());
			}catch(NumberFormatException nfe){
				System.out.println("Invalid index");
				chosen = 0;
			}
			
			if(chosen > 0 && chosen <=packagesDB.size()){
				validChoice=true;
			}
		}
		
		packagesDB.remove(chosen-1);
		try {
			saveDB(packagesDB);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//////////////////////////////////////--> Adding of Items <--////////////////////////////////////////
	
	private static void doAddItem(ArrayList<InventoryPackages> packagesDB){
		Scanner addItemScanner = new Scanner(System.in);
		
		String category="";
		String name="";
		ArrayList<Item> ingredients=new ArrayList<Item>();
		String productType="";
		Integer noOfSmallStocks=0;
		Integer noOfMediumStocks=0;
		Integer noOfLargeStocks=0;
		
		
		category = inputCategory(addItemScanner, category);
		name = inputProductName(addItemScanner, name);
		inputProductIngredients(addItemScanner, category, ingredients);
		
		// --> Number of Stocks
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("Enter the Number of Stocks in Small size: ");
		noOfSmallStocks =(Integer) Integer.parseInt(addItemScanner.nextLine());
		
		System.out.print("Enter the Number of Stocks in Medium size: ");
		noOfMediumStocks =(Integer) Integer.parseInt(addItemScanner.nextLine());
		
		System.out.print("Enter the Number of Stocks in Large size: ");
		noOfLargeStocks =(Integer) Integer.parseInt(addItemScanner.nextLine());
		
		productType = inputProductType(addItemScanner, productType);
		
		
	
		//////////////////////////////////////--> Object Creation <--////////////////////////////////////////
		
		Category categoryObject = null;
		
		if(category.equals("nuts")){
			categoryObject = new NutsCategory();
			categoryObject.setType(productType);
		}
		else if(category.equals("candies")){
			categoryObject = new CandyCategory();
			categoryObject.setType(productType);
		}
		if(category.equals("nuts and candies")){
			categoryObject = new NutsCandyCategory();
			categoryObject.setType(productType);
		}
		
		Product product = new Product(name, categoryObject, ingredients);
	
		InventoryPackages packageSmall = new InventoryPackages(product, "small", noOfSmallStocks);
		InventoryPackages packageMedium = new InventoryPackages(product, "medium", noOfMediumStocks);
		InventoryPackages packageLarge = new InventoryPackages(product, "large", noOfLargeStocks);
		
		packagesDB.add(packageSmall);
		packagesDB.add(packageMedium);
		packagesDB.add(packageLarge);
	
		try {
			saveDB(packagesDB);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//////////////////////////////////////--> Update Items <--////////////////////////////////////////
	
	private static void updateItems(ArrayList<InventoryPackages> packagesDB){
		
		Scanner updateItemScanner = new Scanner(System.in);

		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Choose Item to be Updated:");
		System.out.println("Product Category\tName\t\tSize\t\tNo. of Stocks\n");
		
		int num = 1;
		
		Iterator<InventoryPackages> it = packagesDB.iterator();
				
		while (it.hasNext()) {
			InventoryPackages ip = (InventoryPackages) it.next();
			
			if(ip.getProduct().getCategory() instanceof NutsCategory){
				System.out.print("["+num+++"] "+"Nuts\t\t");
			}
			else if(ip.getProduct().getCategory() instanceof CandyCategory){
				System.out.print("["+num+++"] "+"Candy\t\t");
			}
			else if(ip.getProduct().getCategory() instanceof NutsCandyCategory){
				System.out.print("["+num+++"] "+"Nuts And Candy\t");
			}
			
		System.out.print(ip.getProduct().getName()+"\t\t");
		
		System.out.print(ip.getSize()+"\t\t");
		
		System.out.print(ip.getNumberOfStock()+"\t\t\n");
		
		}
		
		System.out.print("\nEnter Number to be updated: ");
		int numberChoice = Integer.parseInt(updateItemScanner.nextLine());
		System.out.print("\nEnter Number of Stocks: ");
		int input = updateItemScanner.nextInt();
		
		int ctr=0;
		for(InventoryPackages ip : packagesDB){
			ctr++;
			if (ctr==numberChoice) {
				ip.setNumberOfStock(input);
			}
		}
		
		try {
			saveDB(packagesDB);
		} catch (IOException e) {
			e.printStackTrace();
		};
		 
	}
	

	//////////////////////////////////////--> Display Items <--////////////////////////////////////////
	
	private static void displayItems(ArrayList<InventoryPackages> packagesDB){
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Product Category\tName\t\tType\t\tSize\t\tWeigh in Grams\t\tPrice\t\tNo. of Stocks\tIngredients\n");
		
		Iterator<InventoryPackages> it = packagesDB.iterator();
		int ctr = 1;
		while (it.hasNext()) {
			InventoryPackages ip = (InventoryPackages) it.next();
			
			if(ip.getProduct().getCategory() instanceof NutsCategory){
				System.out.print("["+ctr+++"] Nuts\t\t");
			}
			else if(ip.getProduct().getCategory() instanceof CandyCategory){
				System.out.print("["+ctr+++"] Candy\t\t");
			}
			else if(ip.getProduct().getCategory() instanceof NutsCandyCategory){
				System.out.print("["+ctr+++"] Nuts And Candy\t");
			}
			
			System.out.print(ip.getProduct().getName()+"\t");
			
			System.out.print(ip.getProduct().getCategory().getType()+"\t\t");
			
			System.out.print(ip.getSize()+"\t\t");
			
			System.out.print(ip.getWeighInGrams()+" grams"+"\t\t");
			
			
				//////////////////////////--> Product Price <--//////////////////////////////
		
				if(ip.getSize().equalsIgnoreCase("small")){
					System.out.print("Php "+ip.getProduct().getCategory().getSmallPrice()+"\t");
				}
				else if(ip.getSize().equalsIgnoreCase("medium")){
					System.out.print("Php "+ip.getProduct().getCategory().getMediumPrice()+"\t");
				}
				else if(ip.getSize().equalsIgnoreCase("large")){
					System.out.print("Php "+ip.getProduct().getCategory().getLargePrice()+"\t");
				}
				
			System.out.print(ip.getNumberOfStock()+"\t\t");
			
			ArrayList<Item> ingredients = ip.getProduct().getItems();
			
			Iterator<Item> itemsIt = ingredients.iterator();
			
			while(itemsIt.hasNext()){
				Item item = itemsIt.next();
				System.out.print(item.getName()+"(");
				if(item instanceof Candy){
					System.out.print("Candy)/");
				}
				else if(item instanceof Nut){
					System.out.print("Nut)/");
				}
			}
			System.out.print("\n");
		}
		
	}	
	//////////////////////////////////////--> Extract Methods <--////////////////////////////////////////
	

	private static String inputProductType(Scanner addItemScanner, String productType) {
		boolean validType=false;
		while(!validType){
			System.out.print("Enter Product Type:\n\n"+
					   "\t[1] Premium\n"+
					   "\t[2] Regular\n"+
					   "Selected: ");
			String input = addItemScanner.nextLine();
			if(input.equals("1")){
				productType = "premium";
				validType=true;
			}
			else if(input.equals("2")){
				productType = "regular";
				validType=true;
			}
			else{
				System.out.println("Invalid Input!!!");
			}
		}
		return productType;
	}

	private static void inputProductIngredients(Scanner addItemScanner,
		String category, ArrayList<Item> ingredients) {
		boolean ingredientCompleted=false;
		while(!ingredientCompleted){
			String ingredientName = "";
			
			boolean validIngredient = false;	
			while(!validIngredient){
				System.out.print("\nEnter Product Ingredient: ");
				ingredientName = addItemScanner.nextLine();
				
				if(!(ingredientName.trim().equals(""))){
					validIngredient = true;
				}	
			}
			
			Item item=null;
			
			if(category.equals("nuts")){
				item = new Nut();
			}
			else if(category.equals("candies")){
				item = new Candy();
			}
			else if(category.equals("nuts and candies")){
				boolean specified=false;
				while(!specified){
					System.out.print("Specify:\n"+
							 "\n[1] Nut"+
							 "\n[2] Candy"+
							 "\nSelected: ");
					String selected = addItemScanner.nextLine();
					if(selected.equals("1")){
						item = new Nut();
						specified=true;
					}
					else if(selected.equals("2")){
						item = new Candy();
						specified=true;
					}
					else{
						System.out.println("Invalid!");
					}
				}
			}
			item.setName(ingredientName);
			ingredients.add(item);
			
			boolean correctWantMore=false;
			while(!correctWantMore){
				System.out.print("\nDo you want to add another ingredient?\n"+
						 "[Y] - Yes/ [N] - No : ");
				String wantMore = addItemScanner.nextLine();
				
				if(wantMore.equalsIgnoreCase("y")){
					System.out.print("\nAdd another Ingredient: ");
					correctWantMore=true;
				}
				else if(wantMore.equalsIgnoreCase("n")){
					ingredientCompleted=true;
					correctWantMore=true;
				}
				else{
					System.out.println("Invalid Input!!!");
				}
			}
		}
	}

	private static String inputProductName(Scanner addItemScanner, String name) {
		boolean confirmedName=false;
		while(!confirmedName){
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("Enter Product Name: ");
			String currentname = addItemScanner.nextLine();
			
			System.out.print("\nAre you sure with the Product Name? \n"+
					   "[Y] - Yes/ [N] - No: ");
			String sure = addItemScanner.nextLine();
			
			if(sure.equalsIgnoreCase("y")){
				name = currentname;
				confirmedName=true;
			}
			else if(sure.equalsIgnoreCase("n")){
				currentname="";
			}
			else{
				System.out.println("Invalid Input!!!");
			}
		}
		return name;
	}

	private static String inputCategory(Scanner addItemScanner, String category) {
		boolean validCategory=false;
		while(!validCategory){
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("Enter Product Category:\n\n"+
					   "\t[1] Nuts\n"+
					   "\t[2] Candies\n"+
					   "\t[3] Nuts And Candies\n\n"+
					   "Selected: ");
			String input = addItemScanner.nextLine();
			if(input.equals("1")){
				category = "nuts";
				validCategory=true;
			}
			else if(input.equals("2")){
				category = "candies";
				validCategory=true;
			}
			else if(input.equals("3")){
				category = "nuts and candies";
				validCategory=true;
			}
			else{
				System.out.println("Invalid Input!!!");
			}
		}
		return category;
	}
	

}
