package main;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadRecipes {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("recipeBook.ser");
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Recipe> recipeBook = new ArrayList<>();
            recipeBook = (ArrayList<Recipe>) ois.readObject();

            for (int i = 0; i < recipeBook.size(); i++) {
                //System.out.println(recipeBook.get(i)); //this will call toString()
                recipeBook.get(i).printEverything();
                System.out.println("##############################################################");
            }
        }
    }
}
