package API_Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;



public class Data_Providers {
	public String projectPath() {
		//for get project path we can also use ./ notation
		String path = System.getProperty("user.dir");
		return path;
	}

	@DataProvider (name="API Data")
	public String [][] getData() throws IOException{
		String path=projectPath()+"\\Test data\\RestAssuredFrame.xlsx";
	
		XL_Utilities xlutil=new XL_Utilities();
		int totalrows=xlutil.getRowCount(path, "Sheet1");
		int totalcols=xlutil.getCellCount(path, "Sheet1", 1);
		
		String APIData[][]=new String[totalrows][totalcols];
		for (int i = 1; i <=totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
			APIData[i-1][j]=XL_Utilities.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return APIData;
	}
	
	@DataProvider (name="Username Data")
	public String [] UsernameData() throws IOException{
		String path=projectPath()+"\\Test data\\RestAssuredFrame.xlsx";
	
		XL_Utilities xlutil=new XL_Utilities();
		int totalrows=xlutil.getRowCount(path, "Sheet1");
	
		
		String APIData[]=new String[totalrows];
		for (int i = 1; i <=totalrows; i++) {
			APIData[i-1]=XL_Utilities.getCellData(path,"Sheet1", i,1);
		}
		
		return APIData;
	}
}
