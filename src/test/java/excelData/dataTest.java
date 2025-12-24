package excelData;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataTest {

	

		

    public ArrayList<String> getdata(String testCaseName) throws IOException {
    	
    	// Identify TestCase column by scanning the entire 1st row.
    			// once column is identified then scan the entire test column to verify purchase
    			// after you grab purchase TestCaase row = pull all the data of that row and fed
    			// into test
    	
    	ArrayList<String> array = new ArrayList<String> ();
    	
		FileInputStream file = new FileInputStream("C:\\Users\\siddh\\Documents\\Book1.xlsx");
		// fileInput argument to fetch that file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheets = workbook.getNumberOfSheets();

		// identify the first sheet of the file
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify TestCase column by scanning the entire 1st row.
				Iterator<Row> rows = sheet.iterator(); // sheet is collection of row
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator(); // row is collection of cell
				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase(testCaseName)) {
						column = k;
						System.out.println(value.getStringCellValue()+" at index "+column);
						break;
					}
					k++;
				}
				//System.out.println(column);

				// once column is identified then scan the entire test column to verify purchase

				while (rows.hasNext())
					
				{
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {

						// after you grab purchase TestCase row = pull all the data of that row and fed
						Iterator<Cell> it = r.cellIterator();
						while (it.hasNext()) {
							Cell c=it.next();
							if(c.getCellType()==CellType.STRING) {
							array.add(c.getStringCellValue());
							}
							else {
								array.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								//array.add(c.getNumericCellValue());
							}
						}
						 

					}
				}

			}
		}
		return array;
    }
  

	}


