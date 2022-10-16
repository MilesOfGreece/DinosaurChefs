package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeWriter {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.print("Initializing recipe book......."); //load in recipe book to retrieve existing recipes

        FileInputStream fis = new FileInputStream("recipeBook.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ArrayList<Recipe> recipeBook = new ArrayList<>();
        recipeBook = (ArrayList<Recipe>) ois.readObject();
        ois.close();
        fis.close();

        System.out.println("Success!");

        System.out.println("Welcome to Charlotte and Miles' Recipe Book!");
        System.out.println("What would you like to do? Type one of the following options:");
        System.out.println("1.) Create");
        System.out.println("2.) View");
        System.out.println("3.) Quit");
        System.out.print("Type here: ");

        Scanner scan = new Scanner(System.in); //make a scanner object to take in user input
        String input1 = scan.next();

        while(!input1.toLowerCase().equals("3")) { //master while loop, terminates when we quit

            System.out.println("You typed: " + input1);
            if (input1.equals("1") || input1.toLowerCase().equals("create")) { //create a recipe

                System.out.print("Name of recipe you want to create: ");
                String input2 = scan.next(); //input2 = name
                input2 = input2 + scan.nextLine();
                System.out.println();
    
                System.out.print("Write a description for your recipe: ");
                String input3 = scan.next(); //input3 = description
                input3 = input3 + scan.nextLine();
                System.out.println();
    
                System.out.println("List your ingredients, type \"done\" to finish: ");
                String input4 = scan.next();
                ArrayList<String> myIngredients = new ArrayList<>();
                while (!input4.equals("done")){
                    input4 = input4 + scan.nextLine();
                    myIngredients.add(input4);
                    input4 = scan.next();
                }
    
                System.out.println("Now list your instructions, type \"done\" to finish: ");
                String input5 = scan.next();
                ArrayList<String> myInstructions = new ArrayList<>();
                while (!input5.equals("done")){
                    input5 = input5 + scan.nextLine();
                    myInstructions.add(input5);
                    input5 = scan.next();
                }
    
                Recipe userRecipe = new Recipe(input2, input3, myIngredients, myInstructions); //create a recipe object
                recipeBook.add(userRecipe); //add the object to the recipebook
                FileOutputStream fos = new FileOutputStream("recipeBook.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(recipeBook); //write the recipeBook to the file
    
                oos.close();
                fos.close();
                System.out.println("Wrote to recipe book!");
    
                // System.out.println("Name = " + input2);
                // System.out.println("Description = " + input3);
                // System.out.println("Ingredients: " + myIngredients);
                // System.out.println("Instructions: " + myInstructions);
    
                //prompt user to continue using program
                System.out.println("Would you like to do anything else? Type one of the following options:");
                System.out.println("1.) Create");
                System.out.println("2.) View");
                System.out.println("3.) Quit");
                System.out.print("Type here: ");
                input1 = scan.next(); //set their response to while loop variable
            }

            if (input1.equals("2") || input1.toLowerCase().equals("view")) {

                System.out.println("Would you like to search by name, or select from list? Type one of the following options: ");
                System.out.println("1.) Search");
                System.out.println("2.) Select from list");
                Scanner scan2 = new Scanner(System.in); //make a scanner object to take in user input
                String userSearch = scan2.next();

                if (userSearch.equals("1") || userSearch.toLowerCase().equals("search")) {
                    System.out.println("Type your recipe name here: ");
                    userSearch = scan2.next();
                    userSearch = userSearch + scan2.nextLine();
                    for (int i = 0; i < recipeBook.size(); i++) {
                        String x = recipeBook.get(i).name;
                        if (x.toLowerCase().equals(userSearch.toLowerCase())) {
                            System.out.println("Found a recipe for " + userSearch + "!");
                            System.out.println("Would you like to view ingredients one by one? Or just view the entire recipe?");
                            System.out.println("Type one of the following options:");
                            System.out.println("1.) One by one");
                            System.out.println("2.) View the entire recipe");
                            userSearch = scan2.next();
                            if (userSearch.equals("1")) {
                                System.out.println("Recipe Name: " + x);
                                System.out.println("Description: " + recipeBook.get(i).description);
                                System.out.println("Ingredients: " + recipeBook.get(i).ingredients);
                                System.out.println("Type any character to read next step: ");
                                userSearch = scan2.next();
                                int counter = 0;
                                while (counter < recipeBook.get(i).instructionssize) {
                                    System.out.println("Step " + (counter + 1) + ": " + recipeBook.get(i).instructions.get(counter));
                                    counter++;
                                    userSearch = scan2.next();
                                }
                            } else if (userSearch.equals("2")){
                                recipeBook.get(i).printEverything();
                            }
                        }
                    }
                }
                else if (userSearch.equals("2") || userSearch.toLowerCase().equals("select from list") || userSearch.toLowerCase().equals("select")) {
                    System.out.println("Type the recipe or number you'd like");
                    System.out.println("Recipes:");
                    for (int i = 0; i < recipeBook.size(); i++) {
                        System.out.println( i+1 + ".)"+ recipeBook.get(i).name ); 
                    }
                    System.out.println("Type:");
                    //int r_num = scan2.nextInt();
                    String r_String = scan2.next();
                    r_String = r_String + scan2.nextLine();
                    char first = r_String.charAt(0);
                    if (Character.isDigit(first)) {
                        int r_num = Integer.parseInt(r_String);
                        r_num--;

                        if (r_num < recipeBook.size()) {
                            System.out.println("Found a recipe for " + recipeBook.get(r_num).name + "!");
                            recipeBook.get(r_num).printEverything();
                            System.out.println("#######################################################\n");
                        }
                        else {
                            System.out.println("Error: The number you typed is not in your recipe book!");
                        }
    


                    } else {
                        boolean myBool = false;
                        for (int i = 0; i < recipeBook.size(); i++) {
                            //System.out.println("Index " + i + " name: " + recipeBook.get(i).name.toLowerCase() + " | " + r_String.toLowerCase());
                            if (r_String.toLowerCase().equals(recipeBook.get(i).name.toLowerCase())) {
                                System.out.println("Found a recipe for " + recipeBook.get(i).name + "!\n");
                                recipeBook.get(i).printEverything();
                                System.out.println("#######################################################\n");
                                myBool = true;
                            }
                        }

                        if (myBool == false) {
                            System.out.println("Could not find such a recipe! Please try again");
                        }
                    }

                    // if (r_num < recipeBook.size()) {
                    //     System.out.println("Found a recipe for " + recipeBook.get(r_num).name + "!");
                    //     recipeBook.get(r_num).printEverything();
                    //     System.out.println("#######################################################\n");
                    // }
                    // else {
                    //     System.out.println("Error: The number you typed is not in your recipe book!");
                    // }
                    // recipeBook.get(0).printEverything();
                    // for (int i = 0; i < recipeBook.size(); i++) {
                    //     System.out.println("Index " + i + " name: " + recipeBook.get(i).name);
                    //     if (r_String.toLowerCase().equals(recipeBook.get(i).name.toLowerCase())) {
                    //         System.out.println("Found a recipe for " + recipeBook.get(i).name + "!");
                    //         recipeBook.get(i).printEverything();
                    //         System.out.println("#######################################################\n");
                    //     }
                    // }
                    //aaaaaaa
                    // System.out.println("Found a recipe for " + recipeBook.get(r_num).name + "!");
                    // recipeBook.get(r_num).printEverything();
                    // System.out.println("#######################################################\n");
                }

                // System.out.println("Recipes:");
                // System.out.println("#######################################################");
                // for (int i = 0; i < recipeBook.size(); i++) {
                //     //System.out.println(recipeBook.get(i)); //this will call toString()
                //     System.out.println("Recipe " + (i + 1) + ":");
                //     System.out.println(recipeBook.get(i).name);
                //     //recipeBook.get(i).printEverything();
                //     System.out.println("##############################################################");
                // }

                //prompt user to continue using program
                System.out.println("Would you like to do anything else? Type one of the following options:");
                System.out.println("1.) Create");
                System.out.println("2.) View");
                System.out.println("3.) Quit");
                System.out.print("Type here: ");
                input1 = scan.next(); //set their response to while loop variable

            }
        }
        

        
    }
}
