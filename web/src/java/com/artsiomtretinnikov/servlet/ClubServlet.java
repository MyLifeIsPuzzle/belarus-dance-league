package com.artsiomtretinnikov.servlet;

import by.artsiomtretinnikov.service.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewClub")
public class ClubServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("club", ClubService.getInstance().getClub(Long.valueOf(req.getParameter("clubId"))));
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/viewClub.jsp")
                .forward(req, resp);
    }
}
