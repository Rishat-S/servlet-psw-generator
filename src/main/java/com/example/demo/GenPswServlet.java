package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@WebServlet(name = "genPswServlet",urlPatterns = {"/"})
public class GenPswServlet extends HttpServlet {
    private static final int SIZE_OF_PASSWORD = 8;
    private static final int SIZE_OF_COUNT = 10;
    List<Character> charactersUpCase = new ArrayList<>(
            Arrays.asList(
                    'A', 'B', 'C', 'D', 'E', 'F', 'Z', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'V', 'X'
            ));
    List<Character> charactersLowCase = new ArrayList<>(
            Arrays.asList(
                    'a', 'b', 'c', 'd', 'e', 'f', 'z', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'v', 'x'
            ));
    List<Character> charactersSpec = new ArrayList<>(
            Arrays.asList(
                    '+', '(', ')', '{', '}', '[', ']', '!', '&', '$', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
            ));
    List<String> listPSW = new ArrayList<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        doPSW();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form>");
        out.println("<button>Generate</button>");
        out.println("</form>");
        for (int i = 0; i < SIZE_OF_COUNT; i++) {
            out.println("<p>");
            out.println(listPSW.get(i));
            out.println("</p>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }


    public void doPSW() {

        listPSW.clear();

        for (int i = 0; i < SIZE_OF_COUNT; i++) {
            System.out.println();

            listPSW.add(generatorPSW(charactersUpCase, charactersLowCase, charactersSpec));
        }
        System.out.println();
    }

    private static String generatorPSW(List<Character> charactersUpCase, List<Character> charactersLowCase, List<Character> charactersSpec) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int index;
        int secondIndex;

        for (int i = 0; i < SIZE_OF_PASSWORD; i++) {

            secondIndex = random.nextInt(3);

            switch (secondIndex) {
                case 0: {
                    index = random.nextInt(charactersUpCase.size() - 1);
                    sb.append(charactersUpCase.get(index));
                    break;
                }
                case 1: {
                    index = random.nextInt(charactersLowCase.size() - 1);
                    sb.append(charactersLowCase.get(index));
                    break;
                }
                case 2: {
                    index = random.nextInt(charactersSpec.size() - 1);
                    sb.append(charactersSpec.get(index));
                }
            }
        }
        return sb.toString();
    }
}