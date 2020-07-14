package Warehouses;

import Others.NotEnoughSpaceException;
import Items.Item;

import java.util.ArrayList;

public class Warehouse {
    private int id;
    private double capacity;
    private String location;
    ArrayList<Item> items;

    private static int idGenerator = 1;

    public Warehouse(double capacity, String location){
        this.id = idGenerator++;
        this.capacity = capacity;
        this.location = location;
        items = new ArrayList<>();
    }

    public Warehouse(int id, double capacity, String location){
        this.id = id;
        this.capacity = capacity;
        this.location = location;
        items = new ArrayList<>();
        idGenerator = id;
    }

    private double usedSpace(){
        if(items.size() == 0)
            return 0;
        else {
            return items.stream().mapToDouble(Item::getSize).sum();
        }
    }

    public void addItem(Item item) throws NotEnoughSpaceException {
        if(item.getSize() <= capacity - usedSpace()) {
            items.add(item);
            item.setWarehouseId(id);
        }
        else
            throw new NotEnoughSpaceException("Not enough space in the warehouse");
    }

    public void removeItem(Item p){
        items.remove(p);
    }

    public int fillLevel(){
        return  (int) ((usedSpace()/ capacity)*100);
    }

    public int getId(){
        return id;
    }

    public double getCapacity(){
        return capacity;
    }

    public String getLocation(){
        return location;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    @Override
    public String toString() {
        return "Warehouse " + id + ", location: " + location + ", free space: " + (capacity - usedSpace()) + "m2";
    }

    public String toFile() {
        return id + "&" + capacity + "&" + location + "\n";
    }
}
