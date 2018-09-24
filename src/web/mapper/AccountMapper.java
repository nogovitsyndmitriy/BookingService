package web.mapper;

import dao.vo.AccountVO;
import web.auth.Encoder;
import javax.servlet.http.HttpServletRequest;

public class AccountMapper {

    public static AccountVO map(HttpServletRequest req) {
        AccountVO account = new AccountVO();
        account.setLogin(req.getParameter("login"));
        account.setPassword(Encoder.encode(req.getParameter("pwd")));
        account.setName(req.getParameter("name"));
        account.setLastName(req.getParameter("lastName"));
        account.setPassportId(req.getParameter("passportId"));
        account.setAddress(req.getParameter("address"));
        account.setPhone(req.getParameter("phone"));
        account.setCardNumber(req.getParameter("creditCardNum"));
        account.setCardHolder(req.getParameter("cardholder"));
        account.setAge(req.getParameter("age"));
        return account;
    }
}
