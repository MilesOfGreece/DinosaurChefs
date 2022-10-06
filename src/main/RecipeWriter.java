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

            if (input1.equals("2")) {

                System.out.println("Recipe Book:");
                System.out.println("#######################################################");
                for (int i = 0; i < recipeBook.size(); i++) {
                    //System.out.println(recipeBook.get(i)); //this will call toString()
                    recipeBook.get(i).printEverything();
                    System.out.println("##############################################################");
                }

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
