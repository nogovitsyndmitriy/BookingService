package web.command.impl;

import services.RoomService;
import services.impl.RoomServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomController implements Controller {
    private RoomService roomService = RoomServiceImpl.getINSTANCE();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("rooms", roomService.getAll());
        req.getLocale();
        req.setAttribute("rooms", roomService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}