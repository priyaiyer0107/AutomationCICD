package SKAutomation.TestData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DataReader {

	public  List<HashMap<String, String>> Convertjsontomap() throws IOException
	{
		Path filePath = Paths.get(System.getProperty("User.dir")+"\\src\\test\\java\\SKAutomation\\TestData\\PurchaseOrder.json");
		String fileContent = Files.readString(filePath);
        System.out.println(fileContent);
        
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>>data=mapper.readValue(fileContent,new TypeReference<List<HashMap<String,String>>>(){
        });
        
        return data;
        
     }
}
