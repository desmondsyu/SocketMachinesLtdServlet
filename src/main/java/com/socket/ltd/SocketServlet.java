package com.socket.ltd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SocketServlet
 */
@WebServlet("/quote")
public class SocketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SocketServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");
		String quantity = request.getParameter("quantity");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		int qty = quantityValidate(quantity);
		double price = businessLogic(type, qty);

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		try {
			String htmlResponse = "<html>";
			htmlResponse += "<h1>Socket Machines Ltd</h1><br>";
			htmlResponse += "<h3>Contact</h3>";
			htmlResponse += "<p>Name: " + name + "</p>";
			htmlResponse += "<p>Email: " + email + "</p><br>";
			htmlResponse += "<h3>Quote</h3>";
			htmlResponse += "<p>Product: " + type + "</p>";
			htmlResponse += "<p>Quantity: " + qty + "</p>";
			htmlResponse += "<p>Total: $" + price + "</p>";
			htmlResponse += "</html>";

			writer.println(htmlResponse);
		} finally {
			writer.close();
		}

	}

	private int quantityValidate(String quantity) throws NumberFormatException {
		int qty = Integer.parseInt(quantity);

		if (qty <= 0) {
			throw new NumberFormatException("Quantity should be positive");
		}

		return qty;
	}

	private double businessLogic(String type, int quantity) {
		final double A_PRICE = 16.5;
		final double B_PRICE = 21.5;
		final double C_PRICE = 42.99;

		double totalPrice = 0;

		switch (type) {
		case "typeA":
			totalPrice = A_PRICE * quantity;
			break;
		case "typeB":
			totalPrice = B_PRICE * quantity;
			break;
		case "typeC":
			totalPrice = C_PRICE * quantity;
			break;
		}

		return totalPrice;
	}
}
