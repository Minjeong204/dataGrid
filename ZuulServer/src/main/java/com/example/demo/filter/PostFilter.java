package com.example.demo.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostFilter extends ZuulFilter {
	Logger log = LoggerFactory.getLogger(PostFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		log.info("===== START Post Filter. =====");

		int httpStatus = ctx.getResponse().getStatus();
		String respHttpBody = getRespHttpBody(ctx);
		log.info("[Post Filter] HttpStatus : {}, HttpBody : {}", httpStatus, respHttpBody);

		return null;
	}

	@Override
	public String filterType() {
		return "Post";
	}

	@Override
	public int filterOrder() {
		return 2;
	}
	private String getRespHttpBody(RequestContext ctx) {
        String responseBody = ctx.getResponseBody();
        if (responseBody == null) {
            InputStream is  = ctx.getResponseDataStream();
            try {
                byte[] ib = StreamUtils.copyToByteArray(is);
                responseBody = new String(ib, StandardCharsets.UTF_8);
                ctx.setResponseDataStream(new ByteArrayInputStream(ib));
            } catch (IOException e) {
                log.error("It is failed to obtainning Response Http Body.", e);
            }
        }
        return responseBody;
    }
}
