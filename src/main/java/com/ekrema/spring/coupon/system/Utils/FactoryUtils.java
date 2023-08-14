package com.ekrema.spring.coupon.system.Utils;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FactoryUtils {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> initCompanies() {
        Company c1 = Company.builder().name("KSP").email("KSP@KSP.com").password("1234").build();
        Company c2 = Company.builder().name("BBB").email("info@BBB.com").password("1234").build();

        Company c3 = Company.builder().name("Global Industries").email("info@globalindustries.com").password("1234").build();

        Company c4 = Company.builder().name("Tours").email("info@tours.com").password("1234").build();

        Company c5 = Company.builder().name("Web Solutions Inc.").email("info@websolutionsinc.com").password("1234").build();

        Company c6 = Company.builder().name("Best Buy Co. Inc.").email("info@bestbuy.com").password("1234").build();

        Company c7 = Company.builder().name("Newegg Inc.").email("info@newegg.com").password("1234").build();

        Company c8 = Company.builder().name("Nike Inc.").email("info@nike.com").password("1234").build();

        Company c9 = Company.builder().name("The Coca-Cola Company").email("info@coca-cola.com").password("1234").build();

        Company c10 = Company.builder().name("Walmart Inc.").email("info@walmart.com").password("1234").build();

        List<Company> companies = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);

        return companies;
    }

    public List<Coupon> initCoupons(List<Company> companies) {
        List<Coupon> coupons = new ArrayList<>();

        String[] foodTitles = {"50% Off Combo Meal", "Free Dessert with Entree", "Family Dinner Deal", "Weekend Brunch Special", "Pizza Night Discount"};
        String[] foodDescriptions = {"Enjoy 50% off any combo meal on our menu.", "Get a free dessert of your choice when you order an entree.", "Special discount for family dinners this week.", "Join us for a delightful weekend brunch with exclusive savings.", "Grab a pizza night deal with discounted prices."};

        String[] electricityTitles = {"Smart Home Upgrade", "Energy-Efficient Appliances", "Free Energy Audit", "Solar Panel Installation", "Smart Thermostat Deal"};
        String[] electricityDescriptions = {"Upgrade your home to a smart home with energy-efficient devices.", "Save on your electricity bills with new energy-efficient appliances.", "Schedule a free energy audit to optimize your energy usage.", "Switch to solar energy with a discounted panel installation.", "Get a smart thermostat and save on heating and cooling costs."};

        String[] restaurantTitles = {"Fine Dining Experience", "Cuisine Tasting Menu", "Wine and Dine Night", "Date Night Special", "Chef's Table Reservation"};
        String[] restaurantDescriptions = {"Indulge in a luxurious fine dining experience with exquisite dishes.", "Explore our cuisine tasting menu and savor a variety of flavors.", "Join us for a wine and dine night with exclusive pairings.", "Make your date night memorable with our special menu.", "Reserve a seat at the chef's table for an exclusive culinary adventure."};

        String[] vacationTitles = {"Tropical Getaway", "Adventure Expedition", "Cultural Immersion Tour", "Luxury Resort Retreat", "Weekend Escape Package"};
        String[] vacationDescriptions = {"Escape to a tropical paradise and relax on white sandy beaches.", "Embark on an exciting adventure expedition to breathtaking destinations.", "Immerse yourself in local culture with our guided tour experience.", "Indulge in a luxury resort retreat with top-notch amenities.", "Experience a perfect weekend escape with our curated package."};

        Random random = new Random();

        for (Company company : companies) {
            for (int i = 0; i < 5; i++) {
                Category category = Category.values()[random.nextInt(Category.values().length)];
                String title, description;

                switch (category) {
                    case FOOD:
                        title = foodTitles[random.nextInt(foodTitles.length)];
                        description = foodDescriptions[random.nextInt(foodDescriptions.length)];
                        break;
                    case ELECTRICITY:
                        title = electricityTitles[random.nextInt(electricityTitles.length)];
                        description = electricityDescriptions[random.nextInt(electricityDescriptions.length)];
                        break;
                    case RESTAURANT:
                        title = restaurantTitles[random.nextInt(restaurantTitles.length)];
                        description = restaurantDescriptions[random.nextInt(restaurantDescriptions.length)];
                        break;
                    case VACATION:
                        title = vacationTitles[random.nextInt(vacationTitles.length)];
                        description = vacationDescriptions[random.nextInt(vacationDescriptions.length)];
                        break;
                    default:
                        title = "Default Title";
                        description = "Default Description";
                }

                Date startDate = Date.valueOf(LocalDate.now().plusDays(i * 7));
                Date endDate = Date.valueOf(LocalDate.now().plusDays(i * 7 + 14));
                int amount = random.nextInt(50) + 50;
                double price = random.nextInt(500) + 50.0;
                String image = generateCouponImageURL(category);

                Coupon coupon = Coupon.builder()
                        .company(company)
                        .category(category)
                        .title(title)
                        .description(description)
                        .startDate(startDate)
                        .endDate(endDate)
                        .amount(amount)
                        .price(price)
                        .image(image)
                        .build();

                coupons.add(coupon);
            }
        }

        return coupons;
    }


    private String generateCouponImageURL(Category category) {
        switch (category) {
            case FOOD:
                return "https://media.giphy.com/media/eSQKNSmg07dHq/giphy.gif"; // Replace with actual food image URL
            case ELECTRICITY:
                return "https://media.giphy.com/media/VbnUQpnihPSIgIXuZv/giphy.gif"; // Replace with actual electricity image URL
            case RESTAURANT:
                return "https://media.giphy.com/media/TGcD6N8uzJ9FXuDV3a/giphy.gif"; // Replace with actual restaurant image URL
            case VACATION:
                return "https://media.giphy.com/media/C2L2bXRnv2chSO1mAH/giphy.gif"; // Replace with actual vacation image URL
            default:
                return "";
        }
    }

    public List<Customer> initCustomers() {
        Customer c1 = Customer.builder().firstName("John").lastName("Doe").email("johndoe@email.com").password("1234").build();

        Customer c2 = Customer.builder().firstName("Jane").lastName("Smith").email("janesmith@email.com").password("1234").build();

        Customer c3 = Customer.builder().firstName("Bob").lastName("Jones").email("bobjones@email.com").password("1234").build();

        Customer c4 = Customer.builder().firstName("Samantha").lastName("Lee").email("samlee@email.com").password("1234").build();

        Customer c5 = Customer.builder().firstName("Michael").lastName("Johnson").email("michaelj@email.com").password("1234").build();

        Customer c6 = Customer.builder().firstName("Emily").lastName("Davis").email("emilyd@email.com").password("1234").build();

        Customer c7 = Customer.builder().firstName("Chris").lastName("Wilson").email("chrisw@email.com").password("1234").build();

        Customer c8 = Customer.builder().firstName("Amanda").lastName("Taylor").email("amandat@email.com").password("1234").build();

        Customer c9 = Customer.builder().firstName("David").lastName("Garcia").email("davidg@email.com").password("1234").build();

        Customer c10 = Customer.builder().firstName("Maria").lastName("Martinez").email("mariam@email.com").password("1234").build();

        List<Customer> customers = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
        return customers;
    }
}
