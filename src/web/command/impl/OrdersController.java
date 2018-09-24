package web.command.impl;

import entity.Account;
import entity.Order;
import services.OrderService;
import services.impl.OrderServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Account account = (Account) req.getSession().getAttribute("Account");
        List<Order> orders = orderService.getByAccountId(account.getID());

        req.setAttribute("orders", orders);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
