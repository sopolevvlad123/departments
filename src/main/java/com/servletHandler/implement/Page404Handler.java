package com.servletHandler.implement;

import com.exception.AppException;
import com.servletHandler.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.PAGE_404;

public class Page404Handler implements ServletHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        request.getRequestDispatcher(PAGE_404).forward(request, response);
    }
}
