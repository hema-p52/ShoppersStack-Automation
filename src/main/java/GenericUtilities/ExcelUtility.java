package GenericUtilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelUtility {

    @DataProvider
    public Object[][] getMultipleData() throws Exception {

        FileInputStream fis =
                new FileInputStream("./src/resources/testScriptData.xlsx");

        Workbook wb = WorkbookFactory.create(fis);

        Sheet sh = wb.getSheet("LoginData");

        int rows = sh.getPhysicalNumberOfRows();
        int columns = sh.getRow(0).getLastCellNum();

        System.out.println("Rows = " + rows);
        System.out.println("Columns = " + columns);

        Object[][] data = new Object[rows - 1][columns];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < rows; i++) {

            Row row = sh.getRow(i);

            for (int j = 0; j < columns; j++) {

                data[i - 1][j] =
                        formatter.formatCellValue(row.getCell(j));

                System.out.print(
                        "Column " + j + " = "
                        + data[i - 1][j] + " | "
                );
            }

            System.out.println();
        }

        wb.close();
        fis.close();

        return data;
    }
}
