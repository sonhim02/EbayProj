package Himanshu.EbayProj;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SearchProduct  {

	public static Product pd;
	public static String startTime;
	public static String endTime;
	
	@Test(priority =0)
	public  void searchProductOnEbay() throws InterruptedException, IOException {

		String product=BasicSetup.searchString;
		
		Reporting.reportMessage(BasicSetup.bw,"Searching For Product","Pass","Product Name Is :"+product,"","" );
		startTime=Reporting.getTime();
		BasicSetup.driver.findElement(By.id("gh-ac")).sendKeys(product);
		BasicSetup.driver.findElement(By.id("gh-btn")).click();
		WebDriverWait wait = new WebDriverWait(BasicSetup.driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'results for')]")));
		endTime=Reporting.getTime();
		
		if(BasicSetup.driver.findElement(By.xpath("//*[contains(text(),'results for')]")).isDisplayed()) {
			Reporting.reportMessage(BasicSetup.bw,"Product Search Results","Pass","Product Name Is :"+product,startTime,endTime );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Product Search Results","Fail","Product Name Is :"+product,startTime,endTime );
		}

	}

	@Test(priority =1)

	public  void validateAllProductsOnSearchPage() throws IOException  {
		String [] arr=BasicSetup.searchString.split(" ");
		String seacrhPart1=arr[0];
		String seacrhPart2=arr[1];
		boolean searchFlag=true;
		// finding collection of all the search objects
		List<WebElement> listItems =  BasicSetup.driver.findElements(By.xpath("//a[@class='vip']"));
		if(listItems.size()==0){
			Reporting.reportMessage(BasicSetup.bw,"Validating Item Displayed On Seach Page ","Fail","No Item Displayed For Seach Criteria","","" );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Items Displayed On Seach Page ","Pass","Total Item Displayed Are :"+listItems.size() ,"","" );
		}
			
		startTime=Reporting.getTime();
		
		int counter=1;
		for (WebElement lsItem :listItems ){

			if (lsItem.getAttribute("title").toString().trim().toUpperCase().contains(seacrhPart1.trim().toUpperCase()) &  lsItem.getAttribute("title").toString().trim().toUpperCase().contains(seacrhPart2.trim().toUpperCase())  ) {
				
			}else{ 
				searchFlag = false;
			}
			counter=counter+1;
		}
		endTime=Reporting.getTime();
		if (searchFlag==false){
			Reporting.reportMessage(BasicSetup.bw,"Validating All Products Which Are Displayed On Screen Matches Search Criteria ","Fail","",startTime,endTime );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating All Products  Which Are Displayed On Screen Matches Search Criteria ","Pass","",startTime,endTime );
		}
	} 

	@Test(priority =2)
	public void selectItemCategoryByScreenSize() throws InterruptedException, IOException{
		String arr[]=BasicSetup.screenSize.split("-");
		
		String screenSizeToClick=arr[0].trim()+"\"";
		
		startTime=Reporting.getTime();
		
		
		BasicSetup.driver.findElement(By.xpath("//*[contains(text(),'"+screenSizeToClick+"')]")).click();
		
		WebDriverWait wait = new WebDriverWait(BasicSetup.driver, 15);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='cons ']")));
		
		endTime=Reporting.getTime();
		Reporting.reportMessage(BasicSetup.bw,"Clicking On Screen Size","Pass","Screen Size Selected Is :"+BasicSetup.screenSize,startTime,endTime );
		
		if(BasicSetup.driver.findElement(By.xpath("//span[@class='cons ']")).isDisplayed()) {
			Reporting.reportMessage(BasicSetup.bw,"Checking Products For Selected Screen Size Displayed","Pass","Screen Size Selected Is :"+BasicSetup.screenSize,"","" );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Checking Products For Selected Screen Size Displayed","Fail","Screen Size Selected Is :"+BasicSetup.screenSize,"","" );
		}

	}

	@Test(priority =3)
	public void randomItemSelector() throws InterruptedException, IOException{
		String [] arr=BasicSetup.searchString.split(" ");
		List<WebElement> listItems =  BasicSetup.driver.findElements(By.xpath("//a[@class='vip']"));
		Reporting.reportMessage(BasicSetup.bw,"Total Items Displated For Slected Screen Size","Pass","Total Itesm Are "+listItems.size(),"","" );
		startTime=Reporting.getTime();


		for (WebElement lsItem :listItems ){
			String displayedTitle=lsItem.getAttribute("title").toString().trim().toUpperCase();

			if (displayedTitle.contains(arr[0].trim().toUpperCase()) &  displayedTitle.contains(arr[1].trim().toUpperCase()) & displayedTitle.contains("50")  ) {
				
				lsItem.click();
				break;
			}
		}
		

		WebDriverWait wait = new WebDriverWait(BasicSetup.driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("isCartBtn_btn")));
		endTime=Reporting.getTime();
	
		if(BasicSetup.driver.findElement(By.id("isCartBtn_btn")).isDisplayed()) {
			Reporting.reportMessage(BasicSetup.bw,"Randomly Selected The TV","Pass","",startTime,endTime );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Randomly Selecting The TV","Fail","",startTime,endTime );
		}

	}

	@Test(priority=4)
	public void validateProductDetails() throws InterruptedException, IOException{
		//Validating time Left Is In Correct Format
		startTime=Reporting.getTime();
		if (BasicSetup.driver.findElement(By.id("vi-cdown_timeLeft")).isDisplayed()){
			String timeLeftString=BasicSetup.driver.findElement(By.id("vi-cdown_timeLeft")).getText();
			
			String arr[]=timeLeftString.split(":");
			String timeLeftStr=arr[0].trim().toLowerCase();

			String pattern = "(\\d+ day \\d+ hours)|(\\d+h \\d+m \\d+s)|(\\d+m \\d+s)";
			
			if (Pattern.matches(pattern, timeLeftStr)) {
				Reporting.reportMessage(BasicSetup.bw,"Validating Total Time Left To Purchase Product","Pass","Time Left Value :" + timeLeftStr,startTime,endTime );
				
			}else {
				Reporting.reportMessage(BasicSetup.bw,"Validating Total Time Left To Purchase Product","Fail","Time Left Value :" + timeLeftStr,startTime,endTime );
				
			}
			
		}else{
			endTime=Reporting.getTime();
			Reporting.reportMessage(BasicSetup.bw,"Validating Total Time Left To Purchase Product","Pass","Time Left Value Not Displyed On Screen:",startTime,endTime );
		}
		
		startTime=Reporting.getTime();
		String priceString=BasicSetup.driver.findElement(By.id("prcIsum")).getText();
		String priceStrPattern="US \\$\\d+.\\d+";
		Pattern p=Pattern.compile(priceStrPattern);

		// Now create matcher object.
		Matcher n =p.matcher(priceString);
		endTime=Reporting.getTime();
		if (n.find( )) {
			
			Reporting.reportMessage(BasicSetup.bw,"Validating Price Value Displayed In Correct Format","Pass","Price String  Displayed:" + n.group(0),startTime,endTime );
		}else {
			
			Reporting.reportMessage(BasicSetup.bw,"Validating Price Value Displayed In Correct Format","Fail","Price String Displayed As :" + priceString,startTime,endTime );
		}
		
		
		String productName=BasicSetup.driver.findElement(By.id("itemTitle")).getText();

		String sellerName=BasicSetup.driver.findElement(By.className("mbg-nw")).getText();

		// storing the product details

		String arrPrice[]=priceString.split(" ");
		priceString=arrPrice[1];

		pd = new Product(priceString,productName,sellerName);
		if(pd.price!=""){
			Reporting.reportMessage(BasicSetup.bw,"Storing Product Details ","Pass","Price of The Product Is  :" + pd.price,"","" );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Storing Product Details","Fail","Price of The Product Is  :" + pd.price,"","" );
		}

		
		if(pd.productName!=""){
			Reporting.reportMessage(BasicSetup.bw,"Storing Product Details ","Pass","Name of The Product Is  :" + pd.productName,"","" );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Storing Product Details","Fail","Name of The Product Is  :" + pd.productName,"","" );
		}

		if(pd.sellerName!=""){
			Reporting.reportMessage(BasicSetup.bw,"Storing Product Details ","Pass","Name of The Seller Is  :" + pd.sellerName,"","" );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Storing Product Details","Fail","Name of The Seller Is  :" + pd.sellerName,"","" );
		}

		//Adding Product to cart 
		startTime=Reporting.getTime();
		Reporting.reportMessage(BasicSetup.bw,"Adding Product To Cart","Pass","","","" );
		BasicSetup.driver.findElement(By.id("isCartBtn_btn")).click();
		
		WebDriverWait wait = new WebDriverWait(BasicSetup.driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ptcBtnRight")));
		endTime=Reporting.getTime();
		
		if( BasicSetup.driver.findElement(By.id("ptcBtnRight")).isDisplayed()) {
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Added To Cart SuccessFully","Pass","",startTime,endTime );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Added To Cart SuccessFully","Fail","",startTime,endTime );
		}


	}


