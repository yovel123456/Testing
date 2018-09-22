package com.Pk;

public class Main {

    public static void main(String[] args) {

        PetrolPurchase pp = new PetrolPurchase("Afula","Benzin", 5, 25, 0.1);
        System.out.printf("The Station Location is: %s\n The Petrol Type is: %s\n The Quantity in liters is: %d\n The Price per liter is: %s\n The Percentage Discount is: %s\n The Purchase Amount is: %s"
                , pp.getStationLoc(), pp.getPetrolType(), pp.getQuantityInLiters(), pp.getPricePerLiter(), pp.getPercentageDiscount(), pp.getPurchaseAmount());
    }
}
