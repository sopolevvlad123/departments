package com.servletHandler.implement;

import com.servletHandler.implement.department.DepartmentListHandler;
import org.springframework.stereotype.Component;

import static com.utils.ServletHandlerConstants.DEFAULT_URL;

@Component(DEFAULT_URL)
public class Indexhandler extends DepartmentListHandler {
}
