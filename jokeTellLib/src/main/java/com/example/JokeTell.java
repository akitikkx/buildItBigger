package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeTell {

    private List<String> jokes = new ArrayList<>();
    private String joke;

    public String getRandomJoke() {
        generateJokeList();

        if (jokes.size() > 0) {
            Random random = new Random();
            joke = jokes.get(random.nextInt(jokes.size()));
        }
        return joke;
    }

    private void generateJokeList() {
        jokes.add("Why does a chicken coop have two doors? Because if it had four doors it would be a chicken sedan.");
        jokes.add("How do you find Will Smith in the snow? Look for the fresh prints.");
        jokes.add("What do you get when you cross the Atlantic with the Titanic?About half way.");
    }

}
