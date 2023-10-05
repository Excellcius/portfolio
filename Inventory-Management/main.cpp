#include <iostream>
#include <vector>
#include <string>

class Item {
public:
    Item(const std::string& name, double price) : name(name), price(price) {}

    virtual void display() const {
        std::cout << "Item: " << name << ", Price: $" << price << std::endl;
    }

    const std::string& getName() const {
        return name;
    }

    double getPrice() const {
        return price;
    }

private:
    std::string name;
    double price;
};

class Inventory {
public:
    void addItem(const Item& item) {
        items.push_back(item);
    }

    void displayInventory() const {
        std::cout << "Inventory:\n";
        for (const auto& item : items) {
            item.display();
        }
    }

    const Item* searchItem(const std::string& itemName) const {
        for (const auto& item : items) {
            if (item.getName() == itemName) {
                return &item;
            }
        }
        return nullptr;
    }

private:
    std::vector<Item> items;
};

int main() {
    Inventory inventory;

    // Adding items to the inventory
    Item item1("Laptop", 899.99);
    Item item2("Mouse", 29.99);
    Item item3("Keyboard", 49.99);

    inventory.addItem(item1);
    inventory.addItem(item2);
    inventory.addItem(item3);

    // Displaying the current inventory
    inventory.displayInventory();

    // Searching for an item
    std::cout << "\nEnter the name of the item to search: ";
    std::string searchName;
    std::cin >> searchName;

    const Item* searchedItem = inventory.searchItem(searchName);

    if (searchedItem != nullptr) {
        std::cout << "Item found!\n";
        searchedItem->display();
    } else {
        std::cout << "Item not found.\n";
    }

    return 0;
}
