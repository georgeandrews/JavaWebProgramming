/**
 * 
 */
package com.iteachcoding.web.dao;

import java.util.List;

import com.iteachcoding.web.dao.impl.PersonDaoException;
import com.iteachcoding.web.model.Person;

/**
 * @author George Andrews
 *
 */
public interface PersonDao {
  
  void populate(String filePath) throws PersonDaoException;
  
  List<Person> retrievePeople() throws PersonDaoException;

  void insertPerson(Person person) throws PersonDaoException;
  
}
