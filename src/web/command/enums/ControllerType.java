package web.command.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.command.Controller;
import web.command.impl.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ControllerType {
    LOGIN("login.jsp", "Login", "login.title", new LoginController()),
    LOGOUT("login.jsp", "Logout", "logout", new LogoutController()),
    ORDERS("orders/orders.jsp", "Orders", "orders.title", new OrdersController()),
    ADD_ROOM("", "addRoom", "", new AddToBasket()),
    REDUCE_ROOM("", "reduceRoom", "", new ReduceBasket()),
    ROOMS("rooms/rooms.jsp", "header.rooms", "rooms.title", new RoomController()),
    CREATE_ORDER("", "createOrder", "orders.title", new CreateOrderController()),
    REGISTRATION("registration/registration.jsp", "Registration", "registration.title", new RegistrationController()),
    REGPAGE("registration/registration.jsp", "RegistrationPage", "registration.title", new RegPageController()),
    HOME("home/home.jsp", "Home", "home.title", new HomePageController());


    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;


    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return ROOMS;
    }
}

