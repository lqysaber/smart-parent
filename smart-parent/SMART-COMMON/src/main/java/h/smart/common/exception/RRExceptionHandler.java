package h.smart.common.exception;

import java.io.IOException;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import h.smart.common.utils.R;

@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException ex) throws IOException {
        String msg = ex.getMessage();
        String[] msgs = msg.split(": ");
        return R.error(500, msgs[msgs.length-1]);
    }

//	@ExceptionHandler(DuplicateKeyException.class)
//	public R handleDuplicateKeyException(DuplicateKeyException e){
//		logger.error(e.getMessage(), e);
//		return R.error("数据库中已存在该记录");
//	}

//	@ExceptionHandler(AuthorizationException.class)
//	public R handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return R.error("没有权限，请联系管理员授权");
//	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
	
//	@ExceptionHandler(AuthorizationException.class)
//	public Object handleAuthorizationException(HttpServletRequest request) {
//		if (HttpUtils.isAjaxRequest(request)) {
//			return R.error("暂无权限，请联系管理员！");
//		} else {
//			ModelAndView mav = new ModelAndView();
//			mav.setViewName("error/403");
//			return mav;
//		}
//	}
//
//	@ExceptionHandler(ExpiredSessionException.class)
//	public String handleExpiredSessionException() {
//		return "login";
//	}
}
