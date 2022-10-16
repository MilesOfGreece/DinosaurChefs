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

    public Recipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.instructionssize = instructions.size();
        this.ingredientssize = ingredients.size();
    }


    public void printIngredients(ArrayList<String> x, int y) {
        for(int i = 0; i < y; i++) {
            System.out.println(x.get(i));
        }
    }

    public void printEverything() {
        System.out.println("Recipe Name: " + name);
        System.out.println("Recipe Description: " + description + "\n");
        System.out.println("Ingredients:");
        for (int i = 0; i < ingredientssize; i++) {
            int num = i + 1;
            System.out.println(num + ".) " + ingredients.get(i));
            num++;
        }
        System.out.println("\nInstructions:");
        for (int i = 0; i < instructionssize; i++) {
            int num = i + 1;
            System.out.println(num + ".) " + instructions.get(i));
            num++;
        }
        System.out.println("\n");
    }


    public String toString(){
        return "Recipe Name: " + name + "\nRecipe Description: " + description + "\nIngredients: " + ingredients + "\nInstructions: " + instructions;
    }
}
