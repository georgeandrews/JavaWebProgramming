/**
 * 
 */
package com.iteachcoding.web.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.iteachcoding.web.model.User;

/**
 * @author George Andrews
 *
 */
public class UserIO {

  public static boolean add(final User user, final String filepath) {
    try {
      final File file = new File(filepath);
      final PrintWriter out = new PrintWriter(new FileWriter(file, true));
      out.println(user.getEmail() + "|"
          + user.getFirstName() + "|"
          + user.getLastName());

      out.close();
      return true;
    } catch (final IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static User getUser(final String email, final String filepath) {
    try {
      final BufferedReader reader = new BufferedReader(new FileReader(filepath));
      User user = null;
      String line = reader.readLine();
      while (line != null) {
        user = new User();

        final StringTokenizer tokenizer = new StringTokenizer(line, "|");
        if (tokenizer.countTokens() < 3) {
          return null;
        }
        final String token = tokenizer.nextToken();
        if (token.equalsIgnoreCase(email)) {
          final String firstName = tokenizer.nextToken();
          final String lastName = tokenizer.nextToken();
          user.setEmail(email);
          user.setFirstName(firstName);
          user.setLastName(lastName);
        }
        line = reader.readLine();
      }
      reader.close();
      return user;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static ArrayList<User> getUsers(final String filepath) {
    try {
      final ArrayList<User> users = new ArrayList<User>();
      final BufferedReader reader = new BufferedReader(new FileReader(filepath));

      String line = reader.readLine();
      while (line != null) {
        final StringTokenizer tokenizer = new StringTokenizer(line, "|");
        final String email = tokenizer.nextToken();
        final String firstName = tokenizer.nextToken();
        final String lastName = tokenizer.nextToken();
        final User user = new User(firstName, lastName, email);
        users.add(user);
        line = reader.readLine();
      }
      reader.close();
      return users;
    } catch (IOException e) {
      System.out.println(e);
      return null;
    }
  }
}