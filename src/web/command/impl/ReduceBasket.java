package web.command.impl;

import web.Basket.Basket;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ReduceBasket implements Controller {


    @Override
    public synchronized void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        if (basket == null) {
            basket = new Basket(new HashMap<>());
            req.getSession().setAttribute("basket", basket);
        }
        int roomId = Integer.parseInt(req.getParameter("roomId"));
        AtomicInteger count = basket.getBasket().get(roomId);
        int currentCount = 0;
        if (count == null) {
            count = new AtomicInteger();
            count.set(1);
            currentCount++;
        } else {
            currentCount = count.decrementAndGet();
        }
        basket.getBasket().put(roomId, count);
        PrintWriter write = resp.getWriter();
        write.print(currentCount);
    }
}

