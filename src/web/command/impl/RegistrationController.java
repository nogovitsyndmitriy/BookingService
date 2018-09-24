package web.command.impl;

import dao.vo.AccountVO;
import services.AccountService;
import services.impl.AccountServiceImpl;
import web.command.Controller;
import web.mapper.AccountMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationController implements Controller {
    private AccountService accountService = AccountServiceImpl.getINSTANCE();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AccountVO account = AccountMapper.map(req);
        accountService.save(account);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/frontController?command=rooms");
        return;

    }
}
