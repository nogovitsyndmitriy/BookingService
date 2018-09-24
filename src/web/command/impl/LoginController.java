package web.command.impl;

import entity.Account;
import services.AccountService;
import services.impl.AccountServiceImpl;
import web.auth.Encoder;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController implements Controller {
    private AccountService accountService = AccountServiceImpl.getINSTANCE();

    public LoginController() {
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null || login.equals("") || password.equals("")) {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }

        Account account = accountService.getByLogin(login);
        if (account != null && account.getPassword().equals(Encoder.encode(password))) {
            req.getSession().setAttribute("Account", account);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=rooms");
            return;
        } else {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            req.setAttribute("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }
    }
}
