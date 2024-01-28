package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class excelUtils {

    @DataProvider(name = "testData")
    public static Object[][] getDataFromDataProvider(Method m) {

        if (m.getName().contains("login")) {
            return dataFromExcel("login");
        } else if (m.getName().contains("search")) {
            return dataFromExcel("search");
        } else {
            return dataFromExcel("register");
        }

    }

    private static Object[][] dataFromExcel(String sheetName) {

        Object[][] excelData;
        DataFormatter dataFormatter = new DataFormatter();
        dataFormatter.setUseCachedValuesForFormulaCells(true);

        try {

            InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/TestData.xls"));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();

            excelData = new Object[rowCount][columnCount];

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    excelData[i - 1][j] = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                }
            }

            workbook.close();
            inputStream.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return excelData;
    }

    public static void excelWriter(String sno, String columnName, String result) {

        File xlsFile = new File("test-output/TestOutput.xls");

        try {

            BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(xlsFile.toPath()));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("searchResult");
            DataFormatter dataFormatter = new DataFormatter();

            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                if (dataFormatter.formatCellValue(sheet.getRow(0).getCell(j)).contains(columnName)) {
                    sheet.getRow(Integer.parseInt(sno.trim())).createCell(j).setCellValue(result);
                }
            }

            // Creating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(xlsFile);
            workbook.write(os);
            os.flush();
            workbook.close();
            inputStream.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
