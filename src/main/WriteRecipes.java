package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteRecipes {


    public static void main(String[] args) throws IOException {
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("hydrogen");
        ingredients.add("oxygen");
        ArrayList<String> instructions = new ArrayList<String>();
        instructions.add("prepare 2 hydrogen atoms and 1 oxygen atom");
        instructions.add("when they bond, they form H2O, or water");
        Recipe water = new Recipe("Water", "its a drink", ingredients, instructions);
        ArrayList<Recipe> recipeBook = new ArrayList<>();
        recipeBook.add(water);
        //water.printEverything();

        ArrayList<String> ingredients2 = new ArrayList<String>();
        ingredients2.add("instant ramen");
        ingredients2.add("water");
        ArrayList<String> instructions2 = new ArrayList<String>();
        instructions2.add("boil water");
        instructions2.add("add ramen");
        Recipe ramen = new Recipe("Ramen", "instant ramen", ingredients2, instructions2);
        recipeBook.add(ramen);


        FileOutputStream fos = new FileOutputStream("recipeBook.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(recipeBook);

        System.out.println("Created recipeBook file");
        oos.close();
        fos.close();
    }
}
