package Items;

public class Item {
    private int id;
    private String name;
    private double size;
    private String description;
    private int warehouseId;

    private static int idGenerator = 1;

    public Item(String name, double size, String description){
        this.id = idGenerator++;
        this.name = name;
        this.size = size;
        this.description = description;
        warehouseId = 0;
    }

    public Item(int id, String name, double size, String description, int warehouseId){
        this.id = id;
        this.name = name;
        this.size = size;
        this.description = description;
        this.warehouseId = warehouseId;
        idGenerator++;
    }

    @Override
    public String toString() {
        return "Item " + id + ", " + name + ", " + size + "m2, warehouse: " + (warehouseId == 0 ? "NONE": warehouseId) + ", description: " + description;
    }

    public String toFile() {
        return id + "&" + name + "&" + size + "&" + description + "&" + warehouseId + "\n";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    public int getWarehouseId(){
        return warehouseId;
    }

    public void setWarehouseId(int id){
        this.warehouseId = id;
    }

}
