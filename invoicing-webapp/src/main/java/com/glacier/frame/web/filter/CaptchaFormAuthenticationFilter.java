package com.glacier.frame.web.filter;

import com.glacier.basic.exception.IncorrectCaptchaException;
import com.glacier.basic.util.IpUtil;
import com.glacier.frame.compent.realm.CaptchaUsernamePasswordToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
	private final static Logger LOGGER = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        LOGGER.info(String.format("request : %s", request.getParameter("username")));
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String ip = IpUtil.getIpAddr((HttpServletRequest) request);
        String host = ip + IpUtil.getIpInfo(ip);
        LOGGER.info(String.format("username : %s.",username));
        LOGGER.info(String.format("password : %s.",password));

        char[] charPassword = null;

        if (StringUtils.isNotBlank(password)) {
            charPassword = password.toCharArray();
        }

        return new CaptchaUsernamePasswordToken(username, charPassword, rememberMe, host, captcha);
    }

    /**
     * 登录认证，失败会捕获相关异常信息
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) createToken(request, response);
        try {
            doCaptchaValidate( (HttpServletRequest)request,token);
            Subject subject = getSubject(request, response);
            subject.login(token);
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            session.setAttribute("currentUser", subject.getPrincipal());
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            LOGGER.warn(String.format("Exception : %s.",e.getMessage()),e);
            return onLoginFailure(token, e, request, response);
        }
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
        String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isBlank(captcha) || !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }
    
}
