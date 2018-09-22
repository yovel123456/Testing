package com.Pk;

public class PetrolPurchase {
    private String stationLoc;
    private String petrolType;
    private int quantityInLiters;
    private double pricePerLiter;
    private double percentageDiscount;

    public PetrolPurchase(String stationLoc, String petrolType, int quantityInLiters, double pricePerLiter, double percentageDiscount){
        this.stationLoc = stationLoc;
        this.petrolType = petrolType;
        this.quantityInLiters = quantityInLiters;
        this.pricePerLiter = pricePerLiter;
        this.percentageDiscount = percentageDiscount;
    }

    public void setStationLoc(String stationLoc) { this.stationLoc = stationLoc; }
    public void setPetrolType(String petrolType) { this.petrolType = petrolType; }
    public void setQuantityInLiters(int quantityInLiters) { this.quantityInLiters = quantityInLiters; }
    public void setPricePerLiter(double pricePerLiter) { this.pricePerLiter = pricePerLiter; }
    public void setPercentageDiscount(double percentageDiscount) { this.percentageDiscount = percentageDiscount; }

    public String getStationLoc() { return stationLoc; }
    public String getPetrolType() { return petrolType; }
    public int getQuantityInLiters() { return quantityInLiters; }
    public double getPricePerLiter() { return pricePerLiter; }
    public double getPercentageDiscount() { return percentageDiscount; }

    public double getPurchaseAmount() {
        return (quantityInLiters*pricePerLiter) - (percentageDiscount)*(quantityInLiters * pricePerLiter);
    }
}
