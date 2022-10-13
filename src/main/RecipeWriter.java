package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeWriter {
    public static void menu(){
        System.out.println("==============================================================!");
        System.out.println("Welcome to Charlotte and Miles' Recipe Book!");
        System.out.println("What would you like to do? Type one of the following options:");
        System.out.println("1.) Create a new recipe");
        System.out.println("2.) View recipe");
        System.out.println("3.) Quit");
        System.out.print("Type your choice here: ");
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.print("Initializing recipe book......."); //load in recipe book to retrieve existing recipes

        FileInputStream fis = new FileInputStream("recipeBook.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ArrayList<Recipe> recipeBook = new ArrayList<>();
        recipeBook = (ArrayList<Recipe>) ois.readObject();
        ois.close();
        fis.close();

        System.out.println("Success!");
        menu(); // display main menu 

        Scanner scan = new Scanner(System.in);
        String input1 = scan.next();

        while(!input1.toLowerCase().equals("3")) { //master while loop, terminates when we quit

            System.out.println("You typed: " + input1);
            if (input1.equals("1") || input1.toLowerCase().equals("create")) { //create a recipe

                System.out.println("----------------------------------------");
                System.out.println("Name of recipe you want to create: ");
                String input2 = scan.nextLine(); //input2 = name
                input2 = input2 + scan.nextLine();
                //System.out.println();
                

                System.out.println("----------------------------------------");
                System.out.println("Write a description for your recipe: ");
                String input3 = scan.nextLine(); //input3 = description
                input3 = input3 + scan.nextLine();
                //System.out.println();

                Integer ingredients_num = 0;
                System.out.println("----------------------------------------");
                System.out.print("Type the number of ingredients:");
                ingredients_num = Integer.parseInt(scan.nextLine());
                ArrayList<String> myIngredients = new ArrayList<>();
                for(int i=1; i<=ingredients_num; i++) {
                    System.out.print(i + ": "); 
                    myIngredients.add(scan.nextLine());
                }

                Integer instructions_num = 0;
                System.out.println("----------------------------------------");
                System.out.println("Type the number of instructions:");
                instructions_num = Integer.parseInt(scan.nextLine());
                ArrayList<String> myInstructions = new ArrayList<>();
                for(int i=1; i<=instructions_num; i++) {
                    System.out.print(i + ": "); 
                    myInstructions.add(scan.nextLine());
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
                menu();
                input1 = scan.next(); //set their response to while loop variable
            }

            if (input1.equals("2")) {
                
                System.out.println("----------------------------------------");
                System.out.println("Would you like to search by name, or select from list? Type one of the following options: ");
                System.out.println("1.) Search by name");
                System.out.println("2.) Select from list");
                Scanner scan2 = new Scanner(System.in); //make a scanner object to take in user input
                String userSearch = scan2.next();

                if (userSearch.equals("1")) {
                    System.out.println("----------------------------------------");
                    System.out.println("Type your recipe name here: ");
                    userSearch = scan2.next();
                    for (int i = 0; i < recipeBook.size(); i++) {
                        String x = recipeBook.get(i).name;
                        if (x.toLowerCase().equals(userSearch.toLowerCase())) {
                            Recipe found_recipe = recipeBook.get(i); 
                            System.out.println("Found a recipe for " + userSearch + "!");
                            System.out.println("");
                            System.out.println("Would you like to view instruction steps one by one? Or just view the entire recipe?");
                            System.out.println("Type one of the following options:");
                            System.out.println("1.) One by one");
                            System.out.println("2.) View the entire recipe");
                            userSearch = scan2.next();
                            // display whole recipe
                            if (userSearch.equals("2")) {
                                System.out.println("----------------------------------------");
                                found_recipe.printEverything();
                            }
                            //display instructions one by one
                            else if(userSearch.equals("2")){
                                System.out.println("-----------------------------------------");


                            }
                        }
                    }
                    scan2.close(); // close scanner 
                }
                else if (userSearch.equals("2")) {
                    System.out.println("Recipes:");
                    for (int i = 0; i < recipeBook.size()-1; i++) {
                        System.out.println( i+1 + ".)"+ recipeBook.get(i+1).name ); 
                    }
                    System.out.println("Type(number):");
                    int r_num = scan2.nextInt();
                    Recipe chosen_Recipe = recipeBook.get(r_num);
                    System.out.println("Found a recipe for " + chosen_Recipe.name + "!");
                    System.out.println("");
                    System.out.println("Would you like to view instruction steps one by one? Or just view the entire recipe?");
                    System.out.println("Type one of the following options:");
                    System.out.println("1.) One by one");
                    System.out.println("2.) View the entire recipe");
                    userSearch = scan2.next();
                            // display whole recipe
                            if (userSearch.equals("2")) {
                                System.out.println("----------------------------------------");
                                chosen_Recipe.printEverything();
                            }
                            //display instructions one by one
                            else if(userSearch.equals("2")){
                                System.out.println("----------------------------------------");
                                chosen_Recipe.printAll_noInstruction();
                                System.out.println("Instructions:");
                                int count_instructions = 0;
                                while (count_instructions < chosen_Recipe.getInstructions().size()) {
                                    System.out.println("Press return to continue.(enter 'exit' to stop)");
                                    String leave = scan2.nextLine();
                                    if (leave.toLowerCase().equals("exit")) {
                                         break;
                                    } 
                                    else {
                                        System.out.println(chosen_Recipe.getInstructions().get(count_instructions));
                                        System.out.println("");
                                        count_instructions++;
                                    }
                                }
                            }

                }
            
                else{
                    System.out.println("Invalid input");
                }

                menu();  // continue prompting menu
                input1 = scan.next(); //set their response to while loop variable
            }
        }
        scan.close(); // close the scanner
    }
}
