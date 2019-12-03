class Pizza {
    private String toppings;
    private static int numPizzas = 0;

    public Pizza() {
        this("cheese");
    }

    public Pizza(String toppings) {
        this.toppings = toppings;
        numPizzas++;
    }

    public String getToppings() {
        if (this.toppings == null)
            this.toppings = "no toppings";
        return this.toppings;
    }

    void setToppings(String newToppings) {
        if (!newToppings.contains("anchovies")) {
            this.toppings = newToppings;
        }
    }

    public String toString() {
        return this.toppings;
    }

    public static int numPizzas() {
        return numPizzas;
    }
}
