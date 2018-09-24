package web.handler;

import web.command.enums.ControllerType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static web.command.enums.ControllerType.ROOMS;

public class RequestHandler {
    public static ControllerType getCommand(HttpServletRequest req) {
        String param = req.getParameter("command");


        ControllerType type = ControllerType.getByPageName(param);
        req.setAttribute("title", type.getI18nKey());
        HttpSession session = req.getSession();
        String pageName = (String) session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", ROOMS.getPageName());
            session.setAttribute("pagePath", ROOMS.getPagePath());
        }

        return type;
    }
}
