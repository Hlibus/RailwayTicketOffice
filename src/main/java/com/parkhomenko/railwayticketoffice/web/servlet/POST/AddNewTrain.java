package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.service.TrainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addTrain")
public class AddNewTrain extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddNewTrain.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String trainNumber = req.getParameter("trainNumber");
        String countWagonsAndSeatsStr = req.getParameter("countWagonsAndSeats");
        int[][]countWagonsAndSeats = parseWagonsAndSeatsCount(countWagonsAndSeatsStr);
        try {
            TrainService.addNewTrainWithWagonsAndSeats(trainNumber, countWagonsAndSeats);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error while update data from DB");
            e.printStackTrace();
        }
        resp.sendRedirect("getTrainSettings");

        logger.debug("Command finished");
    }

    private int[][] parseWagonsAndSeatsCount(String countWagonsAndSeats){
        int[][]res;
        String[] lines = countWagonsAndSeats.split("\n");
        res = new int[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            if(line.length == 2){
                res[i][0] = Integer.parseInt(line[0].replaceAll("\\s+",""));
                res[i][1] = Integer.parseInt(line[1].replaceAll("\\s+",""));
            }
        }
        return res;
    }
}
