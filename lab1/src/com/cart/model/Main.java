package com.cart.model;

import com.cart.flyweight.ProductDisplayStyle;
import com.cart.flyweight.ProductDisplayStyleFactory;
import com.cart.composite.CartBundle;
import com.cart.composite.CartItemComponent;
import com.cart.bridge.Notification;
import com.cart.bridge.UrgentNotification;
import com.cart.bridge.EmailMessageSender;
import com.cart.bridge.SmsMessageSender;
import com.cart.adapter.ExternalPaymentAdapter;
import com.cart.adapter.ExternalPaymentGateway;
import com.cart.pool.NotificationServicePool;
import com.cart.factory.PromotionFactory;
import com.cart.factory.VipPromotionFactory;
import com.cart.discount.DiscountPolicy;
import com.cart.notification.NotificationService;
import com.cart.catalog.ProductCatalog;
import com.cart.controller.CartController;
import com.cart.discount.SeasonalDiscount;
import com.cart.notification.EmailNotificationService;
import com.cart.payment.CardPaymentFactory;
import com.cart.payment.CashPaymentFactory;
import com.cart.payment.Payable;
import com.cart.payment.PaymentFactory;

public class Main {
    public static void main(String[] args) {
        Customer customer = new VipCustomer("C2", "Олена", "olena@mail.com", 5);
        ShoppingCart cart = new ShoppingCart(customer);

        // DIP: конкретну реалізацію (EmailNotificationService) підставляємо ззовні,
        // CartController знає лише про інтерфейс NotificationService
        CartController controller = new CartController(cart, new EmailNotificationService());

        Product laptop = new Product("P1", "Ноутбук", 25000, 5);
        controller.addProductToCart(laptop, 1);

        cart.setDiscountPolicy(new SeasonalDiscount(15));

        controller.checkout();
        System.out.println(customer.getContactInfo());

        // Singleton: перевірка, що ProductCatalog існує в єдиному екземплярі
        ProductCatalog catalog1 = ProductCatalog.getInstance();
        ProductCatalog catalog2 = ProductCatalog.getInstance();

        System.out.println("Це той самий об'єкт? " + (catalog1 == catalog2));

        catalog1.addProduct(laptop);
        System.out.println("Товарів у каталозі (через catalog2): " + catalog2.size());

        // Prototype: клонуємо вже існуючий товар, а не створюємо новий з нуля
        Product clonedLaptop = laptop.clone();
        System.out.println("Клонований товар: " + clonedLaptop);
        System.out.println("Це той самий об'єкт? " + (laptop == clonedLaptop));

        // Factory Method: клієнтський код не знає, який саме клас створюється
        PaymentFactory cashFactory = new CashPaymentFactory();
        Payable cashPayment = cashFactory.createPayment();
        cashPayment.pay(500);

        PaymentFactory cardFactory = new CardPaymentFactory();
        Payable cardPayment = cardFactory.createPayment();
        cardPayment.pay(1500);

        // Abstract Factory: створюємо узгоджену пару знижка+сповіщення одним викликом
        PromotionFactory vipFactory = new VipPromotionFactory();
        DiscountPolicy vipDiscount = vipFactory.createDiscountPolicy();
        NotificationService vipNotification = vipFactory.createNotificationService();

        vipNotification.sendConfirmation(customer.getEmail(), vipDiscount.applyDiscount(10000));

        // Builder: покрокове конструювання складного об'єкта Order
        Order builtOrder = new OrderBuilder(cart)
                .withDeliveryAddress("м. Київ, вул. Хрещатик, 1")
                .withComment("Дзвонити перед доставкою")
                .build();
        builtOrder.confirm();
        System.out.println(builtOrder);

        // Object Pool: перевикористання об'єктів NotificationService замість створення нових
        NotificationServicePool pool = new NotificationServicePool();

        NotificationService service1 = pool.acquire();
        service1.sendConfirmation(customer.getEmail(), 100);
        pool.release(service1);

        NotificationService service2 = pool.acquire();
        service2.sendConfirmation(customer.getEmail(), 200);

        // Adapter: сторонній клас ExternalPaymentGateway адаптовано під наш Payable
        Payable adaptedPayment = new ExternalPaymentAdapter(new ExternalPaymentGateway());
        adaptedPayment.pay(2000);

        // Bridge: "що" (Notification/UrgentNotification) і "як" (Email/SmsMessageSender) комбінуються вільно
        Notification emailNotification = new Notification(new EmailMessageSender());
        emailNotification.notify(customer.getEmail(), "Ваше замовлення прийнято в обробку");

        Notification urgentSms = new UrgentNotification(new SmsMessageSender());
        urgentSms.notify(customer.getEmail(), "Залишок товару закінчується");

        // Composite: набір товарів рахується так само, як і один товар, через спільний інтерфейс
        Product mouse = new Product("P2", "Мишка", 500, 20);
        Product bag = new Product("P3", "Сумка для ноутбука", 800, 10);

        CartBundle laptopBundle = new CartBundle("Комплект: Ноутбук + аксесуари");
        laptopBundle.add(new CartItemComponent(laptop));
        laptopBundle.add(new CartItemComponent(mouse));
        laptopBundle.add(new CartItemComponent(bag));

        System.out.println(laptopBundle.getName() + " — загальна ціна: " + laptopBundle.getPrice() + " грн");

        // Flyweight: кілька товарів однієї категорії перевикористовують один спільний об'єкт стилю
        ProductDisplayStyle style1 = ProductDisplayStyleFactory.getStyle("Електроніка");
        style1.display(laptop.getName());

        ProductDisplayStyle style2 = ProductDisplayStyleFactory.getStyle("Електроніка");
        style2.display("Смартфон");

        System.out.println("Це той самий об'єкт стилю? " + (style1 == style2));
    }
}