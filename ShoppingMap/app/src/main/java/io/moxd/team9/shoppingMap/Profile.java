package io.moxd.team9.shoppingMap;

public class Profile {

    private String name, favoriteShop, description;


    public Profile(){

    }

    public Profile(String name, String favoriteShop, String description) {
        this.name = name;
        this.favoriteShop = favoriteShop;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getFavoriteShop() {
        return favoriteShop;
    }

    public String getDescription() {
        return description;
    }
}
