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

public class CreateOrderController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("Account");
        if (account != null) {
            int roomId = Integer.parseInt(req.getParameter("roomId"));
            Order orders = orderService.createOrder(account.getID(), roomId, 1);

            req.setAttribute("orders", orders);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } else {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=login");
            return;
        }
    }
}
