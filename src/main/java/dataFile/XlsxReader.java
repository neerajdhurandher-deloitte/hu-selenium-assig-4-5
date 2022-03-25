package dataFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XlsxReader {
    String filePath;
    ArrayList<User> userList = new ArrayList<>();

    public XlsxReader (String path){
        this.filePath = path;
    }

    public ArrayList<User> readFile() throws IOException {

        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet workbookSheet = workbook.getSheet("sheet1");

        int rowCount = workbookSheet.getLastRowNum()-workbookSheet.getFirstRowNum();

        for (int i = 1; i < rowCount+1; i++) {

            Row row = workbookSheet.getRow(i);

            User user = new User();

            for (int j = 0; j < row.getLastCellNum(); j++) {
                String data = row.getCell(j).getStringCellValue();

                switch (j){
                    case 0 -> user.setUserName(data);
                    case 1-> user.setPassword(data);
                }
            }
            userList.add(user);
//            System.out.println(user.getUserName()+" " + user.getPassword());
        }

        return userList;
    }
}
