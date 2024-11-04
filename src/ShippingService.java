import java.util.*;

public class ShippingService {
    public static void shipItems(List<Shippable> items) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("\n** Shipment notice **");
        Map<String, Integer> itemCounts = new LinkedHashMap<>();
        Map<String, Double> itemWeights = new LinkedHashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = "Unknown Item";
            double weight = 0;
            if (item instanceof Product) {
                name = ((Product) item).getName();
                weight = item.getWeight();
            }
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.put(name, weight);
            totalWeight += weight;
        }

        for (String name : itemCounts.keySet()) {
            int count = itemCounts.get(name);
            double weightPerItem = itemWeights.get(name) * 1000; // Convert kg to grams
            System.out.println(count + "x " + name + "\t" + (int) (weightPerItem * count) + "g");
        }
        System.out.println("Total package weight " + String.format("%.1f", totalWeight) + "kg\n");
    }
}
