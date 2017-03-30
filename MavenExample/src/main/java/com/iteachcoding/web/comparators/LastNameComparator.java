/**
 * 
 */
package com.iteachcoding.web.comparators;

import java.util.Comparator;

import com.iteachcoding.web.model.Person;

/**
 * @author George Andrews
 *
 */
public class LastNameComparator implements Comparator<Person> {

  @Override
  public int compare(final Person person1, final Person person2) {
    return person1.getLastName().compareTo(person2.getLastName());
  }

}
