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

        ArrayList<String> ingredients3 = new ArrayList<String>();
        ingredients3.add("flour");
        ingredients3.add("water");
        ingredients3.add("malt syrup");
        ArrayList<String> instructions3 = new ArrayList<String>();
        instructions3.add("mix flour water and salt");
        instructions3.add("wait for 2-4hours");
        instructions3.add("bake at 450 degrees");
        Recipe bagel = new Recipe("bagel", "great start of the morning", ingredients3, instructions3);
        recipeBook.add(bagel);

        ArrayList<String> ingredients4 = new ArrayList<String>();
        ingredients4.add("potato");
        ingredients4.add("oil");
        ArrayList<String> instructions4 = new ArrayList<String>();
        instructions4.add("cut potato into stripes");
        instructions4.add("heat oil and deep fry potato strips for at leat twice until they have crispy golden looks");
        Recipe fries = new Recipe("fries", "great sides with burger", ingredients2, instructions2);
        recipeBook.add(fries);
        

        FileOutputStream fos = new FileOutputStream("recipeBook.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(recipeBook);

        System.out.println("Created recipeBook file");
        oos.close();
        fos.close();
    }
}
