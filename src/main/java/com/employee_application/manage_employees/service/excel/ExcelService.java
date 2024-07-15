package com.employee_application.manage_employees.service.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.model.finance.Finance;
import com.employee_application.manage_employees.service.project.ProjectService;

@Service
public class ExcelService {
	
	@Value("${spring.excel.book.location}")
	private String FILE_PATH;
	
	@Autowired
	private ProjectService projectService;

	// Get all records from Excel sheet and populate that in H2
	public List<Finance> getAllFinaceRecords() {
        List<Finance> financeRecords = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Finance finance = new Finance();
                finance.setId((long) row.getCell(0).getNumericCellValue());
                finance.setProject(projectService.getProjectByName(row.getCell(1).getStringCellValue())); // Set this based on your application logic
                finance.setDate(row.getCell(2).getDateCellValue());
                finance.setExpenseType(row.getCell(3).getStringCellValue());
                finance.setDescription(row.getCell(4).getStringCellValue());
                finance.setAmount(row.getCell(5).getNumericCellValue());
                finance.setPaidTo(row.getCell(6).getStringCellValue());
                finance.setPaymentMethod(row.getCell(7).getStringCellValue());
                finance.setInvoiceNumber((long) row.getCell(8).getNumericCellValue());
                finance.setApprovalStatus(row.getCell(9).getStringCellValue());
                financeRecords.add(finance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return financeRecords;
    }
	
	// Create a new row in Excel sheet from Finance object
	public void addFinanceRecord(Finance finance) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row row = sheet.createRow(rowCount);

            row.createCell(0).setCellValue(finance.getId());
            row.createCell(1).setCellValue(finance.getProject() != null ? finance.getProject().getName() : ""); // Assuming Project has a getName() method
            row.createCell(2).setCellValue(finance.getDate());
            row.createCell(3).setCellValue(finance.getExpenseType());
            row.createCell(4).setCellValue(finance.getDescription());
            row.createCell(5).setCellValue(finance.getAmount());
            row.createCell(6).setCellValue(finance.getPaidTo());
            row.createCell(7).setCellValue(finance.getPaymentMethod());
            row.createCell(8).setCellValue(finance.getInvoiceNumber());
            row.createCell(9).setCellValue(finance.getApprovalStatus());

            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	// Update the sheet using object
	public void updateFinanceRecord(Finance finance) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if ((long) row.getCell(0).getNumericCellValue() == finance.getId()) {
                    row.getCell(1).setCellValue(finance.getProject() != null ? finance.getProject().getName() : "");
                    row.getCell(2).setCellValue(finance.getDate().toString());
                    row.getCell(3).setCellValue(finance.getExpenseType());
                    row.getCell(4).setCellValue(finance.getDescription());
                    row.getCell(5).setCellValue(finance.getAmount());
                    row.getCell(6).setCellValue(finance.getPaidTo());
                    row.getCell(7).setCellValue(finance.getPaymentMethod());
                    row.getCell(8).setCellValue(finance.getInvoiceNumber());
                    row.getCell(9).setCellValue(finance.getApprovalStatus());
                    break;
                }
            }
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	// Delete 
	public void deleteFinanceRecord(long id) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if ((long) row.getCell(0).getNumericCellValue() == id) {
                    sheet.removeRow(row);
                    break;
                }
            }
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void createSheetWithFinances(List<Finance> finances) {
		for (Finance finance : finances) {
			addFinanceRecord(finance);
		}
	}
	
	public void initialiseSheet() {
		try (FileInputStream fis = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            row.getCell(0).setCellValue("Id");
            row.getCell(1).setCellValue("Project");
            row.getCell(2).setCellValue("Date");
            row.getCell(3).setCellValue("Expense type");
            row.getCell(4).setCellValue("Description");
            row.getCell(5).setCellValue("Amount");
            row.getCell(6).setCellValue("Paid to");
            row.getCell(7).setCellValue("Payment method");
            row.getCell(8).setCellValue("Invoice Number");
            row.getCell(9).setCellValue("Approval status");
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
