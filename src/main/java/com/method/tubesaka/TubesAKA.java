package com.method.tubesaka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TubesAKA {

    private List<Makanan> daftarMakanan = new ArrayList<>();
    private DefaultTableModel table;
    private JLabel searchTimeLabel;
    
    public TubesAKA() {
        daftarData();
        
        
        JFrame frame = new JFrame("Find Allergen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(135, 206, 250));

        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
        panel.setLayout(new GridLayout(6, 1));
        panel.setBackground(new Color(43, 84, 126));

        JLabel label = new JLabel("Masukkan Nama Allergen:");
        label.setForeground(Color.CYAN);

        JTextField inputan = new JTextField(20);
        
        JButton showAllData = new JButton("Tampilkan Semua Data");
        showAllData.setBackground(new Color(72, 199, 112));
        showAllData.setForeground(Color.WHITE);

        JButton recursive = new JButton("Pencarian secara Rekursif");
        recursive.setBackground(new Color(21, 105, 199));
        recursive.setForeground(Color.WHITE);

        JButton iterative = new JButton("Pencarian secara Iteratif");
        iterative.setBackground(new Color(72, 138, 199));
        iterative.setForeground(Color.WHITE);

        searchTimeLabel = new JLabel("Search Time: Not Started");
        searchTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchTimeLabel.setForeground(Color.WHITE);
       

        panel.add(label);
        panel.add(inputan);
        panel.add(showAllData);
        panel.add(recursive);
        panel.add(iterative);
        panel.add(searchTimeLabel);

        frame.add(panel, BorderLayout.NORTH);

        
        String[] namaKolom = {"No", "Nama Makanan", "Bahan Makanan", "Alergen"};
        table = new DefaultTableModel(namaKolom, 0);
        JTable tabel = new JTable(table);
        JScrollPane scroll = new JScrollPane(tabel);
        frame.add(scroll, BorderLayout.CENTER);
        
        showAllData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllData();
                JOptionPane.showMessageDialog(frame, "Semua data telah ditampilkan.");
            }
        });

        
        
        recursive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = inputan.getText().trim();
                if (!keyword.isEmpty()) {
                    long startTime = System.nanoTime();
                    clearTable();
                    searchRecursive(keyword, 0);
                    long endTime = System.nanoTime();
                    updateSearchTime(startTime, endTime);
                } else {
                    JOptionPane.showMessageDialog(frame, "Silahkan masukkan nama allergen.");
                }
            }
        });

        
        iterative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = inputan.getText().trim();
                if (!keyword.isEmpty()) {
                    long startTime = System.nanoTime();
                    clearTable();
                    searchIterative(keyword);
                    long endTime = System.nanoTime();
                    updateSearchTime(startTime, endTime);
                } else {
                    JOptionPane.showMessageDialog(frame, "Silahkan masukkan nama allergen.");
                }
            }
        });
        
        frame.setVisible(true);
    }

    private void daftarData() {
 List<Makanan> makananList = List.of(
        new Makanan("Almond Cookies", List.of("Almonds"), List.of("Almonds", "Wheat", "Dairy")),
        new Makanan("Chicken Noodle Soup", List.of("Chicken broth"), List.of("Chicken", "Wheat", "Celery")),
        new Makanan("Cheddar Cheese", List.of("Cheese"), List.of("Dairy")),
        new Makanan("Ranch Dressing", List.of("Butter Milk"), List.of("Garlic", "Herbs")),
        new Makanan("Caramel Popcorn", List.of("Popcorn"), List.of("Dairy")),
        new Makanan("Caesar Salad", List.of("Romaine Lettuce"), List.of("Dairy")),
        new Makanan("Caesar Wrap", List.of("Grilled Chicken"), List.of("Dairy")),
        new Makanan("Chicken Caesar Salad", List.of("Chicken breast"), List.of("Dairy", "Anchovies")),
        new Makanan("Chocolate Mousse", List.of("Chocolate"), List.of("Dairy", "cocoa")),
        new Makanan("Quiche Lorraine", List.of("Bacon"), List.of("Dairy", "Eggs")),
        new Makanan("Chicken Piccata", List.of("Almonds"), List.of("Dairy", "Fish")),
        new Makanan("Chicken Tikka Masala", List.of("Cheese"), List.of("Dairy", "Ghee")),
        new Makanan("Sweet Potato Casserole", List.of("sweet potatoes"), List.of("Dairy", "Nuts")),
        new Makanan("Baked Brie", List.of("Brie Cheese"), List.of("Dairy", "Nuts")),
        new Makanan("French Onion Soup", List.of("Onions"), List.of("Dairy", "Wheat")),
        new Makanan("Greek Gyro", List.of("Lamb/Chicken"), List.of("Dairy", "Wheat")),
        new Makanan("Mango Lassi", List.of("Mango", "Sugar", "Yogurt(milk, cultures)", "Cardamom"), List.of("Dairy")),
        new Makanan("Omelette", List.of("Eggs"), List.of("Eggs")),
        new Makanan("Egg Salad Sandwich", List.of("Eggs"), List.of("Eggs", "Mustard")),
        new Makanan("Egg Fried Rice", List.of("Eggs"), List.of("Eggs", "Soybeans")),
        new Makanan("Fish Sticks", List.of("Fish Fillets"), List.of("fish")),    
        new Makanan("Malabar Fish Curry", List.of("Fish"), List.of("Fish", "Dairy")),
        new Makanan("Baked Cod", List.of("Cod"), List.of("Fish", "Dairy")),
        new Makanan("Tuna Sandwich", List.of("Tuna"), List.of("Fish", "Eggs")),
        new Makanan("Sushi", List.of("Salmon", "Tuna"), List.of("Fish", "Soybeans")),
        new Makanan("Honey Soy Glazed Salmon", List.of("Salmon"), List.of("Fish", "Soybeans")),
        new Makanan("Milk Chocolate", List.of("Cocoa butter"), List.of("milk")),
        new Makanan("Strawberry Yoghurt", List.of("milk", "cultures"), List.of("Milk", "Strawberries")),
        new Makanan("Chicken Satay", List.of("Chicken"), List.of("Peanuts")),
        new Makanan("Pesto Chicken", List.of("Chicken"), List.of("Pine nuts", "Diary")),
        new Makanan("Rice Pudding", List.of("Rice"), List.of("Rice", "Diary")),
        new Makanan("Shrimp Scampi", List.of("Shrimp"), List.of("Shellfish")),
        new Makanan("Lobster Bisque", List.of("Lobster"), List.of("Shellfish", "Dairy")),
        new Makanan("Caesar Shrimp Skewers", List.of("Shrimp"), List.of("Shellfish", "Dairy")),
        new Makanan("Lobster Roll", List.of("Lobster"), List.of("Shellfish", "Eggs")),
        new Makanan("Bacon-Wrapped Shrimp", List.of("Shrimp"), List.of("Shellfish", "pork")),
        new Makanan("Shrimp Friend Rice", List.of("Shrimp"), List.of("Shellfish", "Soybeans")),
        new Makanan("Sushi Rolls", List.of("Shusi rice"), List.of("Soybeans", "Fish")),
        new Makanan("Chicken Noodle Casserole", List.of("Chicken"), List.of("wheat")),
        new Makanan("Oatmeal Raisiin Cookies", List.of("Oats"), List.of("Wheat", "Diary")),
        new Makanan("Lemon Pepper Chicken", List.of("Olive Oil", "Lemon pepper season"), List.of("None")),
        new Makanan("Tiramisu", List.of("Ladyfingers"), List.of("Wheat", "Alcohol", "Dairy")),
        new Makanan("Chocolate Cake", List.of("Flour", "Eggs", "Butter"), List.of("Wheat", "Dairy", "Cocoa")),
        new Makanan("Chocolate Chip Pancakes", List.of("Flour", "Sugar"), List.of("Wheat", "Dairy", "Cocoa")),
        new Makanan("Lemon Bars", List.of("Lemon Juice"), List.of("Wheat", "Dairy", "Eggs")),
        new Makanan("Pecan Pie", List.of("Pecans"), List.of("Wheat", "Dairy", "Nuts")),
        new Makanan("Zucchini Bread", List.of("Zucchini"), List.of("Wheat", "Dairy", "Nuts")),
        new Makanan("Banana Bread", List.of("Bananas"), List.of("Wheat", "Dairy", "Nuts")),
        new Makanan("Hawaiian Pizza", List.of("Pizza Dough"), List.of("Wheat", "Pork", "Dairy")),
        new Makanan("Baked Ziti", List.of("Pasta"), List.of("Wheat", "Dairy")),
        new Makanan("Vegetable Lasagna", List.of("Vegetables"), List.of("Wheat", "Dairy")),
        new Makanan("Chicken Parmesan", List.of("Chicken"), List.of("Wheat", "Dairy")),
        new Makanan("Pumpkin Pie", List.of("Pumpkin"), List.of("Wheat", "Dairy")),
        new Makanan("Beef Wellington", List.of("Beef"), List.of("Wheat", "Dairy")),  
        new Makanan("Hyderabadi Biryani", List.of("Basmati Rice"), List.of("None")),
        new Makanan("Greek Lemon Potatoes", List.of("Potatoes"), List.of("None")),
        new Makanan("Mutton Biryani,", List.of("Mutton"), List.of("None")),
        new Makanan("Teriyaki Beef,", List.of("Beef"), List.of("Soybeans")),
        new Makanan("Vanilla Cupcakes,", List.of("Flour"), List.of("Wheat", "Dairy")),
        new Makanan("Blueberry Pancakes,", List.of("Flour"), List.of("Wheat", "Dairy")), 
        new Makanan("Cinnamon Rolls,", List.of("Flour"), List.of("Wheat", "Dairy")),
        new Makanan("Pineapple Upside-Down Cake,", List.of("Pineapple"), List.of("Wheat", "Dairy")),
        new Makanan("Apple Pie,", List.of("Apple"), List.of("Wheat", "Dairy")),
        new Makanan("Apple Crisp,,", List.of("Apple"), List.of("Wheat", "Dairy")),
        new Makanan("Jaebi,", List.of("Flour"), List.of("Wheat", "Dairy")),
        new Makanan("Dosa,", List.of("Rice"), List.of("None", "Dairy")),
        new Makanan("Mango Salsa,", List.of("Mango"), List.of("None")),
        new Makanan("Chicken Caesar Salad,,", List.of("Chicken breast"), List.of("Dairy,",  "Anchovies")),
        new Makanan("Greek Gyro,", List.of("Lamb/Chicken"), List.of("Wheat", "Dairy")),
        new Makanan("Quiche,,", List.of("Eggs"), List.of("Eggs,", "Dairy")),
        new Makanan("Pesto Pasta,", List.of("Pasta"), List.of("None")),
        new Makanan("Quinoa Stuffed Peppers,", List.of("Quinoa"), List.of("Wheat", "Dairy")),
        new Makanan("Avocado Toast,", List.of("Avocado"), List.of("None")),
        new Makanan("Carrot Cake,", List.of("Carrots"), List.of("Wheat", "Dairy")),
        new Makanan("Pasta Carbonara,", List.of("Pasta"), List.of("Wheat", "Dairy")),
        new Makanan("Ratatouille Tart,", List.of("Vegetables"), List.of("None")),
        new Makanan("Cucumber Salad,", List.of("Cucumbers"), List.of("None")),
        new Makanan("Caesar Salad Wrap,", List.of("Romaine lettuce"), List.of("Wheat", "Dairy")),
        new Makanan("Chicken Alfredo Pizza,", List.of("Pizza dough"), List.of("Wheat", "Dairy")),
        new Makanan("Butter Naan,", List.of("Flour"), List.of("Wheat", "Dairy")),
        new Makanan("Pancake Stack,", List.of("Flour"), List.of("Wheat", "Dairy")),
        new Makanan("Stuffed Mushrooms,", List.of("Mushrooms"), List.of("None")),
        new Makanan("Stuffed Cabbage Rolls,", List.of("Cabbage  "), List.of("None")),
        new Makanan("Teriyaki Salmon,", List.of("Salmon fillet"), List.of("Fish", "Soybeans")),
        new Makanan("Tomato Soup,", List.of("Tomatoes"), List.of("None")),
        new Makanan("Pani Puri,", List.of("Semolina"), List.of("None")),
        new Makanan("Aloo Gobi,", List.of("Potatoes"), List.of("None")),
        new Makanan("Paniyaram,,", List.of("Fermented batter"), List.of("None")),
        new Makanan("Chole Kulche,", List.of("Chickpeas"), List.of("None")),
        new Makanan("Dahi Vada,", List.of("Lentil dumplings"), List.of("Dairy")),
        new Makanan("Rabri,", List.of("Milk"), List.of("Dairy")),
        new Makanan("Mushroom Risotto,", List.of("Mushrooms"), List.of("Dairy")),
        new Makanan("Chicken Alfredo Pizza,", List.of("Pizza dough"), List.of("Wheat", "Dairy")),
        new Makanan("Chicken Enchiladas,", List.of("Chicken"), List.of("Wheat", "Dairy")),
        new Makanan("Caesar Pasta Salad,", List.of("Pasta"), List.of("Wheat", "Dairy")),
        new Makanan("Matar Paneer,", List.of("Paneer"), List.of("Wheat", "Dairy")),
        new Makanan("Rasmalai,", List.of("Paneer"), List.of("Dairy")),
        new Makanan("Butter Chicken,", List.of("Chicken"), List.of("Dairy")),
        new Makanan("Dal Makhani,,", List.of("Lentils"), List.of("Dairy")),
        new Makanan("Berry Crumble,", List.of("Mixed berries"), List.of("Wheat", "Dairy")),
        new Makanan("Strawberry Smoothie", List.of("Strawberries","Honey","Yogurt (milk, cultures)"), List.of("Dairy")),
        new Makanan("Cheese Pizza", List.of("Tomato Sauce"), List.of("Dairy")),
        new Makanan("Margherita Pizza", List.of("Cheese", "Tomato Sauce, Basil"), List.of("Dairy")),
        new Makanan("Mashed Potatoes", List.of("Potatoes","Butter","Salt", "Pepper"), List.of("Dairy")),
        new Makanan("Greek Yogurt", List.of("Yogurt(milk, cultures)"), List.of("Dairy")),
        new Makanan("Caesar Salad Wrap", List.of("Grilled Chicken", "Caesar dressing", "Lettuce", "Parmesan Cheese"), List.of("Dairy")),
        new Makanan("Caprese Salad", List.of("Tomatoes", "Olive oil", "Mozzarella cheese", "Basil"), List.of("Dairy")),
        new Makanan("Watermelon Salad", List.of("Watermelon", "Feta Cheese", "Mint"), List.of("Dairy")),
        new Makanan("Berry Smoothie", List.of("Mixed Berries", "Sugar", "Yogurt(milk, cultures)"), List.of("Dairy")),
        new Makanan("Berry Parfait", List.of("Mixed Berries", "Sugar", "Yogurt(milk, cultures)"), List.of("Dairy")),
        new Makanan("Chicken Alfredo", List.of("Chicken", "Butter", "Cream", "Parmesan Cheese"), List.of("Dairy")),
        new Makanan("Baked Apple", List.of("Apples", "Sugar", "Butter", "Cinnamon", "Oats"), List.of("Wheat", "Dairy")),
        new Makanan("Chicken Teriyaki", List.of("Chicken", "Soy sauce", "Honey", "Ginger"), List.of("Soybeans")),
        new Makanan("Baked Salmon", List.of("Salmon","Olive oil","Lemon", "Herbs"), List.of("Fish")),
        new Makanan("Sushi Bowl", List.of("Sushi rice","Soy Sauce","Raw fish", "Vegetables"), List.of("Fish", "Soybeans")),
        new Makanan("Vanilla Ice Cream", List.of("Sugar","Cocoa butter","Milk powder","Vanilla extract"), List.of("Milk")),
        new Makanan("Chicken Biryani", List.of("Chicken","Ghee","Basmati rice", "Spices"), List.of("None")),
        new Makanan("Chicken Pot Pie", List.of("Chicken","Butter","Vegetables", "Pastry"), List.of("Wheat")),
        new Makanan("Lentil Salad", List.of("Lentils","Olive oil","Lemon juice", "Vegetables"), List.of("None")),
        new Makanan("Lemon Garlic Shrimp", List.of("Shrimp",",Olive oil","Lemon juice", "garlic"), List.of("Shellfish")),
        new Makanan("Eggplant Parmesan", List.of("Eggplant","Olive Oil","Mariana Sauce", "Cheese"), List.of("Dairy")),
        new Makanan("Miso Soup", List.of("Miso paste","Tofu","Seaweed"), List.of("Soybeans")),
        new Makanan("Prawn Briyani", List.of("Prawns","Ghee","Basmati rice", "Spices"), List.of("Shellfish")),
        new Makanan("Veggie Burger", List.of("Vegetable oil","Seasonings"), List.of("None")),
        new Makanan("Avocado Toast", List.of("Avocado","Olive oil","Salt", "Pepper"), List.of("None")),
        new Makanan("Veggie omelette", List.of("Mixed vegetables","Olive oil","Salt", "Pepper"), List.of("None")),
        new Makanan("Stuffed Tomatoes", List.of("Tomatoes","Herbs","Breadcrumbs"), List.of("None")),
        new Makanan("Raspberry Spinach Salad", List.of("Spinach","Raspberry","Vinaigrette"), List.of("None")),
        new Makanan("Stuffed Bell Peppers", List.of("Bell peppers","Olive oil","Ground meat", "Rice"), List.of("None")),
        new Makanan("Orange Chicken", List.of("Vegetable oil", "Orange sauce"), List.of("None")),
        new Makanan("Chocolate Chip Cookies", List.of("Flour","Sugar","Butter", "Chocolate chips"), List.of("Wheat", "Dairy")),
        new Makanan("Egg Salad", List.of("Eggs","Mayonnaise","Celery", "Onion"), List.of("Eggs")),
        new Makanan("Lemon Dill Salmon", List.of("Salmon","Olive oil","Lemon", "Dill"), List.of("Fish")),
        new Makanan("Vanilla Yogurt", List.of("Yogurt(milk, cultures)","Sugar","Vanilla extract"), List.of("Milk")),
        new Makanan("Pancetta Pasta", List.of("Pasta","Pancetta","Garlic", "Herbs"), List.of("None")),
        new Makanan("Mango Salsa", List.of("Mango","Red onion","Lime"), List.of("None")),
        new Makanan("Prawn Curry", List.of("Prawns","Coconut milk","Curry spices"), List.of("Shellfish", "Dairy")),
        new Makanan("Aloo Paratha", List.of("Potatoes","Ghee","Wheat flour", "Spices"), List.of("Wheat")),
        new Makanan("Strawberry Shortcake", List.of("Strawberry","Sugar","Whipped cream", "Shortcake"), List.of("Wheat", "Dairy")),
        new Makanan("Greek Mousakka", List.of("Eggplant","Olive oil","Ground meat", "Potatoes"), List.of("None")),
        new Makanan("Mango Coconut Popsicles", List.of("Sugar","Coconut milk"), List.of("None")),
        new Makanan("Strawberry Spinach Salad", List.of("Spinach","Strawberries","Vinaigrette"), List.of("None")),
        new Makanan("Tofu Scramble", List.of("Tofu","Vegetable oil","Turmeric", "Spices"), List.of("Soybeans")),
        new Makanan("Pinapple Upside-Down Cake", List.of("Pineapple","Sugar","Butter", "Flour"), List.of("Wheat", "Dairy")),
        new Makanan("Masala Dosa", List.of("Rice",",Oil","Potato masala", "Spices"), List.of("None")),
        new Makanan("Pesto Pasta Salad", List.of("Pasta","Pesto sauce","Tomatoes", "Vegetables"), List.of("None")),
        new Makanan("Chicken Fajitas", List.of("Vegetable oil","Fajita seasoning"), List.of("None")),
        new Makanan("Chicken Korma", List.of("Chicken","Ghee","Cream sauce", "Spices"), List.of("Dairy")),
        new Makanan("Zuchini Noodles with Pesto", List.of("Zucchini","Olive oil","Pesto sauce"), List.of("Pine nuts", "Dairy")),
        new Makanan("Lemon Herb Roasted Chicken", List.of("Chicken","Olive oil","Lemon", "Herbs"), List.of("None")),
        new Makanan("Honey Mustard Chicken", List.of("Chicken","Honey","Olive oil", "Mustard", "Herbs"), List.of("None")),
        new Makanan("Spinach Stuffed Chicken", List.of("Chicken","Olive oil","Spinach", "Garlic"), List.of("None")),
        new Makanan("Baked Garlic Parmesan Chicken", List.of("Chicken","Butter","Parmesan cheese", "Garlic"), List.of("Dairy")),
        new Makanan("Mushroom and Goat Cheese Flatbread", List.of("Mushrooms","Olive oil","Goat cheese", "Herbs"), List.of("Dairy")),
        new Makanan("Chicken Fettucine Alfredo", List.of("Chicken","Butter","Cream", "Parmesan cheese"), List.of("Dairy")),
        new Makanan("Cilantro Lime Chicken", List.of("Chicken","Olive oil","Cilantro", "Lime"), List.of("None")),
        new Makanan("Tomato Bruschetta", List.of("Tomatoes","Olive oil","Garlic", "Basil"), List.of("None")),
        new Makanan("Cucumber Salad", List.of("Cucumber","Olive oil","Lemon juice", "Dill"), List.of("None")),
        new Makanan("Tuna Salad", List.of("Tuna","Mayonnaise","Celery", "Onion"), List.of("Fish", "Eggs")),
        new Makanan("Stuffed Portobello Mushrooms", List.of("Portobello Mushrooms","Cheese","Breadcrumbs"), List.of("Dairy")),
        new Makanan("Beef Stroganoff", List.of("Beef","Source cream","Mushrooms", "Onions"), List.of("Dairy")),
        new Makanan("Rogan Josh", List.of("Lamb","Ghee","Yogurt", "Kashmiri red chili"), List.of("Dairy")),
        new Makanan("Butternut Squash Soup", List.of("Butternut squash","Buter","Nutmeg", "Cream"), List.of("Dairy")),
        new Makanan("Beef Burger", List.of("Ground beef","Vegetable oil","Salt", "Pepper"), List.of("None")),
        new Makanan("Gazpacho", List.of("Tomatoes","Olive oil","Garlic", "Herbs"), List.of("None")),
        new Makanan("Quionoa Salad", List.of("Quionoa","Olive oil","Lemon juice", "Herbs"), List.of("None")),
        new Makanan("Sweet Potato Fries", List.of("Sweet potatoes","Vegetable oil","Salt", "Paprika"), List.of("None")),
        new Makanan("Lemon Garlic Chicken", List.of("Chicken","Olive oil","Lemon juice", "Garlic"), List.of("None")),
        new Makanan("Greek Lemon Chicken", List.of("Chicken","Olive oil","Lemon juice", "Herbs"), List.of("None")),
        new Makanan("Chole Bhature", List.of("Chickpeas","Sugar","Ghee", "Chole masala", "Spices"), List.of("None")),
        new Makanan("Sweet and Sour Chicken", List.of("Chicken","Sugar","Vegetable oil", "Pinapple", "Bell peppers"), List.of("None")),
        new Makanan("Sausage and Pepper Pizza", List.of("Pizza dough","Sausage","Bell peppers"), List.of("Wheat", "Dairy")),
        new Makanan("Cinammon Rolls", List.of("Dough","Sugar","Butter", "Cinnamon", "Icing"), List.of("Wheat", "Dairy")),
        new Makanan("Raspberry Cheesecake", List.of("Cream cheese","Sugar","Butter", "Graham cracker crust", "Raspberries"), List.of("Wheat", "Dairy")),
        new Makanan("Key Lime Pie", List.of("Lime Juice","Sugar","Butter", "Graham cracker crust"), List.of("Wheat", "Dairy")),
        new Makanan("Mixed Berry Pie", List.of("Mixed berries","Sugar","Butter", "Flour"), List.of("Wheat", "Dairy")),
        new Makanan("Lemon Poppy Seed Muffins", List.of("Flour","Sugar","Butter", "Lemon zest", "Poppy seeds"), List.of("Wheat", "Dairy")),
        new Makanan("Chicken Enchiladas", List.of("Chicken","Enchilada sauce","Cheeese", "Tortillas"), List.of("Wheat", "Dairy")),
        new Makanan("Grilled Portobello Mushroom Burger", List.of("Portobello Mushrooms","Burger bun","Vegetables"), List.of("Wheat")),
        new Makanan("Chicken and Rice Soup", List.of("Chicken","Vegetable oil","Rice", "Vegetables"), List.of("None")),
        new Makanan("Cabbage Rolls", List.of("Cabbage","Olive oil","Ground meat", "Rice"), List.of("None")),
        new Makanan("Spinach Artichoke Dip", List.of("Spinach","Cream Cheese","Sour cream", "Parmesan cheese", "Garlic"), List.of("Dairy")),
        new Makanan("Spinach and Feta Stuffed Chicken", List.of("Chicken","Olive oil","Spinach", "Feta cheese"), List.of("Dairy")),
        new Makanan("Banana Smoothie", List.of("Bananas","Honey","Yogurt(milk, cultures)"), List.of("Dairy")),
        new Makanan("Buffalo Wings", List.of("Vegetable Oil","Buffalo Sauce"), List.of("None")),
        new Makanan("Banana Pudding", List.of("Bananas","Sugar","Milk", "Vanilla pudding mix", "Cookies"), List.of("Dairy")),
        new Makanan("Minestrone Soup", List.of("Olive oil","Tomato broth"), List.of("None")),
        new Makanan("Apple Cider", List.of("Sugar","Cinnamon"), List.of("None")),
        new Makanan("Green Smoothie", List.of("Honey","Avocado","Almond milk"), List.of("None")),
        new Makanan("Beef Chili", List.of("Chili powder"), List.of("None")),
        new Makanan("S'mores", List.of("Graham crackers","Marshmallow","Chocolate"), List.of("Wheat", "Dairy")),
        new Makanan("Chicken Quesadilla", List.of("Chicken","Butter","Cheese"), List.of("Wheat", "Dairy")),
        new Makanan("Soy Milk", List.of("Sugar","Emulsifiers"), List.of("Soybeans")),
        new Makanan("Honey Glazed Carrots", List.of("Honey","Butter"), List.of("None")),
        new Makanan("Roasted Brussels Sprouts", List.of("Olive oil","Balsamic glaze"), List.of("None")),
        new Makanan("Baked Chicken Wings", List.of("Olive oil","Seasonings"), List.of("None")),
        new Makanan("Black Bean Soup", List.of("Olive oil","Vegetables"), List.of("None")),
        new Makanan("Beef Tacos", List.of("Vegetable oil","Taco seasoning"), List.of("None")),
        new Makanan("Greek Gyro Wrap", List.of("Lamb","Tzaziki sauce","Tomatoes", "Onions"), List.of("Dairy")),
        new Makanan("Peanut Butter", List.of("Sugar","Vegetable oil","Salt"), List.of("Peanuts"))       
    );

    for (Makanan makanan : makananList) {
        daftarMakanan.add(makanan);
    }
    }

    private void clearTable() {
        table.setRowCount(0);
    }
    
    private void showAllData() {
    clearTable();
    int no = 1;
    for (Makanan makanan : daftarMakanan) {
        table.addRow(new Object[]{
            no++, 
            makanan.getNamaMakanan(), 
            String.join(", ", makanan.getBahan()), 
            String.join(", ", makanan.getAlergen())
        });
        searchTimeLabel.setText("Search Time: Not Started");
    }
}

    private void searchIterative(String keyword) {
        int no = 0; 
        boolean found = false;
        table.setRowCount(0);

        while (no < daftarMakanan.size() && !found) {
            if (daftarMakanan.get(no).getNamaMakanan().equals(keyword)) {
                table.addRow(new Object[]{
                    no + 1,
                    daftarMakanan.get(no).getNamaMakanan(),
                    String.join(", ", daftarMakanan.get(no).getBahan()),
                    String.join(", ", daftarMakanan.get(no).getAlergen())
                });
                found = true;
            }
            no++;
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Tidak ada makanan yg mengandung alergen " + keyword);
        }
    }


    private void searchRecursive(String keyword, int index) {
        if (index >= daftarMakanan.size()) return;

        Makanan makanan = daftarMakanan.get(index);
        if (makanan.getNamaMakanan().contains(keyword)) {
            table.addRow(new Object[]{
                    table.getRowCount() + 1,
                    makanan.getNamaMakanan(),
                    String.join(", ", makanan.getBahan()),
                    String.join(", ", makanan.getAlergen())
            });
        } else {
            searchRecursive(keyword, index + 1);
        }
    }

    private void updateSearchTime(long startTime, long endTime) {
        long elapsedTime = endTime - startTime;
        double milliseconds = elapsedTime / 1_000_000.0;
        searchTimeLabel.setText(String.format("Search Time: %.3f ms", milliseconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TubesAKA());
    }
}