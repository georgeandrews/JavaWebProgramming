/**
 * 
 */
package com.iteachcoding.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.iteachcoding.web.model.Person;
import com.iteachcoding.web.util.WorkbookUtility;

/**
 * @author George Andrews
 *
 */
public class JavaIOExample {

	private static final String INPUT_FILE = "WebContent/assets/JavaWebProgramming.xlsx";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// NOTES: Use an input file to print a list of people to the Console
		final File inputFile = new File(INPUT_FILE);
		
		try {
			final List<Person> people = WorkbookUtility.retrievePeopleFromWorkbook(inputFile);
			
			for (final Person person : people) {
				if (person.getFavoriteColor().equalsIgnoreCase("Green")) {
					System.out.println(person);
				}
			}
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
