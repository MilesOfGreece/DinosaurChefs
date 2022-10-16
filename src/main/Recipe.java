package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    
    String name;
    String description;
    ArrayList<String> ingredients = new ArrayList<>();
    ArrayList<String> instructions = new ArrayList<>();
    int instructionssize = instructions.size();
    int ingredientssize = ingredients.size();

    // constructor
    public Recipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.instructionssize = instructions.size();
        this.ingredientssize = ingredients.size();
    }
   
    // getter for instructions
    public ArrayList<String> getInstructions(){
        return this.instructions;
    }

    // print ingredients 
    public void printIngredients(ArrayList<String> x, int y) {
        for(int i = 0; i < y; i++) {
            System.out.println(x.get(i));
        }
    }
    // print recipe without steps
    public void printAll_noInstruction(){
        System.out.println("Recipe Name: " + name);
        System.out.println("Recipe Description: " + description);
        System.out.println("Ingredients:");
        for (int i = 0; i < ingredientssize; i++) {
            int num = i + 1;
            System.out.println(num + ".) " + ingredients.get(i));
            num++;
        }
    }
    // print whole recipe
    public void printEverything() {
        System.out.println("Recipe Name: " + name);
        System.out.println("Recipe Description: " + description);
        System.out.println("Ingredients:");
        for (int i = 0; i < ingredientssize; i++) {
            int num = i + 1;
            System.out.println(num + ".) " + ingredients.get(i));
            num++;
        }
        System.out.println("Instructions:");
        for (int i = 0; i < instructionssize; i++) {
            int num = i + 1;
            System.out.println(num + ".) " + instructions.get(i));
            num++;
        }
    }


    public String toString(){
        return "Recipe Name: " + name + "\nRecipe Description: " + description + "\nIngredients: " + ingredients + "\nInstructions: " + instructions;
    }
}
