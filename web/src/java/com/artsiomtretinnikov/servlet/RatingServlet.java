package com.artsiomtretinnikov.servlet;

import by.artsiomtretinnikov.service.RatingService;
import com.artsiomtretinnikov.dto.RatingDto;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.entity.Style;
import com.artsiomtretinnikov.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RatingDto> ratings;

        if (req.getParameter("style") == null && req.getParameter("agecategory") == null && req.getParameter("league") == null) {
            ratings = RatingService.getInstance().findAll();
        } else {
            ratings = RatingService.getInstance().findByStyleAgeLeague(req.getParameter("style"), req.getParameter("agecategory"),
                    req.getParameter("league"), Integer.parseInt(req.getParameter("page")), Integer.parseInt(req.getParameter("limit")));
        }

        req.setAttribute("ratings", ratings);
        req.setAttribute("styles", Arrays.stream(Style.values()).map(Style::getName).collect(Collectors.toList()));
        req.setAttribute("agecategorys", Arrays.stream(AgeCategory.values()).map(AgeCategory::getName).collect(Collectors.toList()));
        req.setAttribute("leagues", Arrays.stream(League.values()).map(League::getName).collect(Collectors.toList()));
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("viewRating"))
                .forward(req, resp);
    }
}
