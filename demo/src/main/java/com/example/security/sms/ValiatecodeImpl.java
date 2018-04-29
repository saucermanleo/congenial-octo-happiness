package com.example.security.sms;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.pojo.ImageCode;
import com.example.security.ValidateCodeException;

@Component
public class ValiatecodeImpl implements Valiatecode {
	
	private SessionStrategy st = new HttpSessionSessionStrategy();

	@Override
	public void valiate(ServletWebRequest request) throws ServletRequestBindingException {
		ImageCode imagecode = null;
		imagecode = (ImageCode) st.getAttribute(request, ImageCode.SESSION_KEY);
		String code = ServletRequestUtils.getStringParameter(request.getRequest(), ImageCode.SPRING_SECURITY_FORM_PASSWORD_KEY);
		if (StringUtils.isBlank(code)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		if (imagecode == null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if (imagecode.isExpire()) {
			st.removeAttribute(request, ImageCode.SESSION_KEY);
			throw new ValidateCodeException("验证码过期了");
		}
		if (!StringUtils.equals(imagecode.getCode(), code)) {
			throw new ValidateCodeException("验证码不正确");
		}
		st.removeAttribute(request, ImageCode.SESSION_KEY);
	}

}
