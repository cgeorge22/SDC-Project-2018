import java.util.ArrayList;

public class MakePizza {
    public static void main(String[] args) {
        Pizza one = new Pizza();
        one.setToppings("(bacon, pineapple)");
        Pizza two = new Pizza("pepperoni");
        //two.setToppings("(sausage, pep)");
        ArrayList<Pizza> pizzas = new ArrayList<>();
        pizzas.add(one);
        pizzas.add(two);
        System.out.println(pizzas);
        System.out.println(Pizza.numPizzas());
    }
}
