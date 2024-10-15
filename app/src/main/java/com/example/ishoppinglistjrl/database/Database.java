package com.example.ishoppinglistjrl.database;

import com.example.ishoppinglistjrl.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Product> productList;

    /**
     * Método que se encarga de inicializar la lista de los prodcutos
     */
    public static void initializeList() {
        if (productList == null) {
            productList = new ArrayList<>();
            //Añadimos 10 prodcutos a la lista
            productList.add(new Product(1, "HP Laptop", "HP laptop with an RTX 4070", true));
            productList.add(new Product(2, "Samsung Galaxy S21 Smartphone", "Phone with a 6.2-inch screen and 128GB of storage", true));
            productList.add(new Product(3, "LG 24'' Monitor", "Full HD monitor with 1920x1080 resolution", false));
            productList.add(new Product(4, "Logitech Mechanical Keyboard", "Backlit mechanical keyboard", true));
            productList.add(new Product(5, "Razer Gaming Mouse", "Gaming mouse with a high-precision optical sensor", true));
            productList.add(new Product(6, "Sony WH-1000XM4 Headphones", "Wireless noise-canceling headphones", true));
            productList.add(new Product(7, "iPad Pro Tablet", "11-inch iPad Pro with 256GB of storage", false));
            productList.add(new Product(8, "Apple Watch Series 7 Smartwatch", "Smartwatch with GPS and health monitoring", true));
            productList.add(new Product(9, "HP LaserJet Printer", "Multifunction laser printer for office use", false));
            productList.add(new Product(10, "Sony Alpha 7 Camera", "Mirrorless camera with a full-frame sensor", true));
        }
    }

    /**
     * Método que busca un prodcuto por id y lo devuelve
     *
     * @param id Id del producto
     * @return El objeto product si lo encunetra o null si no lo encuentra
     */

    public static Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
