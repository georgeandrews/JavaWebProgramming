/**
 * 
 */
package com.iteachcoding.web.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.iteachcoding.web.model.Person;

/**
 * @author George Andrews
 *
 */
public class WorkbookUtility {

	public static final String INPUT_FILE = "/assets/JavaWebProgramming.xlsx";
	
	public static List<Person> retrievePeopleFromWorkbook(final File inputFile) throws InvalidFormatException, IOException {
		
		final List<Person> people = new ArrayList<>();
		
		final Workbook workbook = WorkbookFactory.create(inputFile);
		
		final Sheet sheet = workbook.getSheetAt(0);
		
		for (final Row row : sheet) {
			final Cell firstNameCell = row.getCell(0);
			final Cell lastNameCell = row.getCell(1);
			final Cell ageCell = row.getCell(2);
			final Cell favoriteColorCell = row.getCell(3);
			
			final Person person = new Person(
									firstNameCell.getStringCellValue().trim(), 
									lastNameCell.getStringCellValue().trim(), 
									(int) ageCell.getNumericCellValue(), 
									favoriteColorCell.getStringCellValue().trim());
			
			//NOTES: add each new Person to our List of people
			people.add(person);
		}
		
		return people;
		
	}

}
