package com.kyle.route66.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.kyle.route66.db.dao.ImageDao;
import com.kyle.route66.db.model.ArticleImage;

@Component("ImageServlet")
public class ImageServlet implements HttpRequestHandler {
	private static final Log log = LogFactory.getLog(ImageServlet.class);

	@Autowired
	private ImageDao imageDao;

	public void handleRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletOutputStream out = res.getOutputStream();
		
		String[] split = req.getRequestURI().split("/");

		try {
			log.debug(split[2] + ":" + split[3]);
			ArticleImage image = this.imageDao.getImage(
					Integer.parseInt(split[2]), URLDecoder.decode(split[3],"UTF-8"));

			if (image != null) {
				res.setContentType("image/gif");

				out.write(image.getData());
				out.flush();
			}
		} catch (Exception e) {
			res.setContentType("text/html");
			out.println("<html><head><title>Events On Route66 Photo</title></head>");
			out.println("<body><h1>Unable to find image</h1></body></html>");
			out.flush();
			return;
		}
	}

	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

}