//	public Product getProduct(){
//		return pd;
//	}

	@Test(priority=5)
	public void validateShoppingCart() throws IOException {
		
		Reporting.reportMessage(BasicSetup.bw,"Validating Shopping Cart","Pass","","","" );
		
		startTime=Reporting.getTime();
	
		String productNameDisplayed=BasicSetup.driver.findElement(By.xpath("//div[@class='ff-ds3 fs16 mb5 fw-n sci-itmttl']//a")).getText();
		
		endTime=Reporting.getTime();
		if(productNameDisplayed.equals(pd.productName)){
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Name Displayed On Shopping  Cart","Pass","Product name Displayed:"+productNameDisplayed,startTime,endTime );

		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Name Displayed On Shopping  Cart","Fail","Product name Displayed:"+productNameDisplayed,startTime,endTime );
		}

		// Validating Price 
		startTime=Reporting.getTime();
		String priceDisplayed=BasicSetup.driver.findElement(By.xpath("//div[@class='fw-b']")).getText();
		endTime=Reporting.getTime();
		if(priceDisplayed.equals(pd.price)){
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Price Displayed On Shopping  Cart","Pass","Product Price Displayed:"+priceDisplayed,startTime,endTime );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Price Displayed On Shopping  Cart","Fail","Product Price Displayed:"+priceDisplayed,startTime,endTime );
		}

		// validating Seller Info
		startTime=Reporting.getTime();
		String sellerInfoDisplayed=BasicSetup.driver.findElement(By.xpath("//a[@class='mbg-id']")).getText();
		endTime=Reporting.getTime();
		if(sellerInfoDisplayed.equals(pd.sellerName)){
			Reporting.reportMessage(BasicSetup.bw,"Validating Seller Name Displayed On Shopping  Cart","Pass","Product Seller Name Displayed:"+sellerInfoDisplayed,startTime,endTime );

		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Seller Name Displayed On Shopping  Cart","Fail","Product Seller Name Displayed:"+sellerInfoDisplayed,startTime,endTime );
		}

		// validating cart Price Is Equal To Unit Price
		
		startTime=Reporting.getTime();
		String cartPrice=BasicSetup.driver.findElement(By.xpath("//div[@id='asyncTotal']")).getText();
		endTime=Reporting.getTime();
		if(cartPrice.contains(pd.price)){
			Reporting.reportMessage(BasicSetup.bw,"Validating Total Cart Proce Is Equal To Per Unit Item Price","Pass","Total Cart Price Displayed :"+cartPrice,startTime,endTime );

		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Total Cart Proce Is Equal To Per Unit Item Price","Fail","Total Cart Price Displayed :"+cartPrice,startTime,endTime );
		}

		// Clicking On Proceed To Check Out Button
		Reporting.reportMessage(BasicSetup.bw,"Proceeding To Check Out","Pass","","","" );
		startTime=Reporting.getTime();
		BasicSetup.driver.findElement(By.id("ptcBtnBottom")).click();
		WebDriverWait wait = new WebDriverWait(BasicSetup.driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gtChk")));	  
		endTime=Reporting.getTime();
		if( BasicSetup.driver.findElement(By.id("gtChk")).isDisplayed()) {
			Reporting.reportMessage(BasicSetup.bw,"Validating Check Out Happened  SuccessFully ","Pass","Landed On Sign In Page",startTime,endTime );
		} else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Check Out Happened  SuccessFully ","Fail","Failed To Land On Sign In page",startTime,endTime );
		}
			

		// Clicking On Guest Login Button
		Reporting.reportMessage(BasicSetup.bw,"Proceeding With Guest Login","Pass","","","" );
		startTime=Reporting.getTime();
		BasicSetup.driver.findElement(By.id("gtChk")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("page-title")));	  
		endTime=Reporting.getTime();
		if( BasicSetup.driver.findElement(By.className("page-title")).isDisplayed()) {
			Reporting.reportMessage(BasicSetup.bw,"Validating Guest Check Out Screen Displayed ","Pass","Landed On Guest Check Out Screen",startTime,endTime );
		}   else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Guest Check Out Screen Displayed ","Fail","Failed To Land On Guest Check Out Screen",startTime,endTime );	
		}

	}

	@Test(priority=6)
	public void validateGuestCheckOutScreen() throws IOException {
		Reporting.reportMessage(BasicSetup.bw,"Validating Guest CheckOut Screen","Pass","","","" );
		startTime=Reporting.getTime();
		String displayedProductNameOnGuestScreen=BasicSetup.driver.findElement(By.xpath("//div[@class='col-xs-9 item-title']")).getText();
		
		endTime=Reporting.getTime();
		
		if (displayedProductNameOnGuestScreen.equals(pd.productName)){
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Name On Guest Check Out Screen ","Pass","Correct Product Displayed On Guest CheckOut",startTime,endTime );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Name On Guest Check Out Screen ","Pass","In Correct Product Displayed On Guest CheckOut"+displayedProductNameOnGuestScreen,startTime,endTime );
		}
		
		startTime=Reporting.getTime();
		String displayedPriceOnGuestScreen=BasicSetup.driver.findElement(By.xpath("//div[@class='item-price col-xs-3']")).getText();
		
		endTime=Reporting.getTime();
		if (displayedPriceOnGuestScreen.equals(pd.price)){
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Price On Guest Check Out Screen ","Pass","Correct Price Displayed On Guest CheckOut",startTime,endTime );
		}else{
			Reporting.reportMessage(BasicSetup.bw,"Validating Product Name On Guest Check Out Screen ","Pass","In Correct Price Displayed On Guest CheckOut"+displayedPriceOnGuestScreen,startTime,endTime );
		}

	}







}
